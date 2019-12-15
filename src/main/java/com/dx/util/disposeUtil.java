package com.dx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class disposeUtil {

    /*public static void main(String[] args) {
        Double dd=4.21;
        System.out.println(getDateTime("2019-07-30 10:55:09",dd,0));
    }
*/
    //主流程
    public  String getDateTime(String beginTime, Double num,Integer cu){
         Integer WORKING_TIME=7;
        if(beginTime==null || num==null){
            System.out.println("参数不正确");
            return null;
        }
        try {
            SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=isWorkTime(beginTime,1,cu);
            /*date=sDateFormat.parse(beginTime);*/
            System.out.println(sDateFormat.format(date));

            int days=0;
            int minutes= (int) Math.round((num % WORKING_TIME)*60);
            int ceil = (int)Math.ceil(num / WORKING_TIME);
            while ((ceil--)>=0){
                while (isHodliDays(date)){
                    date=getNextDate(date,1,null);
                    days++;
                }
                while (getWeek2(date) && isOverDays(date)){
                    date=getNextDate(date,1,null);
                    days++;
                }
                if(ceil>0){
                    days++;
                }
            }
            date = getNextDate(sDateFormat.parse(beginTime), days, minutes);
            String format = sDateFormat.format(date);
            if(cu==0){
                return getDateTime(format, 0.0, 1);
            }
            return sDateFormat.format(date);
        } catch (ParseException e) {
            System.out.println("转换异常");
            return null;
        }
    }

    private  Date isWorkTime(String date,Integer type,Integer cu) {
        DateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar();

        try {
            Date model = sDateFormat.parse(date);
            Date ymdParam =ymd.parse(date);
            String p = new SimpleDateFormat("HH:mm").format(model);
            Date param = df.parse(p);
            Date amStart = df.parse("09:00");
            Date amEnd = df.parse("12:00");
            Date pmStart = df.parse("13:30");
            Date pmEnd = df.parse("17:30");
            long halfTime=pmStart.getTime()-amEnd.getTime();
            Integer minute= Math.toIntExact(halfTime / 1000 / 60);
            if(type==1){
                if(param.getTime() <= amStart.getTime()){
                    model=jointDateTime(ymdParam.getTime()+amStart.getTime(),model);
                }else if(param.getTime() >= amEnd.getTime() && param.getTime() <pmStart.getTime()){
                    model=jointDateTime(ymdParam.getTime()+pmStart.getTime(),model);
                }else if(param.getTime()>pmEnd.getTime()){
                    ymdParam = getNextDate(ymdParam, 1, 0);
                    model=jointDateTime(ymdParam.getTime()+amStart.getTime(),model);
                }
            }else if(type==2 && cu==0){
                if(param.getTime() >= amEnd.getTime() && param.getTime() <=amEnd.getTime()){
                    calendar.setTime(model);
                    calendar.add(Calendar.MINUTE,minute);
                    model = calendar.getTime();
                }
                if(param.getTime()>pmEnd.getTime()){
                    ymdParam = getNextDate(ymdParam, 1, 0);
                    long time=param.getTime()-pmEnd.getTime()+amStart.getTime();
                    minute= Math.toIntExact(time / 1000 / 60);
                    calendar.setTime(ymdParam);
                    calendar.add(Calendar.MINUTE,minute);
                    String dateTime = getDateTime(sDateFormat.format(calendar.getTime()), 0.0,cu++);
                    model=sDateFormat.parse(dateTime);
                }
            }
            return model;
        } catch (ParseException e) {
            return null;
        }

    }

    private  Date jointDateTime(long m, Date model){
        model.setTime(m);
        return model;
    }

    public  boolean getWeek2(Date date){
        Integer[] weeks = {7,1,2,3,4,5,6};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0){
            week_index = 0;
        }
        if(weeks[week_index]==6 ||weeks[week_index]==7){
            return true;
        }
        return false;
    }

    public  boolean isHodliDays(Date date) {
        // 中国法定节假日期
        String isHoliday = "05-01,05-02,05-03,06-07,09-13,10-01,10-02,10-03,10-04,10-05";
        DateFormat df = new SimpleDateFormat("MM-dd");
        String d = df.format(date);
        if (isHoliday.contains(d)) {
            return true;
        }
        return false;
    }
    public  boolean isOverDays(Date date) { // 判断是否滴节假日，是否有节假日加班
        // 节假前后加班日期
        String overDay = "05-05,09-29,10-12";
        DateFormat df = new SimpleDateFormat("MM-dd");
        String d = df.format(date);
        if (overDay.contains(d)) {
            return false;
        }
        return true;

    }


    private static Date getNextDate(Date date, Integer d, Integer m ) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        if(m!=null && m!=0){
            DateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            Date amStart = df.parse("9:00");
            Date amEnd = df.parse("12:00");
            Date pmStart = df.parse("13:30");
            Date pmEnd = df.parse("17:30");
            long halfTime=pmStart.getTime()-amEnd.getTime();
            String hm = df.format(date);
            Date param = df.parse(hm);
            long s=param.getTime()+(m*60*1000);
            long amEndMinute=amEnd.getTime();
            if(param.getTime()<=amEnd.getTime() && s>=amEndMinute){
                s+=halfTime;
                m+=(int)(halfTime/1000/60);
            }
            if(s>pmEnd.getTime()){
                long times=date.getTime()-pmEnd.getTime()+amStart.getTime();
                date.setTime(times);
                d++;
            }
        }
        calendar.setTime(date);
        calendar.add(Calendar.DATE, d);
        if(m==null){
            return calendar.getTime();
        }
        calendar.add(Calendar.MINUTE, m);
        return calendar.getTime();
    }

}

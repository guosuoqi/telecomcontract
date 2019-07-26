package com.dx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class disposeUtil {

    public static void main(String[] args) {
        Double dd=12.0;
        System.out.println(getDateTime("2019-04-29 11:00:00",dd,0));
    }

    //主流程
    public static String getDateTime(String beginTime, Double num,Integer cu){
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
            while ((ceil-=1)>=0){
                while (isHodliDays(date)){
                    date=getNextDate(date,1,null);
                    days+=1;
                }
                while (getWeek2(date)){
                    date=getNextDate(date,1,null);
                    days+=1;
                }
                if(ceil!=0){
                    days+=1;
                }
            }
            date = getNextDate(sDateFormat.parse(beginTime), days, minutes);
            String format = sDateFormat.format(date);
            date=isWorkTime(format,2,1);
            return sDateFormat.format(date);
        } catch (ParseException e) {
            System.out.println("转换异常");
            return null;
        }
    }

    private static Date isWorkTime(String date,Integer type,Integer cu) {
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
            }else {
                if(param.getTime() >= amEnd.getTime() && param.getTime() <=pmEnd.getTime()){
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
                    String dateTime = getDateTime(sDateFormat.format(calendar.getTime()), 0.0,1);
                    model=sDateFormat.parse(dateTime);
                }
            }
            return model;
        } catch (ParseException e) {
            return null;
        }

    }

    private static Date jointDateTime(long m, Date model){
        model.setTime(m);
        return model;
    }

    public static boolean getWeek2(Date date){
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
    public void isOverDay(Date d) { // 指定时间判断是否是双休日
        DateFormat df = new SimpleDateFormat("yy-MM-dd");
        try {
            d = df.parse("");
            if (d.getDay() == 0 || d.getDay() == 6) {
                System.out.println("日期:[" + df.format(d) + "] 是双休日");
            } else {
                System.out.println("日期:[" + df.format(d) + "] 不是双休日");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isHodliDays(Date date) { // 判断是否滴节假日，是否有节假日加班
        // 中国法定节假日期
        String isHoliday = "01-01,01-02,01-03,02-09,02-10,02-11,02-12,02-13,02-14,"
                + "02-15,04-04,04-05,04-06,04-29,04-30,05-01,06-10,06-11,"
                + "06-12,09-19,09-20,09-21,10-01,10-02,10-03,10-04,10-05,10-06,10-07";


        // 节假前后加班日期
        String overDay = "01-05,01-06,02-16,02-17,04-07,04-17,04-28,06-08,06-09,09-22,09-19,10-12";

        DateFormat df = new SimpleDateFormat("MM-dd");
        String d = df.format(date);

        if (isHoliday.contains(d)) {
            System.out.println("是节假日...");
            return true;
        }
        if (overDay.contains(d)) {
            System.out.println("节假前后加班日期...");
            return true;
        }
        return false;
    }


    private static Date getNextDate(Date date, Integer d, Integer m) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        if(d!=null && d!=0){
            DateFormat df = new SimpleDateFormat("HH:mm");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            Date amStart = df.parse("9:00");
            String hm = df.format(date);
            Date param = df.parse(hm);
            long times=date.getTime()-param.getTime()+amStart.getTime();
            date.setTime(times);
            calendar.setTime(date);
        }else {
            calendar.setTime(date);
        }
        calendar.add(Calendar.DATE, d);
        if(m==null){
            return calendar.getTime();
        }
        calendar.add(Calendar.MINUTE, m);
        return calendar.getTime();
    }

}

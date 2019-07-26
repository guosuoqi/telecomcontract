package com.dx.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class disposeUtil {
    private final Integer WORKING_TIME=7;
   


    public static void main(String[] args) {
        Double dd=15.5;
        System.out.println(getDateTime("2019-04-29 12:00:00",dd));
    }

    //主流程
    public static String getDateTime(String beginTime, Double num){
        if(beginTime==null || num==null){
            System.out.println("参数不正确");
            return null;
        }
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date=sDateFormat.parse(beginTime);
            int days=0;
            int minutes= (int) Math.round((num % 7)*60);
            int ceil = (int)Math.ceil(num / 7);
            while ((ceil-=1)>=0){
                while (isHodliDays(date)){
                    date=getNextDate(date,1,null);
                    days+=1;
                }
                while (getWeek2(date)){
                    date=getNextDate(date,1,null);
                    days+=1;
                }
            }
            return sDateFormat.format(getNextDate(sDateFormat.parse(beginTime),days,minutes));
        } catch (ParseException e) {
            System.out.println("转换异常");
            return null;
        }
    }

    public static String getWeek(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        String week = sdf.format(date);
        return week;
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


    private static Date getNextDate(Date date, Integer d, Integer m){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, d);
        if(m==null){
            return calendar.getTime();
        }
        calendar.add(Calendar.MINUTE, m);
        return calendar.getTime();
    }

}

package com.dx.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String getDate(String yyyyMMdd){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(yyyyMMdd);
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    public static String getDate(){
        return  getDate("yyyyMMdd");
    }


    /**
     * 获得指定月之前日期
     */
    public static String getPastDate(int number){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(calendar.MONTH, number); //设置为后3月
        now = calendar.getTime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(now);
    }
    /**
     * 時間与N月后时间作比较
     */
    public static boolean dateSusses(String date,int number) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date parse = sdf.parse(date);
        long time = parse.getTime();
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(calendar.MONTH, number); //设置为后3月
        now = calendar.getTime();
        long time1 = now.getTime();
        if(time>time1){
            return true;
        }
        return false;
    }
    /**
     * 得到前四个小时的时间 默认格式 HH:mm:ss
     * @return
     */
    public static String getBeforeHourTime(Integer time){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)-time);
        return DateFormatUtils.format(calendar.getTime(),"yyyy-MM-dd HH:mm:ss");
    }
}

package com.dx.util;

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
}

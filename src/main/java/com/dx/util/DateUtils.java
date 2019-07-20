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
    public static String getPastDate(int month){
        Date now = new Date(); //当前时间
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(now);//把当前时间赋给日历
        calendar.add(calendar.MONTH, month); //设置为前3月
        dBefore = calendar.getTime(); //得到前3月的时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        return sdf.format(dBefore); //格式化前3月的时间
    }
}

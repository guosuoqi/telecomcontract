package com.ny.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhuyi on 2019/5/21 10:01
 */
public class ExceptionPrintUtil {
    public static final Logger log = LoggerFactory.getLogger(ExceptionPrintUtil.class);

    /**
     * 输出异常信息
     * @param e
     * @return
     */
    public static String errorTrackSpace(Exception e) {
        StringBuffer sb = new StringBuffer();
        if (e != null) {
            for (StackTraceElement element : e.getStackTrace()) {
                sb.append("\r\n\t").append(element);
            }
        }
        return sb.length() == 0 ? null : sb.toString();
    }
}


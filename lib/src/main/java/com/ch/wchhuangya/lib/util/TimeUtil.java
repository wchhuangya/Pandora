package com.ch.wchhuangya.lib.util;

import android.text.TextUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wchya on 2016-12-12 17:40
 */

public class TimeUtil {

    private TimeUtil() {
        throw new UnsupportedOperationException("该类不能被实例化～");
    }

    private static SimpleDateFormat sdf;
    private static final String LONG_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String SHORT_FORMAT = "yyyy-MM-dd";

    /**
     * 获取当前时间
     * @param format 时间格式，可以传null，默认格式：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentTime(String format) {
        if(null == format)
            sdf = new SimpleDateFormat(LONG_FORMAT);
        else
            sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    /**
     * 把时间戳转换为指定的日期
     * @param timestamp 时间戳
     * @param format 要转换的格式，如果传空或null，使用默认长时间格式：yyyy-MM-dd HH:mm:ss
     * @return 转换后的时间
     */
    public static String changeTimestampToTime(long timestamp, String format) {
        if (TextUtils.isEmpty(format))
            sdf = new SimpleDateFormat(LONG_FORMAT);
        else
            sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(timestamp > 999999999999L ? timestamp : timestamp * 1000));
    }

    /**
     * 获取当前时间的时间戳
     * @return 当前时间的时间戳
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
}

package com.ch.wchhuangya.lib.util;

import android.text.TextUtils;

/**
 * Created by wchya on 2016-11-25 21:32
 */

public class StringUtil {

    private StringUtil() {
        throw new UnsupportedOperationException("该类不能被实例化");
    }

    /** 判断字符串是否为 null 或 空 */
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    /** 判断字符串是否不为 null 或 空 */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}

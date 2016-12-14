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

    /** 是否都为 null */
    public static boolean isAllNull(String...str) {
        boolean val = true;
        for (String s : str)
            if (s != null) {
                val = false;
                break;
            }
        return val;
    }

    /** 传入参数是否有 null 值 */
    public static boolean hasNull(String...str) {
        boolean val = false;
        for (String s : str)
            if (s == null) {
                val = true;
                break;
            }
        return val;
    }

    /** 让 html 中的 img 标签横向铺满屏幕 */
    public static String adjustImgFitScreen(String html) {
        html = html.replace("<img", "<img width=100% ");
        return html;
    }
}

package com.ch.wchhuangya.lib.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wchya on 2017-01-02 17:03
 */

public class RegularUtil {

    private RegularUtil() {
        throw new UnsupportedOperationException("该类不能被实例化～");
    }

    /**
     * 验证字符串是否为身份证号
     * @param idCard 待难的字符串
     */
    public static boolean validIDCard(String idCard) {
        Pattern pattern = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
        Matcher matcher = pattern.matcher(idCard);
        return matcher.find();
    }
}

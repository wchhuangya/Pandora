package com.ch.wchhuangya.android.pandora.enums;

/**
 * RESTAPI错误码枚举
 * Created by wchya on 2017-01-06 11:24
 */

public enum RestError {
    ERROR_101("用户名或密码不正确");

    private String des;

    RestError(String des) {
        this.des = des;
    }

    public static String getErrorDes(String code) {
        for (RestError error : RestError.values()) {
            if (error.toString().contains(code))
                return error.des;
        }
        return "";
    }
}

package com.ch.wchhuangya.android.pandora.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by wchya on 2017-01-05 11:42
 */

public class Md5Query extends BmobObject {
    private String orignalVal;
    private String encryptVal;

    public String getEncryptVal() {
        return encryptVal;
    }

    public void setEncryptVal(String encryptVal) {
        this.encryptVal = encryptVal;
    }

    public String getOrignalVal() {
        return orignalVal;
    }

    public void setOrignalVal(String orignalVal) {
        this.orignalVal = orignalVal;
    }
}

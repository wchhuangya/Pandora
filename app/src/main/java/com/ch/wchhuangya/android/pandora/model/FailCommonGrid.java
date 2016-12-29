package com.ch.wchhuangya.android.pandora.model;

import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;
import android.databinding.ObservableMap;

/**
 * Created by wchya on 2016-12-28 17:13
 */

public class FailCommonGrid extends BaseModel {
    public static final String APP_IMG_URL = "APP_IMG_URL";
    public static final String APP_NAME = "APP_NAME";
    public ObservableMap<String, Object> appDatas = new ObservableArrayMap<>();
    public ObservableField<String> appIcon = new ObservableField<>();
    public ObservableField<String> appName = new ObservableField<>();
}

package com.ch.wchhuangya.lib.binding;

import android.databinding.BindingConversion;

import com.ch.wchhuangya.lib.util.TimeUtil;

/**
 * Created by wchya on 2016-12-12 17:38
 */

public class DateTimeBinding {
    @BindingConversion
    public static String changeTimestampToTime(long time) {
        return TimeUtil.changeTimestampToTime(time, null);
    }
}

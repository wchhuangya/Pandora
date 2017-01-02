package com.ch.wchhuangya.lib.util;

import android.app.Activity;
import android.view.Gravity;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by wchya on 2017-01-01 18:03
 */

public class CroutonUtil {

    private CroutonUtil() {
        throw new UnsupportedOperationException("该类不能被实例化～");
    }

    /** 提示背景色 */
    private static final int INFO_COLOR = 0xff1296db;
    /** 警告背景色 */
    private static final int ALERT_COLOR = 0xffff4444;

    private static Style getInfoStyle() {
        return new Style.Builder()
                .setBackgroundColorValue(INFO_COLOR)
                .setGravity(Gravity.CENTER)
                .setTextColor(android.R.color.white)
                .build();
    }

    private static Style getAlertStyle() {
        return new Style.Builder()
                .setBackgroundColorValue(ALERT_COLOR)
                .setGravity(Gravity.CENTER)
                .setTextColor(android.R.color.white)
                .build();
    }

    private static Configuration getNormalConfiguration() {
        return new Configuration.Builder()
                .setDuration(1500).build();
    }

    /**
     * 显示提示信息
     * @param activity 显示信息的 Activity
     * @param text 显示的信息
     * @param viewgroupId 显示信息的 ViewGroup 的 ID
     */
    public static void showInfo(Activity activity, String text, int viewgroupId) {
        Crouton.showText(activity, text, getInfoStyle(), viewgroupId, getNormalConfiguration());
    }

    /**
     * 显示 警告信息
     * @param activity 显示信息的 Activity
     * @param text 显示的信息
     * @param viewgroupId 显示信息的 ViewGroup 的 ID
     */
    public static void showAlert(Activity activity, String text, int viewgroupId) {
        Crouton.showText(activity, text, getAlertStyle(), viewgroupId, getNormalConfiguration());
    }
}

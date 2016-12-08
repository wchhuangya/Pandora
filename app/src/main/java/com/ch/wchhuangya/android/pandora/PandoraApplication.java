package com.ch.wchhuangya.android.pandora;

import com.ch.wchhuangya.lib.LibApplication;
import com.ch.wchhuangya.lib.util.Constant;

/**
 * Created by wchya on 2016-11-28 09:10
 */

public class PandoraApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Constant.LOG_TAG = "pandora";
    }
}

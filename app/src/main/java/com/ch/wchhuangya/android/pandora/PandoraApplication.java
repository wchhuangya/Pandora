package com.ch.wchhuangya.android.pandora;

import com.ch.wchhuangya.lib.LibApplication;
import com.ch.wchhuangya.lib.util.Constant;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobConfig;

/**
 * Created by wchya on 2016-11-28 09:10
 */

public class PandoraApplication extends LibApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        Constant.LOG_TAG = "pandora";

        BmobConfig config =new BmobConfig.Builder(this)
        //设置appkey
        .setApplicationId("57fdee94722742607d2e87c75978970a")
        //请求超时时间（单位为秒）：默认15s
//        .setConnectTimeout(30)
        //文件分片上传时每片的大小（单位字节），默认512*1024
//        .setUploadBlockSize(1024*1024)
        //文件的过期时间(单位为秒)：默认1800s
//        .setFileExpiration(2500)
        .build();
        Bmob.initialize(config);
    }
}

package com.ch.wchhuangya.lib.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by wchya on 2016-11-26 16:15
 */

public class OkHttpUtil {

    /** 连接成功后，读取数据的等待时间 */
    public static int READ_TIMEOUT = 60;
    /** 连接成功后，定稿数据的等待时间 */
    public static int WRITE_TIMEOUT = 60;
    /** 连接一个 URL 的连接等待时间 */
    public static final int CONNECT_TIMEOUT = 5;

    public static OkHttpClient httpClient = new OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build();
}

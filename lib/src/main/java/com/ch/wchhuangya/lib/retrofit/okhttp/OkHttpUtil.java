package com.ch.wchhuangya.lib.retrofit.okhttp;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by wchya on 2016-11-26 16:15
 */

public class OkHttpUtil {

    /** 连接成功后，读取数据的等待时间 */
    private static int READ_TIMEOUT = 60;
    /** 连接成功后，定稿数据的等待时间 */
    private static int WRITE_TIMEOUT = 60;
    /** 连接一个 URL 的连接等待时间 */
    private static final int CONNECT_TIMEOUT = 5;

    public static OkHttpClient httpClient;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        // 设置缓存

        // 公共参数

        // 设置头部
        Interceptor headInterceptor = chain -> {
            Request orignalRequest = chain.request();
            Request.Builder builder1 = orignalRequest.newBuilder()
                    .header("apikey", "ecea04d457d7aed440bafdc95e582c36")
                    .method(orignalRequest.method(), orignalRequest.body());
            Request request = builder1.build();
            return chain.proceed(request);
        };
        builder.addInterceptor(headInterceptor);

        // Log 信息拦截器
        /*HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);*/

        // 设置 cookie

        // 设置超时、重连
        builder.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);

        httpClient = builder.build();
    }
}

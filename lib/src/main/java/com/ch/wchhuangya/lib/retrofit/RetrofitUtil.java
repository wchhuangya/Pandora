package com.ch.wchhuangya.lib.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 工具类
 * Created by wchya on 2016-11-26 10:22
 */

public class RetrofitUtil {

    public static String BASE_URL = "http://api.dagoogle.cn/";

    public static Retrofit.Builder builder = new Retrofit.Builder()
//                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    /** 根据接口生成相应的客户端 */
    public static <T> T generator(Class<T> service) {
        Retrofit retrofit = builder.client(OkHttpUtil.httpClient).build();
        return retrofit.create(service);
    }
}

package com.ch.wchhuangya.lib.retrofit;

import com.ch.wchhuangya.lib.retrofit.okhttp.OkHttpUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 工具类
 * 注意：1. 在使用 getnerator 方法之前，必须给 Retrofit 的 Builder 设置 BaseUrl；
 * Created by wchya on 2016-11-26 10:22
 */

public class RetrofitUtil {

    private static String BASE_URL = "http://api.dagoogle.cn/";

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

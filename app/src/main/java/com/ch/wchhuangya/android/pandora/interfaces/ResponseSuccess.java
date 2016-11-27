package com.ch.wchhuangya.android.pandora.interfaces;

/**
 * 网络请求成功后处理的方法
 * Created by wchya on 2016-11-26 17:47
 */
public interface ResponseSuccess<T> {

    void onSuccess(T t);
}

package com.ch.wchhuangya.android.pandora.vm;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by wchya on 2016-11-27 20:32
 */

public abstract class BaseVM {

    /** VM 模式中，View 引用的持有 */
    protected AppCompatActivity mActivity;

    protected Fragment mFragment;

    protected Context mContext;
    /** 所有用到的观察者 */
    protected List<Subscription> mSubscriptions = new ArrayList<>();

    /** 释放持有的资源引用 */
    public abstract void reset();

    /** 将所有注册的观察者反注册掉 */
    public void unsubscribe() {
        for (Subscription subscription : mSubscriptions) {
            if (subscription != null && subscription.isUnsubscribed())
                subscription.unsubscribe();
        }
    }
}

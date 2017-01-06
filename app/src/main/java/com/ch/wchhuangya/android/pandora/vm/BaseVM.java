package com.ch.wchhuangya.android.pandora.vm;

import android.app.Dialog;
import android.content.Context;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import rx.Subscription;

/**
 * Created by wchya on 2016-11-27 20:32
 */

public abstract class BaseVM {

    public ObservableBoolean searchShow = new ObservableBoolean(false);
    public ObservableBoolean listShow = new ObservableBoolean(false);
    public ObservableBoolean pbShow = new ObservableBoolean(true);
    public ObservableBoolean tipsShow = new ObservableBoolean(false);
    public ObservableField<String> barTitle = new ObservableField<>("");

    /** VM 模式中，View 引用的持有 */
    protected AppCompatActivity mActivity;
    /** VM 模式中，View 引用的持有 */
    protected Fragment mFragment;
    /** VM 模式中，上下文引用的持有 */
    protected Context mContext;
    /** 所有用到的观察者 */
    protected List<Subscription> mSubscriptions = new ArrayList<>();
    /** 公用的等待动画 */
    protected Dialog mDialog;

    public void dismissDialog() {
        if (mDialog != null)
            mDialog.dismiss();
    }

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

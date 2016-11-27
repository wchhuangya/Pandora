package com.ch.wchhuangya.android.pandora.vm;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wchya on 2016-11-27 20:32
 */

public abstract class BaseVM {

    /** VM 模式中，View 引用的持有 */
    protected AppCompatActivity mActivity;
    /** 资源类 */
    protected Resources mResources;

    /** 释放持有的资源引用 */
    public abstract void reset();
}

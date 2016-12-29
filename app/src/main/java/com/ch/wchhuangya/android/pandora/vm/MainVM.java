package com.ch.wchhuangya.android.pandora.vm;

import android.databinding.ObservableField;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wchya on 2016-11-27 17:39
 */

public class MainVM extends BaseVM {

    /** Toolbar 标题 */
    public ObservableField<String> toolbarTitle = new ObservableField<>("新闻");

    public MainVM(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void setMainContent(int frameLayoutId, Fragment fragment) {
        mActivity.getSupportFragmentManager().beginTransaction().replace(frameLayoutId, fragment).commit();
    }

    @Override
    public void reset() {
        mActivity = null;
        unsubscribe();
    }
}

package com.ch.wchhuangya.android.pandora.vm.account;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import com.ch.wchhuangya.android.pandora.vm.BaseVM;

import cn.bmob.v3.BmobUser;

/**
 * Created by wchya on 2017-01-06 09:16
 */

public class InfoVM extends BaseVM {

    public InfoVM(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void logOut() {
        BmobUser.logOut();
        mActivity.setResult(Activity.RESULT_OK);
        mActivity.finish();
    }

    @Override
    public void reset() {
        unsubscribe();
    }
}

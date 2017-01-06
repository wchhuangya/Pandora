package com.ch.wchhuangya.android.pandora.vm.account;

import android.content.Intent;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;

import com.ch.wchhuangya.android.pandora.view.activity.account.InfoActivity;
import com.ch.wchhuangya.android.pandora.view.activity.account.LoginActivity;
import com.ch.wchhuangya.android.pandora.vm.BaseVM;
import com.ch.wchhuangya.lib.util.TimeUtil;

import cn.bmob.v3.BmobUser;

/**
 * Created by wchya on 2017-01-03 14:15
 */

public class HeaderVM extends BaseVM {
    public static final int REQUEST_LOGIN = 1;
    public static final int REQUEST_INFO = 2;
    public ObservableField<String> headUrl = new ObservableField<>();
    public ObservableField<String> nickName = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();

    public HeaderVM(AppCompatActivity activity) {
        mActivity = activity;
    }

    public void initUser() {
        BmobUser currentUser = BmobUser.getCurrentUser();
        if (currentUser != null) {
            nickName.set(currentUser.getUsername());
            date.set(TimeUtil.getCurrentTime(TimeUtil.SHORT_FORMAT));
        }
    }

    public void headerClick() {
        Intent intent;
        if (BmobUser.getCurrentUser() == null) {
            intent = new Intent(mActivity, LoginActivity.class);
            intent.putExtra("ss", "ss");
            mActivity.startActivityForResult(intent, REQUEST_LOGIN);
        } else {
            intent = new Intent(mActivity, InfoActivity.class);
            intent.putExtra("ss", "ss");
            mActivity.startActivityForResult(intent, REQUEST_INFO);
        }
    }

    public void setUser() {
        BmobUser currentUser = BmobUser.getCurrentUser();
        nickName.set(currentUser != null ? currentUser.getUsername() : "游侠一枚");
        date.set(currentUser != null ? TimeUtil.getCurrentTime(TimeUtil.SHORT_FORMAT) : "");
    }

    @Override
    public void reset() {
        mActivity = null;
        unsubscribe();
    }
}

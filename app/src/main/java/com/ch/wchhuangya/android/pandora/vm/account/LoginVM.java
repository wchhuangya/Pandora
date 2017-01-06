package com.ch.wchhuangya.android.pandora.vm.account;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.components.CustomProgressDialog;
import com.ch.wchhuangya.android.pandora.databinding.LoginBinding;
import com.ch.wchhuangya.android.pandora.enums.RestError;
import com.ch.wchhuangya.android.pandora.vm.BaseVM;
import com.ch.wchhuangya.lib.util.Constant;
import com.ch.wchhuangya.lib.util.CroutonUtil;
import com.ch.wchhuangya.lib.util.MD5Util;
import com.ch.wchhuangya.lib.util.StringUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.marvinlabs.widget.floatinglabel.edittext.FloatingLabelEditText;

import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by wchya on 2017-01-04 11:51
 */

public class LoginVM extends BaseVM implements FloatingLabelEditText.EditTextListener {

    private LoginBinding mBinding;
    private String mName;
    private String mPwd;

    public LoginVM(AppCompatActivity activity, LoginBinding binding) {
        mActivity = activity;
        mBinding = binding;
    }

    public void initFloatingLabel() {
        mBinding.loginNameEt.setEditTextListener(this);
        mBinding.loginPwdEt.setEditTextListener(this);
    }

    public void initLoginBtn() {
        RxView.clicks(mBinding.loginBtn)
                .throttleFirst(2500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> login());
    }

    public void login() {
        mDialog = CustomProgressDialog.show(mActivity);
        if (StringUtil.isEmpty(mName))
            CroutonUtil.showAlert(mActivity, "请输入用户名", R.id.login_name_fl);
        else if (StringUtil.isEmpty(mPwd))
            CroutonUtil.showAlert(mActivity, "请输入密码", R.id.login_pwd_fl);
        else {
            BmobUser user = new BmobUser();
            user.setUsername(mName);
            user.setPassword(new MD5Util().getMD5ofStr(mPwd));
            user.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null) {
                        mActivity.setResult(Activity.RESULT_OK);
                        mActivity.finish();
                    } else {
                        int errorCode = e.getErrorCode();
                        CroutonUtil.showAlert(mActivity, RestError.getErrorDes(String.valueOf(errorCode)), R.id.login_name_fl);
                        Log.d(Constant.LOG_TAG, "done: 用户登录失败，错误码：" + errorCode);
                    }
                    dismissDialog();
                }
            });
        }
    }

    @Override
    public void onTextChanged(FloatingLabelEditText source, String text) {
        switch (source.getId()) {
            case R.id.login_name_et:
                mName = text;
                break;
            case R.id.login_pwd_et:
                mPwd = text;
                break;
        }
    }

    @Override
    public void reset() {
        unsubscribe();
    }
}

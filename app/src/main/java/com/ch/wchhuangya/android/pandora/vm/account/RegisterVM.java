package com.ch.wchhuangya.android.pandora.vm.account;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.components.CustomProgressDialog;
import com.ch.wchhuangya.android.pandora.databinding.RegisterBinding;
import com.ch.wchhuangya.android.pandora.model.Md5Query;
import com.ch.wchhuangya.android.pandora.vm.BaseVM;
import com.ch.wchhuangya.lib.util.Constant;
import com.ch.wchhuangya.lib.util.CroutonUtil;
import com.ch.wchhuangya.lib.util.MD5Util;
import com.ch.wchhuangya.lib.util.StringUtil;
import com.jakewharton.rxbinding.view.RxView;
import com.marvinlabs.widget.floatinglabel.edittext.FloatingLabelEditText;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by wchya on 2017-01-04 15:58
 */

public class RegisterVM extends BaseVM implements FloatingLabelEditText.EditTextListener {

    RegisterBinding mBinding;
    private String mName;
    private String mPwd;
    private String mRepeatPwd;

    public RegisterVM(AppCompatActivity activity, RegisterBinding binding) {
        mActivity = activity;
        mBinding = binding;
    }

    public void initFloatingLabel() {
        mBinding.registerName.setEditTextListener(this);
        mBinding.registerPwd.setEditTextListener(this);
        mBinding.registerRepeatPwd.setEditTextListener(this);
    }

    public void initSubmit() {
        RxView.clicks(mBinding.registerBtn)
                .throttleFirst(2500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> registerClick());
    }

    private void registerClick() {
        if (StringUtil.isEmpty(mName))
            CroutonUtil.showAlert(mActivity, "请输入用户名", R.id.register_name_fl);
        else if (StringUtil.isEmpty(mPwd))
            CroutonUtil.showAlert(mActivity, "请输入密码", R.id.register_pwd_fl);
        else if (StringUtil.isEmpty(mRepeatPwd))
            CroutonUtil.showAlert(mActivity, "请输入密码", R.id.register_repeat_pwd_fl);
        else if (mName.length() < 3 || mName.length() > 16)
            CroutonUtil.showAlert(mActivity, "用户名长度 3～16 位", R.id.register_name_fl);
        else if (mPwd.length() < 6 || mPwd.length() > 20)
            CroutonUtil.showAlert(mActivity, "密码长度 6～20 位", R.id.register_pwd_fl);
        else if (!mPwd.equals(mRepeatPwd))
            CroutonUtil.showAlert(mActivity, "两次密码输入不一致", R.id.register_pwd_fl);
        else {
            String encryptPwd = new MD5Util().getMD5ofStr(mPwd);
            BmobUser user = new BmobUser();
            user.setUsername(mName);
            user.setPassword(encryptPwd);
            mDialog = CustomProgressDialog.show(mActivity);
            user.signUp(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null) {
                        mSubscriptions.add(
                                Observable.timer(1, TimeUnit.MILLISECONDS)
                                            .observeOn(Schedulers.io())
                                            .subscribe(
                                                    aLong -> {
                                                        BmobQuery<Md5Query> query = new BmobQuery<>();
                                                        query.addWhereEqualTo("orignalVal", mPwd);
                                                        query.findObjects(new FindListener<Md5Query>() {
                                                            @Override
                                                            public void done(List<Md5Query> list, BmobException e) {
                                                                if (e == null) {
                                                                    if (list.size() == 0) {
                                                                        Md5Query md5Query = new Md5Query();
                                                                        md5Query.setOrignalVal(mPwd);
                                                                        md5Query.setEncryptVal(encryptPwd);
                                                                        md5Query.save(new SaveListener<String>() {
                                                                            @Override
                                                                            public void done(String s, BmobException e) {
                                                                                Log.d(Constant.LOG_TAG, "保存完成：" + s + ", " + e);
                                                                            }
                                                                        });
                                                                    }
                                                                } else {
                                                                    Log.e(Constant.LOG_TAG, "done: 查询 MD5 字典出错", e);
                                                                }
                                                            }
                                                        });
                                                    }
                                            )
                        );
                        mActivity.setResult(Activity.RESULT_OK);
                        mActivity.finish();
                    } else {
                        String errMsg = e.getMessage();
                        if (errMsg.equals("username '" + mName + "' already taken."))
                            CroutonUtil.showAlert(mActivity, "用户名已经存在，请重新输入", R.id.register_name_fl);
                        else
                            Toast.makeText(mActivity, "失败：" + errMsg, Toast.LENGTH_LONG).show();
                    }
                    dismissDialog();
                }
            });
        }
    }

    @Override
    public void onTextChanged(FloatingLabelEditText source, String text) {
        switch (source.getId()) {
            case R.id.register_name:
                mName = text;
                break;
            case R.id.register_pwd:
                mPwd = text;
                break;
            case R.id.register_repeat_pwd:
                mRepeatPwd = text;
                break;
        }
    }

    @Override
    public void reset() {
        unsubscribe();
    }
}

package com.ch.wchhuangya.android.pandora.view.activity.account;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.RegisterBinding;
import com.ch.wchhuangya.android.pandora.view.activity.BaseActivity;
import com.ch.wchhuangya.android.pandora.vm.account.RegisterVM;

/**
 * Created by wchya on 2017-01-04 15:44
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private RegisterBinding mBinding;
    private RegisterVM mRegisterVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.register);
        mRegisterVM = new RegisterVM(this, mBinding);
        mBinding.setRegister(mRegisterVM);

        initToolbar();

        // 初始化输入控件
        mRegisterVM.initFloatingLabel();

        // 初始化提交按钮
        mRegisterVM.initSubmit();
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.registerToolbar);
        mBinding.registerToolbar.setNavigationIcon(R.mipmap.toolbar_back);
        mBinding.registerToolbar.setNavigationOnClickListener(this);
        getSupportActionBar().setTitle(getResources().getString(R.string.register_new_user));
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRegisterVM.reset();
    }
}

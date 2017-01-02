package com.ch.wchhuangya.android.pandora.view.activity.tools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.CalculatorBinding;
import com.ch.wchhuangya.android.pandora.view.activity.BaseActivity;
import com.ch.wchhuangya.android.pandora.vm.tools.CalculatorVM;

/**
 * Created by wchya on 2016-12-22 17:13
 */

public class CalculatorActivity extends BaseActivity {

    private CalculatorBinding mBinding;
    private CalculatorVM mCalVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.calculator);
        mCalVM = new CalculatorVM(this);
        mBinding.setVm(mCalVM);

        setSupportActionBar(mBinding.toolbar.commonToolbar);
        mCalVM.barTitle.set("计算器");
        mBinding.toolbar.commonToolbar.setNavigationOnClickListener(view -> finish());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCalVM.reset();
    }
}

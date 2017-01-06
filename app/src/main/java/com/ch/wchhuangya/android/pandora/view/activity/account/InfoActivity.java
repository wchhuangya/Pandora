package com.ch.wchhuangya.android.pandora.view.activity.account;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.InfoBinding;
import com.ch.wchhuangya.android.pandora.view.activity.BaseActivity;
import com.ch.wchhuangya.android.pandora.vm.account.InfoVM;

/**
 * Created by wchya on 2017-01-06 08:41
 */

public class InfoActivity extends BaseActivity implements View.OnClickListener {

    private InfoBinding mBinding;
    private InfoVM mInfoVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.info);
        mInfoVM = new InfoVM(this);
        mBinding.setInfo(mInfoVM);

        initToolbar();
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.infoToolbar);
        mBinding.infoToolbar.setNavigationIcon(R.mipmap.toolbar_back);
        mBinding.infoToolbar.setNavigationOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mInfoVM.reset();
    }
}

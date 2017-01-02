package com.ch.wchhuangya.android.pandora.view.activity.life;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.QueryIdCardInfoBinding;
import com.ch.wchhuangya.android.pandora.view.activity.BaseActivity;
import com.ch.wchhuangya.android.pandora.vm.life.QueryIdentificationCardInfoVM;

/**
 * Created by wchya on 2017-01-01 15:23
 */

public class QueryIdentificationCardInfoActivity extends BaseActivity {

    private QueryIdCardInfoBinding mBinding;
    private QueryIdentificationCardInfoVM mVm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.query_id_card_info);
        mVm = new QueryIdentificationCardInfoVM(this);
        mVm.pbShow.set(false);
        mBinding.setInfo(mVm);

        initBtn();
    }

    private void initBtn() {
        mVm.initBtn(mBinding.cardInfoBtn, mBinding.cardInfoEt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVm.reset();
    }
}

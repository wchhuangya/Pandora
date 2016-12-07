package com.ch.wchhuangya.android.pandora.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.CommonRecyclerviewBinding;
import com.ch.wchhuangya.android.pandora.vm.CommonRecyclerViewVM;

/**
 * Created by wchya on 2016-11-28 11:10
 */

public class SingleNewsFragment extends BaseFragment {

    private CommonRecyclerviewBinding mBinding;
    private CommonRecyclerViewVM mCrvVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.common_recyclerview, container, false);
        mCrvVM = new CommonRecyclerViewVM(getContext());
        mBinding.setVm(mCrvVM);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCrvVM.reset();
    }
}

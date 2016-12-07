package com.ch.wchhuangya.android.pandora.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.vm.CommonRecyclerViewVM;

/**
 * Created by wchya on 2016-11-28 11:10
 */

public class SingleNewsFragment extends BaseFragment {

    private CommonRecyclerViewVM mCrvVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mCrvVM = new CommonRecyclerViewVM(getContext(), getResources(), container);
        mCrvVM.mBinding.setVm(mCrvVM);
        return mCrvVM.mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCrvVM.reset();
    }
}

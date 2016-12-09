package com.ch.wchhuangya.android.pandora.view.activity.calculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.CalculatorBinding;
import com.ch.wchhuangya.android.pandora.vm.CalculatorVM;

/**
 * Created by wchya on 2016-12-07 16:17
 */

public class CalculatorFragment extends Fragment {

    private CalculatorBinding mBinding;
    private CalculatorVM mCalVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.calculator, container, false);
        mCalVM = new CalculatorVM(getContext());
        mBinding.setCal(mCalVM);
        return mBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCalVM.reset();
        mCalVM.unsubscribe();
    }
}
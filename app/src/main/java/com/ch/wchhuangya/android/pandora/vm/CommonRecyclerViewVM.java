package com.ch.wchhuangya.android.pandora.vm;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableBoolean;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.CommonRecyclerviewBinding;

/**
 * Created by wchya on 2016-11-28 14:51
 */

public class CommonRecyclerViewVM extends BaseVM {

    public ObservableBoolean searchShow = new ObservableBoolean(false);
    public ObservableBoolean listShow = new ObservableBoolean(false);
    public ObservableBoolean pbShow = new ObservableBoolean(false);
    public ObservableBoolean tipsShow = new ObservableBoolean(false);

    public CommonRecyclerviewBinding mBinding;

    public CommonRecyclerViewVM(Context context, Resources resources, ViewGroup container) {
        mResources = resources;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.common_recyclerview, container, false);
    }

    @Override
    public void reset() {
        mActivity = null;
        mResources = null;
    }
}

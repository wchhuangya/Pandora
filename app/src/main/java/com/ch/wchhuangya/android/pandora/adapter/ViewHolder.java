package com.ch.wchhuangya.android.pandora.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Created by wchya on 2016-11-28 21:11
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;

    public ViewHolder(ViewDataBinding binding) {
        super(binding.getRoot());

        mBinding = binding;
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }
}
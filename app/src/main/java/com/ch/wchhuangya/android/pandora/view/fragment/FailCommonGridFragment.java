package com.ch.wchhuangya.android.pandora.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.adapter.FailCommonGridAdapter;
import com.ch.wchhuangya.android.pandora.databinding.FailCommonGridBinding;
import com.ch.wchhuangya.android.pandora.model.FailCommonGrid;
import com.ch.wchhuangya.android.pandora.view.activity.MainActivity;
import com.ch.wchhuangya.android.pandora.vm.FailCommonGridVM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wchya on 2016-12-23 10:47
 */

public class FailCommonGridFragment extends BaseFragment {

    private FailCommonGridBinding mBinding;
    private FailCommonGridVM mFailCommonGridVM;
    private String mType;
    private List<Map<String, Object>> mData = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString(MainActivity.FRAGMENT_TAG, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fail_common_grid, container, false);

        /*mFailCommonGridVM = new FailCommonGridVM(getContext());
        mFailCommonGridVM.tipsShow.set(false);*/
        mBinding.setVm(new FailCommonGridVM(getContext()));

        switch (mType) {
            case MainActivity.TAG_LIFE:
                initToolsAppData();
                break;
            case MainActivity.TAG_TOOLS:
                initToolsAppData();
                break;
            case MainActivity.TAG_STUDY:
                initToolsAppData();
                break;
        }

        //mFailCommonGridVM.initRecyclerView(getActivity(), mBinding.commonGrid);
        initRecyclerView();

        return mBinding.getRoot();
    }

    private void initRecyclerView() {
        mBinding.commonGridRecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mBinding.commonGridRecyclerview.setAdapter(new FailCommonGridAdapter(mData));
    }

    public void initToolsAppData() {
        Map<String, Object> map = new HashMap<>();

        map.put(FailCommonGrid.APP_IMG_URL, R.mipmap.calculator);
        map.put(FailCommonGrid.APP_NAME, getContext().getResources().getString(R.string.calculator));

        mData.add(map);
    }
}

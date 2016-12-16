package com.ch.wchhuangya.android.pandora.view.fragment.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.adapter.SingleNewsAdapter;
import com.ch.wchhuangya.android.pandora.databinding.CommonRecyclerviewBinding;
import com.ch.wchhuangya.android.pandora.view.fragment.BaseFragment;
import com.ch.wchhuangya.android.pandora.vm.news.SingleNewsVM;

/**
 * Created by wchya on 2016-11-28 11:10
 */

public class SingleNewsFragment extends BaseFragment {

    private CommonRecyclerviewBinding mBinding;
    private SingleNewsVM mCrvVM;
    private int pos = 0;
    private SingleNewsAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null)
            pos = bundle.getInt("pos", 0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.common_recyclerview, container, false);
        mCrvVM = new SingleNewsVM(getContext());
        mBinding.setVm(mCrvVM);

        initRecyclerView();

        initSwipeRefershLayout();

        return mBinding.getRoot();
    }

    /** 初始化 RecyclerView */
    private void initRecyclerView() {
        mBinding.commonRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new SingleNewsAdapter(getContext());
        mBinding.commonRecyclerview.setAdapter(mAdapter);
        mCrvVM.loadData(pos, mAdapter, 0, mBinding.commonRvRefreshlayout);
        mBinding.commonRecyclerview.addOnItemTouchListener(mCrvVM.onItemTouchClick(mBinding.commonRecyclerview, pos,
                mAdapter, mBinding.commonRvRefreshlayout));
        mBinding.commonRecyclerview.addOnScrollListener(mCrvVM.onScrollListener(pos, mAdapter, mBinding.commonRvRefreshlayout));
    }

    /** 初始化 SwipeRefreshLayout */
    private void initSwipeRefershLayout() {
        mBinding.commonRvRefreshlayout.setColorSchemeResources(R.color.srl_first, R.color.srl_second, R.color.srl_third);
        mBinding.commonRvRefreshlayout.setEnabled(false);
        mBinding.commonRvRefreshlayout.setOnRefreshListener(() -> mCrvVM.refresh(pos, mAdapter, mBinding.commonRvRefreshlayout));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCrvVM.reset();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (mCrvVM != null) {
            outState.putInt("page", mCrvVM.getPage());
        }
    }
}

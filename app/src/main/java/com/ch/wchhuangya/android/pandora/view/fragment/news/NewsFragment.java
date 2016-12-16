package com.ch.wchhuangya.android.pandora.view.fragment.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.NewsFragmentBinding;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;
import com.ch.wchhuangya.android.pandora.view.fragment.BaseFragment;

/**
 * Created by wchya on 2016-11-28 09:16
 */

public class NewsFragment extends BaseFragment {

    private NewsFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.news_fragment, container, false);
        mBinding.newsFragmentViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                SingleNewsFragment singleNewsFragment = new SingleNewsFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pos", position);
                singleNewsFragment.setArguments(bundle);
                return singleNewsFragment;
            }

            @Override
            public int getCount() {
                return MainEnum.NewsTitle.values().length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return MainEnum.NewsTitle.getTitleByPos(position);
            }
        });
        mBinding.newsFragmentStl.setViewPager(mBinding.newsFragmentViewpager);
        return mBinding.getRoot();
    }
}

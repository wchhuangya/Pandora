package com.ch.wchhuangya.android.pandora.vm;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.MainBinding;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;

/**
 * Created by wchya on 2016-11-27 17:39
 */

public class MainVM extends BaseVM {

    /** Toolbar 标题 */
    public ObservableField<String> toolbarTitle = new ObservableField<>("新闻");

    private MainBinding mBinding;

    public MainVM(AppCompatActivity activity, Resources resources) {
        mActivity = activity;
        mResources = resources;
        mBinding = DataBindingUtil.setContentView(activity, R.layout.main);
    }

    /** 获取当前 Activity 的布局对应的 ViewBinding */
    public MainBinding getBinding() {
        return mBinding;
    }

    /** 初始化工具栏 */
    public void initToolbar() {
        mActivity.setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setNavigationIcon(R.mipmap.toolbar_nav);
        mBinding.toolbar.setNavigationOnClickListener(view -> mBinding.mainDrawer.openDrawer(Gravity.LEFT));
    }

    /** 初始化底部导航栏 */
    public void initBottomNavBar() {
        mBinding.bottomNavBar.addItem(new BottomNavigationItem(R.mipmap.bottom_bar_news, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.news)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_life, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.life)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_tool, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.tool)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_study, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.study)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_im, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.im)))
                .initialise();
        mBinding.bottomNavBar.setTabSelectedListener(mSelectedListener);
    }

    /** 底部导航栏标签选择事件 */
    public BottomNavigationBar.OnTabSelectedListener mSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            if (position == MainEnum.BottomBarType.news.ordinal()) { // 新闻
                toolbarTitle.set("新闻");
            } else if (position == MainEnum.BottomBarType.life.ordinal()) { // 生活
                toolbarTitle.set("生活");
            } else if (position == MainEnum.BottomBarType.tool.ordinal()) { // 工具
                toolbarTitle.set("工具");
            } else if (position == MainEnum.BottomBarType.study.ordinal()) { // 学习
                toolbarTitle.set("学习");
            } else if (position == MainEnum.BottomBarType.im.ordinal()) { // IM
                toolbarTitle.set("IM");
            }
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };

    @Override
    public void reset() {
        mActivity = null;
        mResources = null;
        mBinding = null;
    }
}

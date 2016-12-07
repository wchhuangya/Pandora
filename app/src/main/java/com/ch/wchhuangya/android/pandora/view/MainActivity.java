package com.ch.wchhuangya.android.pandora.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Gravity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.MainBinding;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;
import com.ch.wchhuangya.android.pandora.fragment.NewsFragment;
import com.ch.wchhuangya.android.pandora.vm.MainVM;

public class MainActivity extends BaseActivity {

    private MainVM mMainVM;
    private MainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main);
        mMainVM = new MainVM(this);
        mBinding.setMainVM(mMainVM);

        // 设置 Toolbar
        initToolbar();

        // 设置 BottomNavigationBar
        initBottomNavBar();

        // 设置第一屏显示的内容
        mMainVM.setMainContent(R.id.main_frame, new NewsFragment());
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.toolbar);
        mBinding.toolbar.setNavigationIcon(R.mipmap.toolbar_nav);
        mBinding.toolbar.setNavigationOnClickListener(view -> mBinding.mainDrawer.openDrawer(Gravity.LEFT));
    }

    private void initBottomNavBar() {
        mBinding.bottomNavBar.addItem(new BottomNavigationItem(R.mipmap.bottom_bar_news, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.news)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_life, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.life)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_tool, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.tool)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_study, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.study)))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_im, MainEnum.BottomBarType.getName(MainEnum.BottomBarType.im)))
                .initialise();
        mBinding.bottomNavBar.setTabSelectedListener(mSelectedListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainVM.reset();
        mMainVM.unsubscribe();
    }

    /** 底部导航栏标签选择事件 */
    public BottomNavigationBar.OnTabSelectedListener mSelectedListener = new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            if (position == MainEnum.BottomBarType.news.ordinal()) { // 新闻
                mMainVM.toolbarTitle.set("新闻");
                mMainVM.setMainContent(R.id.main_frame, new NewsFragment());
            } else if (position == MainEnum.BottomBarType.life.ordinal()) { // 生活
                mMainVM.toolbarTitle.set("生活");
            } else if (position == MainEnum.BottomBarType.tool.ordinal()) { // 工具
                mMainVM.toolbarTitle.set("工具");
            } else if (position == MainEnum.BottomBarType.study.ordinal()) { // 学习
                mMainVM.toolbarTitle.set("学习");
            } else if (position == MainEnum.BottomBarType.im.ordinal()) { // IM
                mMainVM.toolbarTitle.set("IM");
            }
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };
}

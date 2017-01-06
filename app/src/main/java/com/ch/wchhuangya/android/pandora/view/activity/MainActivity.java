package com.ch.wchhuangya.android.pandora.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.DrawerHeaderBinding;
import com.ch.wchhuangya.android.pandora.databinding.MainBinding;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;
import com.ch.wchhuangya.android.pandora.view.activity.tools.CalculatorActivity;
import com.ch.wchhuangya.android.pandora.view.fragment.CommonGridFragment;
import com.ch.wchhuangya.android.pandora.view.fragment.news.NewsFragment;
import com.ch.wchhuangya.android.pandora.vm.MainVM;
import com.ch.wchhuangya.android.pandora.vm.account.HeaderVM;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private MainVM mMainVM;
    private MainBinding mBinding;

    public static final String FRAGMENT_TAG = "FRAGMENT_TAG";
    public static final String TAG_LIFE = "TAG_LIFE";
    public static final String TAG_TOOLS = "TAG_TOOLS";
    public static final String TAG_STUDY = "TAG_STUDY";
    private DrawerHeaderBinding mHeaderBinding;
    private HeaderVM mHeaderVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.main);
        mMainVM = new MainVM(this);
        mBinding.setMainVM(mMainVM);

        // 设置左滑菜单的头部
        initHeader();

        // 设置 Toolbar
        initToolbar();

        // 设置 BottomNavigationBar
        initBottomNavBar();

        // 设置 NavigationView
        initNavigationView();

        // 设置第一屏显示的内容
        setNewsToContents();
    }

    private void initHeader() {
        mHeaderBinding = DrawerHeaderBinding.inflate((LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE));
        mHeaderVM = new HeaderVM(this);
        mHeaderVM.nickName.set("游侠一枚");
        mHeaderBinding.setHeader(mHeaderVM);

        mBinding.leftNavView.addHeaderView(mHeaderBinding.headerContainer);
        mHeaderVM.initUser();
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

    private void initNavigationView() {
        mBinding.leftNavView.setNavigationItemSelectedListener(this);
    }

    private void setNewsToContents() {
        mMainVM.setMainContent(R.id.main_frame, new NewsFragment());
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
            CommonGridFragment commonGridFragment = null;

            if (position == MainEnum.BottomBarType.news.ordinal()) { // 新闻
                mMainVM.toolbarTitle.set("新闻");
                mMainVM.setMainContent(R.id.main_frame, new NewsFragment());
            } else if (position == MainEnum.BottomBarType.life.ordinal()) { // 生活
                mMainVM.toolbarTitle.set("生活");
                commonGridFragment = getCommonGridFragment(commonGridFragment, MainActivity.TAG_LIFE);
            } else if (position == MainEnum.BottomBarType.tool.ordinal()) { // 工具
                mMainVM.toolbarTitle.set("工具");
                commonGridFragment = getCommonGridFragment(commonGridFragment, MainActivity.TAG_TOOLS);
            } else if (position == MainEnum.BottomBarType.study.ordinal()) { // 学习
                mMainVM.toolbarTitle.set("学习");
                commonGridFragment = getCommonGridFragment(commonGridFragment, MainActivity.TAG_STUDY);
            } else if (position == MainEnum.BottomBarType.im.ordinal()) { // IM
                mMainVM.toolbarTitle.set("IM");
            }

            if (commonGridFragment != null)
                mMainVM.setMainContent(R.id.main_frame, commonGridFragment);
        }

        @NonNull
        private CommonGridFragment getCommonGridFragment(CommonGridFragment fragment, String title) {
            fragment = new CommonGridFragment();
            Bundle bundle = new Bundle();
            bundle.putString(FRAGMENT_TAG, title);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                setNewsToContents();
                break;
            case R.id.nav_calculator:
                Intent intent = new Intent(MainActivity.this, CalculatorActivity.class);
                intent.putExtra("s", "ss");
                startActivity(intent);
                break;
        }
        mBinding.mainDrawer.closeDrawer(Gravity.LEFT);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case HeaderVM.REQUEST_LOGIN:
                    case HeaderVM.REQUEST_INFO:
                        mHeaderVM.setUser();
                        break;
                }
                break;
            case RESULT_CANCELED:
                switch (requestCode) {
                }
                break;
        }
    }
}

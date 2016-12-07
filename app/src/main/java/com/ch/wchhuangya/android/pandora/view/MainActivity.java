package com.ch.wchhuangya.android.pandora.view;

import android.os.Bundle;

import com.ch.wchhuangya.android.pandora.vm.MainVM;

public class MainActivity extends BaseActivity {

    private MainVM mMainVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainVM = new MainVM(this, getResources());
        mMainVM.getBinding().setMainVM(mMainVM);

        // 设置 Toolbar
        mMainVM.initToolbar();

        // 设置 BottomNavigationBar
        mMainVM.initBottomNavBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainVM.reset();
    }
}

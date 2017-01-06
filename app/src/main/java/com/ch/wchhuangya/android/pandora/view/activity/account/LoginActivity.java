package com.ch.wchhuangya.android.pandora.view.activity.account;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.LoginBinding;
import com.ch.wchhuangya.android.pandora.view.activity.BaseActivity;
import com.ch.wchhuangya.android.pandora.vm.account.LoginVM;

/**
 * Created by wchya on 2017-01-03 11:48
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    public static final int REQUEST_REGISTER = 1;
    private LoginBinding mBinding;
    private LoginVM mLoginVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.login);
        mLoginVM = new LoginVM(this, mBinding);
        mBinding.setLogin(mLoginVM);

        initToolbar();

        mLoginVM.initFloatingLabel();

        mLoginVM.initLoginBtn();
    }

    private void initToolbar() {
        setSupportActionBar(mBinding.loginToolbar);
        mBinding.loginToolbar.setNavigationIcon(R.mipmap.toolbar_back);
        mBinding.loginToolbar.setNavigationOnClickListener(this);
        getSupportActionBar().setTitle(getResources().getString(R.string.login));
    }

    @Override
    public void onClick(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_user:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                intent.putExtra("ss", "ss");
                startActivityForResult(intent, REQUEST_REGISTER);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case RESULT_OK:
                switch (requestCode) {
                    case REQUEST_REGISTER: // 注册成功，关闭页面
                        setResult(RESULT_OK);
                        finish();
                        break;
                }
                break;
            case RESULT_CANCELED:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginVM.reset();
    }
}

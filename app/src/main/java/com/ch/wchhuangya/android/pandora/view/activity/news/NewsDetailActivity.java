package com.ch.wchhuangya.android.pandora.view.activity.news;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.NewsDetailBinding;
import com.ch.wchhuangya.android.pandora.view.activity.BaseActivity;
import com.ch.wchhuangya.android.pandora.vm.news.NewsDetailVM;

/**
 * Created by wchya on 2016-12-12 11:26
 */

public class NewsDetailActivity extends BaseActivity implements View.OnClickListener {

    private NewsDetailBinding mBinding;
    private NewsDetailVM mDetailVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.news_detail);
        mDetailVM = new NewsDetailVM();
        mBinding.setDetail(mDetailVM);

        if (mDetailVM.handleIntentExtra(getIntent())) {
            setSupportActionBar(mBinding.newsDetailToolbar);
            mBinding.newsDetailToolbar.setNavigationIcon(R.mipmap.toolbar_back);
            mBinding.newsDetailToolbar.setNavigationOnClickListener(this);
            mDetailVM.loadData(mBinding.newsDetailWv);
        } else {
            Toast.makeText(NewsDetailActivity.this, "参数传递错误!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetailVM.reset();
    }
}

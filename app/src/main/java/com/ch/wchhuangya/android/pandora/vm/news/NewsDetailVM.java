package com.ch.wchhuangya.android.pandora.vm.news;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.util.Log;
import android.webkit.WebView;

import com.ch.wchhuangya.android.pandora.client.NewsHandle;
import com.ch.wchhuangya.android.pandora.model.NewsDetail;
import com.ch.wchhuangya.android.pandora.vm.BaseVM;
import com.ch.wchhuangya.lib.util.Constant;
import com.ch.wchhuangya.lib.util.StringUtil;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by wchya on 2016-12-12 17:13
 */

public class NewsDetailVM extends BaseVM {

    public ObservableField<String> toolbarTitle = new ObservableField<>();
    public ObservableField<String> newsTitle = new ObservableField<>();
    public ObservableField<String> source = new ObservableField<>();
    public ObservableLong date = new ObservableLong();
    public ObservableArrayList<String> imgUrls = new ObservableArrayList<>();
    public ObservableField<String> webviewHtml = new ObservableField<>("");
    public ObservableInt type = new ObservableInt();
    private String mTitle;
    private String mNewsId;
    private int mTableNum;

    /** 接收传入的 Intent，获取其中的参数。如果参数有 null 值，返回 false；如果没有 null 值，返回 true； */
    public boolean handleIntentExtra(Intent extra) {
        if (extra != null) {
            mTitle = extra.getStringExtra(SingleNewsVM.EXTRA_TITLE);
            mNewsId = extra.getStringExtra(SingleNewsVM.EXTRA_NEWS_ID);
            mTableNum = extra.getIntExtra(SingleNewsVM.EXTRA_TAB_NUM, -1);

            if (StringUtil.hasNull(mTitle, mNewsId) || mTableNum == -1)
                return false;
            else
                return true;
        } else {
            return false;
        }
    }

    public void loadData(WebView webView) {
        toolbarTitle.set(mTitle);
        mSubscriptions.add(Observable.timer(1, TimeUnit.SECONDS).subscribe(aLong ->
            mSubscriptions.add(NewsHandle.getNewsDetail(mNewsId, mTableNum,
                    newsDetail -> {
                        type.set(mTableNum);
                        NewsDetail.DataBean data = newsDetail.getData();
                        newsTitle.set(data.getTitle());
                        source.set(data.getSource());
                        date.set(Long.parseLong(data.getEdit_time()));
                        String html = "<div style='line-height: 2;'>" + (mTableNum == 6 ? data.getDigest() : data.getContent()) + "</div>";
                        Log.d(Constant.LOG_TAG, "loadData: 网页内容——" + html);
                        webView.loadDataWithBaseURL(null, StringUtil.adjustImgFitScreen(html), "text/html", "UTF-8", null);
                        if (StringUtil.isNotEmpty(data.getTop_image())) {
                            if (!imgUrls.contains(data.getTop_image()))
                                imgUrls.add(data.getTop_image());
                        }
                        if (StringUtil.isNotEmpty(data.getText_image0())) {
                            if (!imgUrls.contains(data.getText_image0()))
                                imgUrls.add(data.getText_image0());
                        }
                        if (StringUtil.isNotEmpty(data.getText_image1())) {
                            if (!imgUrls.contains(data.getText_image1()))
                                imgUrls.add(data.getText_image1());
                        }
                    },
                    throwable -> {
                        pbShow.set(false);
                        Log.e(Constant.LOG_TAG, "loadData: 加载新闻详情失败！", throwable);
                    },
                    () -> {
                        pbShow.set(false);
                    }
            )
        )));
    }

    @Override
    public void reset() {
        unsubscribe();
    }
}

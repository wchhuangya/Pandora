package com.ch.wchhuangya.lib.binding;

import android.databinding.BindingAdapter;
import android.text.TextUtils;
import android.webkit.WebView;

/**
 * Created by wchya on 2016-12-12 17:51
 */

public class WebViewBinding {
    @BindingAdapter({"render"})
    public static void loadHTML(WebView webView, String html) {
        if (!TextUtils.isEmpty(html)) {
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
        }
    }
}

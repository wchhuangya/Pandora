package com.ch.wchhuangya.lib.binding;

import android.databinding.BindingAdapter;
import android.view.ViewGroup;

/**
 * Created by wchya on 2016-12-12 11:35
 */

public class MarginBinding {
    @BindingAdapter("android:layout_marginTop")
    public static void setViewGroupMarginTop(ViewGroup view, float marginTop) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.topMargin = (int) marginTop;
        view.setLayoutParams(layoutParams);
    }
}

package com.ch.wchhuangya.lib.binding;

import android.databinding.BindingAdapter;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ch.wchhuangya.lib.imageloader.ImageLoaderUtil;

/**
 * Created by wchya on 2016-11-28 16:07
 */

public class DataBindingUtil {
    @BindingAdapter("android:layout_marginTop")
    public static void setViewGroupMarginTop(ViewGroup view, float marginTop) {
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.topMargin = (int) marginTop;
        view.setLayoutParams(layoutParams);
    }
    @BindingAdapter("bind:squareImg")
    public static void setSquareImg(ImageView imageView, String url) {
        ImageLoaderUtil.displaySquareImage(imageView, url);
    }
}

package com.ch.wchhuangya.lib.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.ch.wchhuangya.lib.imageloader.ImageLoaderUtil;

/**
 * Created by wchya on 2016-12-12 11:35
 */

public class ImageBinding {
    @BindingAdapter({"squareImg"})
    public static void setSquareImg(ImageView imageView, String url) {
        ImageLoaderUtil.displaySquareImage(imageView, url);
    }
}
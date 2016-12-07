package com.ch.wchhuangya.lib.imageloader;

import android.widget.ImageView;

import com.ch.wchhuangya.lib.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.CircleBitmapDisplayer;

/**
 * Created by wchya on 2016-11-28 21:34
 */

public class ImageLoaderUtil {

    private ImageLoaderUtil() {
        throw new UnsupportedOperationException("该类不能被实例化~");
    }

    public static DisplayImageOptions squareOptions = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.mipmap.no_img)
            .showImageOnFail(R.mipmap.pic_fail)
            .showImageOnLoading(R.mipmap.pic_default)
            .build();

    public static DisplayImageOptions circleOptions = new DisplayImageOptions.Builder()
            .displayer(new CircleBitmapDisplayer())
            .build();

    /** 显示方形图片的方法 */
    public static void displaySquareImage(ImageView imageView, String url) {
        ImageLoader.getInstance().displayImage(url, imageView, squareOptions);
    }

    /** 显示方形图片的方法 */
    public static void displayCircleImage(ImageView imageView, String url) {
        ImageLoader.getInstance().displayImage(url, imageView, circleOptions);
    }
}

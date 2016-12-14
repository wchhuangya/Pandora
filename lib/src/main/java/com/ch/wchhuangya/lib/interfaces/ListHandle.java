package com.ch.wchhuangya.lib.interfaces;

import android.view.View;

/**
 * Created by wchya on 2016-12-12 11:37
 */

public interface ListHandle {
    /**
     * 列表元素点击事件
     * @param view 点击的 View
     * @param object View 上绑定的数据
     * @param position 点击的位置
     */
    void onItemClick(View view, Object object, int position);
}

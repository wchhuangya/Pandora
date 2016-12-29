package com.ch.wchhuangya.android.pandora.vm;

import android.app.Activity;
import android.content.Context;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableMap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.adapter.FailCommonGridAdapter;
import com.ch.wchhuangya.android.pandora.model.FailCommonGrid;
import com.ch.wchhuangya.lib.util.ScreenUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wchya on 2016-12-23 10:48
 */

public class FailCommonGridVM extends BaseVM {
    public List<Map<String, Object>> mData = new ArrayList<>();

    public ObservableArrayList<ObservableMap<String, Object>> appDatas = new ObservableArrayList<>();

    public FailCommonGridVM(Context context) {
        mContext = context;
    }

    public void initLifeAppData() {
        Map<String, Object> map = new HashMap<>();

        map.put(FailCommonGrid.APP_IMG_URL, R.mipmap.calculator);
        map.put(FailCommonGrid.APP_NAME, mContext.getResources().getString(R.string.calculator));

        mData.add(map);
    }

    public void initToolsAppData() {
        Map<String, Object> map = new HashMap<>();

        map.put(FailCommonGrid.APP_IMG_URL, R.mipmap.calculator);
        map.put(FailCommonGrid.APP_NAME, mContext.getResources().getString(R.string.calculator));

        mData.add(map);
    }

    public void initStudyAppData() {
        Map<String, Object> map = new HashMap<>();

        map.put(FailCommonGrid.APP_IMG_URL, R.mipmap.calculator);
        map.put(FailCommonGrid.APP_NAME, mContext.getResources().getString(R.string.calculator));

        mData.add(map);
    }

    public List<Map<String, Object>> getData() {
        return mData;
    }

    public void initRecyclerView(Activity activity, RecyclerView grid) {
        int mWidth = ScreenUtil.getScreenWidth(activity);
        int spaceCount = mWidth / ScreenUtil.dp2px(activity, 80f);

        grid.setLayoutManager(new GridLayoutManager(mContext, spaceCount));
        grid.setAdapter(new FailCommonGridAdapter(mData));
    }

    @Override
    public void reset() {
        mContext = null;
        unsubscribe();
    }
}

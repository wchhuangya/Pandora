package com.ch.wchhuangya.android.pandora.vm;

import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import com.ch.wchhuangya.android.pandora.adapter.SingleNewsAdapter;
import com.ch.wchhuangya.android.pandora.client.NewsHandle;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;
import com.ch.wchhuangya.android.pandora.model.NewsList;
import com.ch.wchhuangya.lib.util.Constant;

import java.util.List;

/**
 * Created by wchya on 2016-11-28 14:51
 */

public class SingleNewsVM extends BaseVM {

    public void loadData(int pos, SingleNewsAdapter adapter, int insertPos, SwipeRefreshLayout refreshLayout) {
        mSubscriptions.add(NewsHandle.getNewsList(MainEnum.NewsTitle.getValueByPos(pos), NewsHandle.PAGE, NewsHandle.PAGE_SIZE,
                newsList -> {
                    List<NewsList.DataBean> data = newsList.getData();
                    adapter.setData(data, insertPos, data.size());
                }, throwable -> {
                    Log.e(Constant.LOG_TAG, "loadData: 加载数据失败！", throwable);
                }, () -> {
                    pbShow.set(false);
                    listShow.set(true);
                    refreshLayout.setEnabled(true);
                    refreshLayout.setRefreshing(false);
                }));
    }

    public void refresh(int pos, SingleNewsAdapter adapter, SwipeRefreshLayout refreshLayout) {
        adapter.clearData();
        loadData(pos, adapter, 0, refreshLayout);
    }

    @Override
    public void reset() {
        mActivity = null;
    }
}

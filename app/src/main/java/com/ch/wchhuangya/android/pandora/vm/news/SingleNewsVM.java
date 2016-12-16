package com.ch.wchhuangya.android.pandora.vm.news;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ch.wchhuangya.android.pandora.adapter.SingleNewsAdapter;
import com.ch.wchhuangya.android.pandora.client.NewsHandle;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;
import com.ch.wchhuangya.android.pandora.model.NewsList;
import com.ch.wchhuangya.android.pandora.model.SingleNews;
import com.ch.wchhuangya.android.pandora.view.activity.news.NewsDetailActivity;
import com.ch.wchhuangya.android.pandora.vm.BaseVM;
import com.ch.wchhuangya.lib.recyclerview.ItemClickListener;
import com.ch.wchhuangya.lib.util.Constant;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by wchya on 2016-11-28 14:51
 */

public class SingleNewsVM extends BaseVM {

    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_NEWS_ID = "news_id";
    public static final String EXTRA_TAB_NUM = "tableNum";

    private int page = 1;
    private boolean loading;

    public SingleNewsVM(Context context) {
        mContext = context;
    }

    public void loadData(int pos, SingleNewsAdapter adapter, int insertPos, SwipeRefreshLayout refreshLayout) {
        mSubscriptions.add(NewsHandle.getNewsList(MainEnum.NewsTitle.getValueByPos(pos), page, NewsHandle.PAGE_SIZE,
                newsList -> {
                    List<NewsList.DataBean> data = newsList.getData();
                    adapter.setData(data, insertPos, data.size());
                }, throwable -> {
                    Log.e(Constant.LOG_TAG, "loadData: 加载数据失败！", throwable);
                }, () -> {
                    pbShow.set(false);
                    listShow.set(true);
                    loading = false;
                    refreshLayout.setEnabled(true);
                    refreshLayout.setRefreshing(false);
                }));
    }

    public void refresh(int pos, SingleNewsAdapter adapter, SwipeRefreshLayout refreshLayout) {
        page = 1;
        adapter.clearData();
        loadData(pos, adapter, 0, refreshLayout);
    }

    public RecyclerView.OnItemTouchListener onItemTouchClick(RecyclerView recyclerview, int pos, SingleNewsAdapter adapter, SwipeRefreshLayout refreshLayout) {
        return new ItemClickListener(recyclerview, new ItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mSubscriptions.add(Observable.timer(200, TimeUnit.MILLISECONDS)
                        .throttleFirst(2, TimeUnit.SECONDS)
                        .subscribe(
                            aLong -> {
                                String newsId = ((SingleNews)view.getTag()).newsId.get();
                                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                                intent.putExtra(EXTRA_TITLE, MainEnum.NewsTitle.getTitleByPos(pos));
                                intent.putExtra(EXTRA_NEWS_ID, newsId);
                                intent.putExtra(EXTRA_TAB_NUM, Integer.parseInt(MainEnum.NewsTitle.getValueByPos(pos)));
                                mContext.startActivity(intent);
                            }
                        )
                );
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    public RecyclerView.OnScrollListener onScrollListener(int pos, SingleNewsAdapter adapter, SwipeRefreshLayout refreshLayout) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                if (!loading && itemCount > 0 && (itemCount - 1) <= lastVisibleItemPosition) {
                    loading = true;
                    page++;
                    loadData(pos, adapter, adapter.getData().size(), refreshLayout);
                }
            }
        };
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public void reset() {
        unsubscribe();
    }
}

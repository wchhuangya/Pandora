package com.ch.wchhuangya.android.pandora.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.SingleNewsItemBinding;
import com.ch.wchhuangya.android.pandora.model.NewsList;
import com.ch.wchhuangya.android.pandora.model.SingleNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wchya on 2016-11-28 21:11
 */

public class SingleNewsAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<NewsList.DataBean> mData = new ArrayList<>();
    private Context mContext;

    public SingleNewsAdapter(Context context) {
        mContext = context;
    }

    /** 把变化的数据填充到数据集的相应位置，并刷新适配器 */
    public void setData(List<NewsList.DataBean> data, int start, int count) {
        mData.addAll(start, data);
        notifyItemRangeInserted(start, count);
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SingleNewsItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_news_item, parent, false);
        SingleNews singleNews = new SingleNews();
        binding.setSingleNews(singleNews);
        return new ViewHolder(binding, singleNews);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SingleNews singleNews = (SingleNews) holder.getBinding().getRoot().getTag();
        NewsList.DataBean dataBean = mData.get(position);
        singleNews.imgUrl.set(dataBean.getTop_image());
        singleNews.newsTitle.set(dataBean.getTitle());
        singleNews.newsSummary.set(dataBean.getDigest());
        singleNews.newsId.set(dataBean.getNews_id());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

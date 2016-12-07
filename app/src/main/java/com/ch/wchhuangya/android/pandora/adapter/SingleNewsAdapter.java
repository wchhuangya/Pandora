package com.ch.wchhuangya.android.pandora.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.BR;
import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.model.NewsList;
import com.ch.wchhuangya.android.pandora.model.SingleNews;

import java.util.List;

/**
 * Created by wchya on 2016-11-28 21:11
 */

public class SingleNewsAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<NewsList.DataBean> mData;

    /** 把变化的数据填充到数据集的相应位置，并刷新适配器 */
    public void setData(List<NewsList.DataBean> data, int start, int count) {
        mData.addAll(start, data);
        notifyItemRangeInserted(start, count);
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.single_news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.singleNews, new SingleNews());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

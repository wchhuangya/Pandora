package com.ch.wchhuangya.android.pandora.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.databinding.FailCommonGridBinding;
import com.ch.wchhuangya.android.pandora.model.FailCommonGrid;
import com.ch.wchhuangya.lib.recyclerview.ViewHolder;

import java.util.List;
import java.util.Map;

/**
 * Created by wchya on 2016-12-28 16:35
 */

public class FailCommonGridAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Map<String, Object>> mData;

    public FailCommonGridAdapter(List<Map<String, Object>> data) {
        mData = data;
    }

    public void setData(List<Map<String, Object>> data) {
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        FailCommonGridBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.fail_common_grid, parent, false);
        return new ViewHolder(binding, new FailCommonGrid());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FailCommonGrid failCommonGrid = (FailCommonGrid) holder.getBinding().getRoot().getTag();
        failCommonGrid.appIcon.set(mData.get(position).get(FailCommonGrid.APP_IMG_URL).toString());
        failCommonGrid.appName.set(mData.get(position).get(FailCommonGrid.APP_NAME).toString());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

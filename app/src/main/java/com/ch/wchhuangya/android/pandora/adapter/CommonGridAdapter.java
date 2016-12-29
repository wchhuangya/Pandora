package com.ch.wchhuangya.android.pandora.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.view.fragment.CommonGridFragment;

import java.util.List;
import java.util.Map;

/**
 * Created by wchya on 2016-12-29 15:38
 */

public class CommonGridAdapter extends RecyclerView.Adapter<CommonGridAdapter.ViewHolder> {
    private List<Map<String, Object>> mData;
    private Context mContext;

    public CommonGridAdapter(Context context, List<Map<String, Object>> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_grid_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mIv.setBackgroundResource(Integer.parseInt(mData.get(position).get(CommonGridFragment.APP_IMG_URL).toString()));
        holder.mTv.setText(mData.get(position).get(CommonGridFragment.APP_NAME).toString());
        holder.mIv.setTag(position);
        holder.mIv.setOnClickListener(view -> {
            int p = Integer.parseInt(view.getTag().toString());
            Intent intent = new Intent(mContext, (Class<?>) mData.get(p).get(CommonGridFragment.APP_URL));
            intent.putExtra("s", "s");
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mIv;
        public TextView mTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mIv = (ImageView) itemView.findViewById(R.id.grid_item_pic);
            mTv = (TextView) itemView.findViewById(R.id.grid_item_tv);
        }
    }
}

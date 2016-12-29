package com.ch.wchhuangya.android.pandora.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ch.wchhuangya.android.pandora.R;
import com.ch.wchhuangya.android.pandora.adapter.CommonGridAdapter;
import com.ch.wchhuangya.android.pandora.view.activity.MainActivity;
import com.ch.wchhuangya.android.pandora.view.activity.calculator.CalculatorActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wchya on 2016-12-29 15:15
 */

public class CommonGridFragment extends BaseFragment {

    private String mType;
    private List<Map<String, Object>> mData = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private LinearLayout mTipsLl;

    public static final String APP_IMG_URL = "APP_IMG_URL";
    public static final String APP_NAME = "APP_NAME";
    public static final String APP_URL = "APP_URL";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString(MainActivity.FRAGMENT_TAG, "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_grid, container, false);
        switch (mType) {
            case MainActivity.TAG_LIFE:

                break;
            case MainActivity.TAG_TOOLS:
                initLifeAppData();
                break;
            case MainActivity.TAG_STUDY:

                break;
        }

        mRecyclerView = (RecyclerView) view.findViewById(R.id.grid_recyclerview);
        if (mData.size() > 0) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
            mRecyclerView.setAdapter(new CommonGridAdapter(getContext(), mData));
        } else {
            mTipsLl = (LinearLayout) view.findViewById(R.id.grid_ll);
            mRecyclerView.setVisibility(View.GONE);
            mTipsLl.setVisibility(View.VISIBLE);
        }
        return view;
    }

    public void initLifeAppData() {
        Map<String, Object> map = new HashMap<>();

        map.put(CommonGridFragment.APP_IMG_URL, R.mipmap.calculator);
        map.put(CommonGridFragment.APP_NAME, getContext().getResources().getString(R.string.calculator));
        map.put(CommonGridFragment.APP_URL, CalculatorActivity.class);

        mData.add(map);
    }
}

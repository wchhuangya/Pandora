package com.ch.wchhuangya.android.pandora.vm;

import android.content.Context;
import android.databinding.ObservableBoolean;

/**
 * Created by wchya on 2016-11-28 14:51
 */

public class CommonRecyclerViewVM extends BaseVM {

    public ObservableBoolean searchShow = new ObservableBoolean(false);
    public ObservableBoolean listShow = new ObservableBoolean(false);
    public ObservableBoolean pbShow = new ObservableBoolean(false);
    public ObservableBoolean tipsShow = new ObservableBoolean(false);

    public CommonRecyclerViewVM(Context context) {
    }

    @Override
    public void reset() {
        mActivity = null;
    }
}

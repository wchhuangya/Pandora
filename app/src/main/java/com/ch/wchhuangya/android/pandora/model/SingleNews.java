package com.ch.wchhuangya.android.pandora.model;

import android.databinding.ObservableField;

/**
 * Created by wchya on 2016-11-29 11:04
 */

public class SingleNews {
    public ObservableField<String> imgUrl = new ObservableField<>("");
    public ObservableField<String> newsTitle = new ObservableField<>("");
    public ObservableField<String> newsSummary = new ObservableField<>("");
}

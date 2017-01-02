package com.ch.wchhuangya.android.pandora.client;

import com.ch.wchhuangya.lib.interfaces.ResponseComplete;
import com.ch.wchhuangya.lib.interfaces.ResponseError;
import com.ch.wchhuangya.lib.interfaces.ResponseSuccess;
import com.ch.wchhuangya.lib.retrofit.RetrofitUtil;
import com.ch.wchhuangya.lib.rxandroid.RxandroidUtil;
import com.google.gson.JsonObject;

/**
 * Created by wchya on 2017-01-02 10:12
 */

public class IdCardInfoHandle {

    private static IdCardInfoService idCardInfoService;

    /**
     * 根据身份证号获取身份证信息
     * @param id 身份证号
     * @param success 成功后执行的回调
     * @param error 失败后执行的回调
     * @param complete 完成后执行的回调
     */
    public static void idCardInfoHandle(String id, ResponseSuccess<JsonObject> success, ResponseError error, ResponseComplete complete) {
        init();
        idCardInfoService.getIdCardInfo(id)
                .compose(RxandroidUtil.applySchedulers())
                .subscribe(success::onSuccess, error::onError, complete::onComplete);
    }

    private static void init() {
        RetrofitUtil.builder.baseUrl("http://apis.baidu.com/");
        idCardInfoService = RetrofitUtil.generator(IdCardInfoService.class);
    }
}

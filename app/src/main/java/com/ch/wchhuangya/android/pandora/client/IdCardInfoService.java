package com.ch.wchhuangya.android.pandora.client;

import com.google.gson.JsonObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by wchya on 2017-01-02 10:06
 */

public interface IdCardInfoService {
    /**
     * 根据身份证号查询身份证基本信息
     * 注意：范型使用 JsonObject 的原因是——
     * 输入正确的身份证号和不正确的身份证号查询时，返回的 json 字符串格式不一致。
     * 正确情况下返回：{"errNum":0,"retMsg":"success","retData":{"address":"XXXXXXX","sex":"M","birthday":"XXXXXX"}}
     * 错误情况下返回：{"errNum":-1,"retMsg":"身份证号码不合法!","retData":[]}
     * @param id 要查询的身份证号
     */
    @GET("apistore/idservice/id")
    Observable<JsonObject> getIdCardInfo(@Query("id") String id);
}

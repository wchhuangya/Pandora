package com.ch.wchhuangya.android.pandora.client;

import com.ch.wchhuangya.android.pandora.model.NewsDetail;
import com.ch.wchhuangya.android.pandora.model.NewsList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 新闻与服务端交互的接口
 * Created by wchya on 2016-11-26 17:06
 */

public interface NewsService {

    /**
     * 获取新闻列表
     * @param tableNum 板块代码：1 => 头条，2 => 娱乐，3 => 军事，4 => 汽车，5 => 财经，6 => 笑话，7 => 体育，8 => 科技
     * @param page 当前页（从 1 开始）
     * @param pageSize 每页显示数目
     */
    @GET("news/get-news")
    Observable<NewsList> getNewsList(@Query("tableNum") String tableNum, @Query("page") int page, @Query("pageSize") int pageSize);

    /**
     * 获取新闻详情
     * @param news_id 新闻 id
     * @param tableNum 板块代码：1 => 头条，2 => 娱乐，3 => 军事，4 => 汽车，5 => 财经，6 => 笑话，7 => 体育，8 => 科技
     */
    @GET("news/single-news")
    Observable<NewsDetail> getNewsDetail(@Query("news_id") String news_id, @Query("tableNum") int tableNum);
}

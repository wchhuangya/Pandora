package com.ch.wchhuangya.android.pandora.client;

import com.ch.wchhuangya.android.pandora.model.NewsDetail;
import com.ch.wchhuangya.android.pandora.model.NewsList;
import com.ch.wchhuangya.lib.interfaces.ResponseError;
import com.ch.wchhuangya.lib.interfaces.ResponseSuccess;
import com.ch.wchhuangya.lib.retrofit.RetrofitUtil;
import com.ch.wchhuangya.lib.rxandroid.RxandroidUtil;

/**
 * 新闻接口的处理类
 * Created by wchya on 2016-11-26 17:20
 */

public class NewsHandle {

    private static NewsService newsService = RetrofitUtil.generator(NewsService.class);

    /**
     * 获取新闻列表
     * @param tableNum 板块代码：1 => 头条，2 => 娱乐，3 => 军事，4 => 汽车，5 => 财经，6 => 笑话，7 => 体育，8 => 科技
     * @param page 当前页（从 1 开始）
     * @param pageSize 每页显示数目
     */
    public static void getNewsList(String tableNum, int page, int pageSize, ResponseSuccess<NewsList> success, ResponseError error) {
        newsService.getNewsList(tableNum, page, pageSize)
                    .compose(RxandroidUtil.applySchedulers())
                    .subscribe(success::onSuccess, error::onError);
    }

    /**
     * 获取新闻详情
     * @param news_id 新闻 id
     * @param tableNum 板块代码：1 => 头条，2 => 娱乐，3 => 军事，4 => 汽车，5 => 财经，6 => 笑话，7 => 体育，8 => 科技
     */
    public static void getNewsDetail(String news_id, int tableNum, ResponseSuccess<NewsDetail> success, ResponseError error) {
        newsService.getNewsDetail(news_id, tableNum)
                    .compose(RxandroidUtil.applySchedulers())
                    .subscribe(success::onSuccess, error::onError);
    }
}

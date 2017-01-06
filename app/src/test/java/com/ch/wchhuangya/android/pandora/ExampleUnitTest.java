package com.ch.wchhuangya.android.pandora;

import com.ch.wchhuangya.android.pandora.client.NewsHandle;
import com.ch.wchhuangya.android.pandora.enums.MainEnum;
import com.ch.wchhuangya.android.pandora.enums.RestError;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    
    @Test
    public void testGetNewsList() throws Exception {
        NewsHandle.getNewsList("1", 1, 20, newsList -> {
            System.out.println("数量：" + newsList.getCount() + "，标题1：" + newsList.getData().get(0).getTitle()
                    + "，图片1：" + newsList.getData().get(0).getTop_image());
            assertEquals(3, 2 + 1);
        }, throwable -> {
            System.out.println("出问题啦：" + throwable.getMessage());
            assertEquals(4, 2 + 1);
        }, () -> {

        });

    }

    @Test
    public void inputEnumValues() throws Exception {
        System.out.println(MainEnum.BottomBarType.im.ordinal());
        System.out.println(MainEnum.BottomBarType.getName(MainEnum.BottomBarType.news));
        System.out.println("包含元素数：" + MainEnum.NewsTitle.values().length);
    }

    @Test
    public void testRestError() throws Exception {
        System.out.println(RestError.getErrorDes("101"));
    }
}
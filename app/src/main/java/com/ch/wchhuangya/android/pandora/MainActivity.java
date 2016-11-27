package com.ch.wchhuangya.android.pandora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ch.wchhuangya.android.pandora.client.NewsHandle;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mBottomBar = (BottomNavigationBar) findViewById(R.id.main_bottom_nav_bar);

        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.bottom_bar_news, "新闻"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_life, "生活"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_tool, "工具"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_study, "学习"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_im, "IM"))
                .initialise();


        mBottomBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (position == 0)
                NewsHandle.getNewsList("1", 1, 20, newsList -> {
                    System.out.println("数量：" + newsList.getCount() + "，标题1：" + newsList.getData().get(0).getTitle()
                            + "，图片1：" + newsList.getData().get(0).getTop_image());
                }, throwable -> {
                    System.out.println("出问题啦：" + throwable.getMessage());
                });
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });
    }
}

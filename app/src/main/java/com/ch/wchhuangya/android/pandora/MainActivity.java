package com.ch.wchhuangya.android.pandora;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.ch.wchhuangya.lib.util.StringUtil;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationBar mBottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Toast.makeText(this, "测试结果：" + StringUtil.isEmpty(""), Toast.LENGTH_SHORT).show();

        mBottomBar = (BottomNavigationBar) findViewById(R.id.main_bottom_nav_bar);

        mBottomBar.addItem(new BottomNavigationItem(R.mipmap.bottom_bar_news, "新闻"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_life, "生活"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_tool, "工具"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_study, "学习"))
                .addItem(new BottomNavigationItem(R.mipmap.bottom_bar_im, "IM"))
                .initialise();


    }
}

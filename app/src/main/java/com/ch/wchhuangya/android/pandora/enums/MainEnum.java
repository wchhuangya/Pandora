package com.ch.wchhuangya.android.pandora.enums;

/**
 * 主界面用到的
 * Created by wchya on 2016-11-27 18:24
 */

public class MainEnum {

    /** 底部工具栏 */
    public enum BottomBarType {
        news("新闻"),
        life("生活"),
        tool("工具"),
        study("学习"),
        im("IM");

        private String name;

        BottomBarType(String name) {
            this.name = name;
        }

        public static String getName(BottomBarType bottomBarType) {
            return bottomBarType.name;
        }
    }

    /** 新闻分类 */
    public enum NewsTitle {
        topic(1, "头条"),
        entertainment(2, "娱乐"),
        military(3, "军事"),
        car(4, "汽车"),
        money(5, "财经"),
        joke(6, "笑话"),
        sports(7, "体育"),
        sciencetechnology(8, "科技");

        private int value;
        private String name;

        NewsTitle(int value, String name) {
            this.value = value;
            this.name = name;
        }

        public static int getValue(NewsTitle newsTitle) {
            return newsTitle.value;
        }

        public static String getName(NewsTitle newsTitle) {
            return newsTitle.name;
        }

        /**
         * 根据位置（ViewPager 中的位置）返回相应的标
         */
        public static String getTitleByPos(int pos) {
            for (NewsTitle news : NewsTitle.values()) {
                if (news.ordinal() == pos)
                    return news.name;
            }
            return NewsTitle.topic.name;
        }
    }
}

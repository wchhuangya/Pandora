package com.ch.wchhuangya.android.pandora.enums;

/**
 * 主界面乃至的
 * Created by wchya on 2016-11-27 18:24
 */

public class MainEnum {

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
}

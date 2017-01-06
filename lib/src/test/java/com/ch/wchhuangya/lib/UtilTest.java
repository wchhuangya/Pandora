package com.ch.wchhuangya.lib;

import com.ch.wchhuangya.lib.util.MD5Util;

import org.junit.Test;

/**
 * Created by wchya on 2017-01-05 11:16
 */

public class UtilTest {
    @Test
    public void testGenerateMd5() throws Exception {
        MD5Util md5Util = new MD5Util();
        System.out.println(md5Util.getMD5ofStr("8228353sx"));
    }
}

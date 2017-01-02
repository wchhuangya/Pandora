package com.ch.wchhuangya.lib;

import com.ch.wchhuangya.lib.util.RegularUtil;

import org.junit.Test;

import static org.junit.Assert.*;

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
    public void testIDCardNumber() throws Exception {
        assertTrue(RegularUtil.validIDCard("62282119850416000X"));
    }
}
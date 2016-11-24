package com.baguix.utils.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import static org.junit.Assert.assertEquals;

/**
 * Created by Scott on 2016/8/25.
 */
public class ChineseToolTest {
    private static final Logger logger = LoggerFactory.getLogger(ChineseToolTest.class);
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: escapeChinese(String str)
     */
    @Test
    public void testEscapeChinese() throws Exception{
        String result = ChineseTool.escapeChinese("中文1234 abcd[]()<+>,.~\\");
        assertEquals("%u4E2D%u65871234+abcd%5B%5D()%3C%2B%3E%2C.~%5C",result);
    }

    /**
     * Method: unEscapeChinese(String str)
     */
    @Test
    public void testUnEscapeChinese() throws Exception{
        String result = ChineseTool.unescapeChinese("%u4E2D%u65871234+abcd%5B%5D()%3C%2B%3E%2C.~%5C");
        assertEquals("中文1234 abcd[]()<+>,.~\\",result);
    }

    /**
     * Method: unEscapeChinese(String str)
     */
    @Test
    public void testToSimplified() throws Exception{
        String result = ChineseTool.toSimplified("中國萬歲");
        assertEquals("中国万岁",result);
    }
    /**
     * Method: unEscapeChinese(String str)
     */
    @Test
    public void testToTraditional() throws Exception{
        String result = ChineseTool.toTraditional("中国万岁");
        assertEquals("中國萬歲",result);
    }
    /**
     * Method: getPinYinFirst(String chinese)
     */
    @Test
    public void testGetPinYinFirst() throws Exception{
        String result = ChineseTool.getPinYinFirst("中国万岁");
        assertEquals("zgws",result);
        result = ChineseTool.getPinYinFirst("中國萬歲");
        assertEquals("zgws",result);
        result = ChineseTool.getPinYinFirst("西安");
        assertEquals("xa",result);
    }
    /**
     * Method: getPinYin(String chinese)
     */
    @Test
    public void testGetPinYin() throws Exception{
        String result = ChineseTool.getPinYin("中国万岁");
        assertEquals("zhong guo wan sui",result);
        result = ChineseTool.getPinYin("中國萬歲");
        assertEquals("zhong guo wan sui",result);
        result = ChineseTool.getPinYin("西安");
        assertEquals("xi an",result);
    }
    /**
     * Method: getPinYinU(String chinese)
     */
    @Test
    public void testGetPinYinU() throws Exception{
        String result = ChineseTool.getPinYinU("中国万岁");
        assertEquals("zhong1 guo2 wan4 sui4",result);
        result = ChineseTool.getPinYinU("中國萬歲");
        assertEquals("zhong1 guo2 wan4 sui4",result);
        result = ChineseTool.getPinYinU("西安");
        assertEquals("xi1 an1",result);
    }

    /**
     * Method: getPinYinTone(String chinese)
     */
    @Test
    public void testGetPinYinTone() throws Exception{
        String result = ChineseTool.getPinYinTone("中国万岁");
        assertEquals("zhong1 guo2 wan4 sui4",result);
        result = ChineseTool.getPinYinTone("中國萬歲");
        assertEquals("zhong1 guo2 wan4 sui4",result);
        result = ChineseTool.getPinYinTone("西安");
        assertEquals("xi1 an1",result);
    }
}
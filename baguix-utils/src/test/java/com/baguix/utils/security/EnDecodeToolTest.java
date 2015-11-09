package com.baguix.utils.security;

import com.baguix.utils.data.Constants;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* EnDecodeTool Tester. 
* 
* @author Scott(SG)
*/ 
public class EnDecodeToolTest { 
    private static final Logger logger = LoggerFactory.getLogger(EnDecodeToolTest.class);
    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
    /**
     * 
     * Method: Native2AscII(String str) 
     * 
     */ 
    @Test
    public void testNative2AscII() throws Exception {
        String exp = EnDecodeTool.Native2AscII("baguix工作室");
        String act = "baguix\\u5DE5\\u4F5C\\u5BA4";
        assertEquals(exp,act);
    } 
    
    /**
     * 
     * Method: AscII2Native(String str) 
     * 
     */ 
    @Test
    public void testAscII2Native() throws Exception {
        String exp = EnDecodeTool.AscII2Native("baguix\\u5DE5\\u4F5C\\u5BA4");
        String act = "baguix工作室";
        assertEquals(exp,act);
    } 
    
    /**
     * 
     * Method: Str2Unicode(String str) 
     * 
     */ 
    @Test
    public void testStr2Unicode() throws Exception {
        String exp = EnDecodeTool.Str2Unicode("baguix工作室");
        String act = "\\u0062\\u0061\\u0067\\u0075\\u0069\\u0078\\u5DE5\\u4F5C\\u5BA4";
        assertEquals(exp,act);
    } 
    
    /**
     * 
     * Method: Unicode2Str(String str) 
     * 
     */ 
    @Test
    public void testUnicode2Str() throws Exception {
        String exp = EnDecodeTool.Unicode2Str("\\u0062\\u0061\\u0067\\u0075\\u0069\\u0078\\u5DE5\\u4F5C\\u5BA4");
        String act = "baguix工作室";
        assertEquals(exp,act);
    }
    /**
     *
     * Method: oldURIEncoding(String str)
     *
     */
    @Test
    public void testOldURIEncoding() throws Exception {
        String exp = EnDecodeTool.oldURIEncoding("baguix 工作室");
        String act = "baguix+%E5%B7%A5%E4%BD%9C%E5%AE%A4";
        assertEquals(exp,act);
    }
    /**
     *
     * Method: URIEncoding(String str)
     *
     */
    @Test
    public void testURIEncoding() throws Exception {
        String act = EnDecodeTool.URIEncoding("baguix 工作室", Constants.UTF8);
        String exp = "baguix%20%E5%B7%A5%E4%BD%9C%E5%AE%A4";
        assertEquals(act,exp);
        String act1 = EnDecodeTool.URIEncoding(" /?:@&=+$,", Constants.UTF8);
        String exp1 = "%20%2F%3F%3A%40%26%3D%2B%24%2C";
        assertEquals(act1,exp1);
    }
} 

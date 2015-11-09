package com.baguix.utils.security;

import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* StringCompressor Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 25, 2015</pre> 
* @version 1.0 
*/ 
public class StringCompressorTest {
    private static final Logger logger = LoggerFactory.getLogger(StringCompressorTest.class);
    @Before
    public void before() throws Exception {
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
    /**
     * Method: gzip(String str)
     */
    @Test
    public void testGzip() throws Exception {
        String compress = StringCompressor.gzip("中文abc123@！");
        assertEquals("H4sIAAAAAAAAAHuyY+2zae2JScmGRsYO7/c0AgDZDKBiEAAAAA==",compress);
    }

    /**
     * Method: ungzip(String str)
     */
    @Test
    public void testUngzip() throws Exception {
        String str = StringCompressor.ungzip("H4sIAAAAAAAAAHuyY+2zae2JScmGRsYO7/c0AgDZDKBiEAAAAA==");
        assertEquals("中文abc123@！",str);
    }
} 

package com.baguix.utils.file;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* CmdTool Tester. 
* 
* @author <Authors name> 
* @since <pre>八月 26, 2015</pre> 
* @version 1.0 
*/ 
public class CmdToolTest {
    private static final Logger logger = LoggerFactory.getLogger(CmdToolTest.class);
    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
    /**
     * Method: exec(String command)
     */
    @Ignore
    public void testExec() throws Exception {
        String OS = System.getProperty("os.name").toLowerCase();
        String msg;
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            msg = CmdTool.exec("ping www.baidu.com");
        } else {
            msg = CmdTool.exec("ping -c 3 www.baidu.com");
        }
        logger.info(msg);
    }

} 

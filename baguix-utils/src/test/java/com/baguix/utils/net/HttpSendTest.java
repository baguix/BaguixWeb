package test.com.baguix.utils.net; 

import com.baguix.utils.net.HttpSend;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* HttpSend Tester. 
* 
* @author Scott(SG)
*/ 
public class HttpSendTest { 
    private static final Logger logger = LoggerFactory.getLogger(HttpSendTest.class);
    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
        /** 
     * 
     * Method: send(String str) 
     * 
     */ 
    @Test
    public void testSend() throws Exception { 
        HttpSend httpSend = new HttpSend();
        String result = httpSend.send("http://www.baidu.com?w=123");
    } 
    
        
    } 

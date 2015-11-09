package com.baguix.utils.net;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* EmailTool Tester. 
* 
* @author Scott(SG)
*/ 
public class EmailToolTest { 
    private static final Logger logger = LoggerFactory.getLogger(EmailToolTest.class);
    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 
    
    /**
     * 
     * Method: sendSimple()
     * 
     */
    @Ignore
    public void testSend() throws Exception { 
        EmailTool et = new EmailTool();
        EmailBean eb = new EmailBean();
        eb.setMailTo("8555323@qq.com");
        eb.setMailSubject("测试邮件");
        eb.setMailContent("<span style='color:red'>非常给力</span>");
        et.send(eb);
    }
    /**
     *
     * Method: writeConfig()
     *
     */
    @Ignore
    public void testWriteConfig() throws Exception {
        EmailTool et = new EmailTool();
        et.writeConfig("smtp.qq.com", "8555323@qq.com","1+1IStwo");
    }

}

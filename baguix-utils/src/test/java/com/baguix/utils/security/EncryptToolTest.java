package com.baguix.utils.security;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EncryptTool Tester.
 *
 * @author Scott(SG)
 */
public class EncryptToolTest {
    private static final Logger logger = LoggerFactory.getLogger(EncryptToolTest.class);

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: encrypt(String str, String algorithm)
     */
    @Test
    public void testEncrypt() throws Exception {
        String str = "测试abc1234@#$%";
        String estr = EncryptTool.encrypt(str, EncryptTool.MD5);
        assertEquals("5069db92a1ebb3bd725322082d3d52c4", estr);
    }

    /**
     * Method: md5AndSha(String str)
     */
    @Test
    public void testMd5AndSha() throws Exception {
        String str = "测试abc1234@#$%";
        String estr = EncryptTool.md5AndSha(str);
        assertEquals("a73f9cc88f0867b2b4c4e3addd767acb8b49bf19", estr);
    }

    /**
     * Method: md5(String str)
     */
    @Test
    public void testMd5() throws Exception {
        String str = "测试abc1234@#$%";
        String estr = EncryptTool.md5(str);
        assertEquals("5069db92a1ebb3bd725322082d3d52c4", estr);
    }

    /**
     * Method: sha(String str)
     */
    @Test
    public void testSha() throws Exception {
        String str = "测试abc1234@#$%";
        String estr = EncryptTool.sha(str);
        assertEquals("084eda29edb0475834620fde95d8dd5245ba3bb3", estr);
    }

    /**
     * Method: basicEncrypt(String str)
     */
    @Test
    public void testBasicEncrypt() throws Exception {
        String str = "测试abc1234@#$%";
        String estr = EncryptTool.basicEncrypt(str);
        String dstr = EncryptTool.basicDecrypt(estr);
        assertEquals(str, dstr);
    }

    /**
     * Method: basicDecrypt(String str)
     */
    @Test
    public void testBasicDecrypt() throws Exception {
        String estr = "DmNbElUimNnkZvQGt+ocP+cvEc/oamVxVEVrWoCR0ZA=";
        String str = EncryptTool.basicDecrypt(estr);
        assertEquals(str, "测试abc1234@#$%");

    }

    /**
     * Method: strongEncrypt(String str)
     */
    @Test
    public void testStrongEncrypt() throws Exception {
        String str = "测试abc1234@#$%";
        String estr = EncryptTool.strongEncrypt(str);
        String dstr = EncryptTool.strongDecrypt(estr);
        assertEquals(str, dstr);
    }

    /**
     * Method: strongDecrypt(String str)
     */
    @Test
     public void testStrongDecrypt() throws Exception {
        String estr = "jWYpOInEM0MUBfjayxPm9liGp9cDcL/vt5nv9taO2Vk=";
        String str = EncryptTool.strongDecrypt(estr);
        assertEquals(str, "测试abc1234@#$%");
    }

    /**
     * Method: KSE(String str, int key) KSD(String str, int key)
     */
    @Test
    public void testKS() throws Exception {
        String str = "http://blog.sina.com.cn/s/blog_7a901a8701012xvt.html";
        String estr = EncryptTool.KSE(str, 5);
        assertEquals(estr, "ff7\"IjGhF0{G=I8G=0fhf7\"Ij>6{%s/{P6s/s/-`xYGyY8\"yYY$N");
        String dstr = EncryptTool.KSD(estr, 5);
        assertEquals(dstr, str);
    }
}

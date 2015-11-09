package com.baguix.utils.doc;

import com.baguix.utils.data.NowDateTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import org.apache.poi.hwpf.HWPFDocument;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * WordGenerator Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>八月 25, 2015</pre>
 */
public class WordGeneratorTest {
    private static final Logger logger = LoggerFactory.getLogger(WordGeneratorTest.class);
    private String filepath1 = "";
    private String filepath2 = "";
    private String filepath3 = "";
    private String filepath4 = "";
    private String filepath5 = "";
    private String temppath = "";

    @Before
    public void before() throws Exception {
        temppath = PathTool.getClassesPath() + "/docxls/template1.doc";
        filepath1 = PathTool.getClassesPath() + "/docxls/test1.doc";
        filepath2 = PathTool.getClassesPath() + "/docxls/test2.doc";
        filepath3 = PathTool.getClassesPath() + "/docxls/test3.doc";
        filepath4 = PathTool.getClassesPath() + "/docxls/test4.doc";
        filepath5 = PathTool.getClassesPath() + "/docxls/test5.doc";
    }

    @After
    public void after() throws Exception {
//        FileManager.delFile(filepath1);
//        FileManager.delFile(filepath2);
//        FileManager.delFile(filepath3);
//        FileManager.delFile(filepath4);
//        FileManager.delFile(filepath5);
    }

    /**
     * Method: getDocByTemplate()
     */
    @Test
    public void testGetDocByTemplate() throws Exception {
        UserBean user = new UserBean();
        user.setUser("李四");
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        HWPFDocument hd = wg.getDocByTemplate();
        wg.replaceText(hd, "${title}", "关于" + user.getUser() + "的任职证明");
        wg.replaceText(hd, "${date}", NowDateTool.getCNDate());
        File out = new File(filepath1);
        FileOutputStream fout = new FileOutputStream(out);
        hd.write(fout);
        fout.flush();
        fout.close();
        assertTrue(out.exists());
    }

    /**
     * Method: genDoc()
     */
    @Test
    public void testGenDoc() throws Exception {
        UserBean user = new UserBean();
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        ByteArrayOutputStream bos = wg.genDoc();
        File out = new File(filepath2);
        FileOutputStream fout = new FileOutputStream(out);
        bos.writeTo(fout);
        fout.flush();
        bos.flush();
        fout.close();
        bos.close();
        assertTrue(out.exists());
    }

    /**
     * Method: getDocOS(HWPFDocument wd)
     */
    @Test
    public void testGetDocOS() throws Exception {
        UserBean user = new UserBean();
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        HWPFDocument hd = wg.getDocByTemplate();
        ByteArrayOutputStream bos = wg.getDocOS(hd);
        File out = new File(filepath3);
        FileOutputStream fout = new FileOutputStream(out);
        bos.writeTo(fout);
        fout.flush();
        bos.flush();
        fout.close();
        bos.close();
        assertTrue(out.exists());
    }

    /**
     * Method: setTemplate(String template)
     */
    @Test
    public void testSetTemplate() throws Exception {
        UserBean user = new UserBean();
        user.setUser("李四");
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        wg.setTemplate(PathTool.getClassesPath() + "/docxls/template2.doc");
        HWPFDocument hd = wg.getDocByTemplate();
        File out = new File(filepath4);
        FileOutputStream fout = new FileOutputStream(out);
        hd.write(fout);
        fout.flush();
        fout.close();
        assertTrue(out.exists());
    }

    /**
     * Method: getTemplate()
     */
    @Test
    public void testGetTemplate() throws Exception {
        UserBean user = new UserBean();
        user.setUser("李四");
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        assertEquals(wg.getTemplate(),temppath);
    }

    /**
     * Method: getObj()
     */
    @Test
    public void testGetObj() throws Exception {
        UserBean user = new UserBean();
        user.setUser("李四");
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        assertEquals(wg.getObj(),user);
    }

    /**
     * Method: setObj(T obj)
     */
    @Test
    public void testSetObj() throws Exception {
        UserBean user = new UserBean();
        UserBean user1 = new UserBean();
        user1.setUser("王五");
        WordGenerator<UserBean> wg = new WordGenerator<UserBean>(temppath, user);
        wg.setObj(user1);
        HWPFDocument hd = wg.getDocByTemplate();
        File out = new File(filepath5);
        FileOutputStream fout = new FileOutputStream(out);
        hd.write(fout);
        fout.flush();
        fout.close();
        assertTrue(out.exists());
    }

} 

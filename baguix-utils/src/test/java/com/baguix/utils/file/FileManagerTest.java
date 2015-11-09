/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/17.
 */
package com.baguix.utils.file;

import com.baguix.utils.data.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * FileManager Tester.
 *
 * @author Scott(SG)
 */
public class FileManagerTest {
    private static final Logger logger = LoggerFactory.getLogger(FileManagerTest.class);
    private static String basepath = PathTool.getPath(FileManager.class);
    private static String separator = FileManager.getFileSeparator();

    @Before
    public void setUp() throws Exception {
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "cc.txt", "cc");
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "测试内容.txt", "测试内容");
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "测试内容GBK.txt", "测试内容GBK", Constants.GBK);
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "测试内容UTF8.txt", "测试内容UTF8", Constants.UTF8);
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "新建文件夹" + separator + "新建文件1.txt", "新建文件1");
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "新建文件夹" + separator + "新建文件2.txt", "新建文件2");
        FileManager.newTextFile(basepath + separator + "test1" + separator + "dsds" + separator + "新建文件夹" + separator + "新建文件3.txt", "新建文件3");
    }

    @After
    public void tearDown() throws Exception {
        FileManager.delFolder(basepath + separator + "test1");
    }

    /**
     * Method: getFileSeparator()
     */
    @Test
    public void testGetFileSeparator() throws Exception {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            assertEquals(FileManager.getFileSeparator(), "\\");
        } else {
            assertEquals(FileManager.getFileSeparator(), "/");
        }
    }

    /**
     * Method: newFolder(File dir)
     */
    @Ignore
    public void testNewFolderDir() throws Exception {
        FileManager.newFolder(new File(basepath + separator + "test1" + separator + "dsds" + separator + "dd1"));
    }

    /**
     * Method: newFolder(String path)
     */
    @Test
    public void testNewFolderPath() throws Exception {
        //fm.newFolder("C:\\test1\\dsds\\cc");
        FileManager.newFolder(basepath + separator + "test1" + separator + "dsds" + separator + "dd1");
    }

    /**
     * Method: newTextFile(String filePathAndName, String fileContent)
     */
    @Test
    public void testNewTextFile() throws Exception {
        // see setUp
    }

    /**
     * Method: readStrFromFile(String file)
     */
    @Test
    public void testReadStrFromFile() throws Exception {
        String file = basepath + separator + "test1" + separator + "dsds" + separator + "测试内容.txt";
        String content = "测试内容";
        FileManager.newTextFile(file, content);
        String result = FileManager.readStrFromFile(file,"UTF-8");
        assertEquals(result, content);
        String fileen = basepath + separator + "test1" + separator + "dsds" + separator + "测试内容UTF8.txt";
        String contenten = "测试内容UTF8";
        String charset = Constants.UTF8;
        FileManager.newTextFile(fileen, contenten, charset);
        String resulten = FileManager.readStrFromFile(fileen,"UTF-8");
        assertEquals(resulten, contenten);
    }

    /**
     * Method: delFile(String file)
     */
    @Test
    public void testDelFile() throws Exception {
        String file = basepath + separator + "test1" + separator + "dsds" + separator + "测试内容UTF8.txt";
        FileManager.delFile(file);
        File ff = new File(file);
        assertFalse(ff.exists());
    }

    /**
     * Method: cleanFolder(String path)
     */
    @Test
    public void testCleanFolder() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        FileManager.cleanFolder(path);
        File ff1 = new File(path + separator + "cc.txt");
        assertFalse(ff1.exists());
        File ff2 = new File(path + separator + "测试内容.txt");
        assertFalse(ff2.exists());
        File ff3 = new File(path + separator + "测试内容GBK.txt");
        assertFalse(ff3.exists());
        File ff4 = new File(path + separator + "测试内容UTF8.txt");
        assertFalse(ff4.exists());
        FileManager.newTextFile(path + separator + "cc.txt", "cc");
        FileManager.newTextFile(path + separator + "测试内容.txt", "测试内容");
        FileManager.newTextFile(path + separator + "测试内容GBK.txt", "测试内容GBK", Constants.GBK);
        FileManager.newTextFile(path + separator + "测试内容UTF8.txt", "测试内容UTF8", Constants.UTF8);
    }

    /**
     * Method: delFolder(String path)
     */
    @Test
    public void testDelFolder() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        FileManager.delFolder(path);
        File ff = new File(path);
        assertFalse(ff.exists());
    }

    /**
     * Method: List<File> listFileFromFolder(String path)
     */
    @Test
    public void testListFileFromFolder() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        List<File> list = FileManager.listFileFromFolder(path);
        List<File> alist = new ArrayList<File>();
        alist.add(new File(path + separator + "cc.txt"));
        alist.add(new File(path + separator + "测试内容.txt"));
        alist.add(new File(path + separator + "测试内容GBK.txt"));
        alist.add(new File(path + separator + "测试内容UTF8.txt"));
        assertEquals(list, alist);
    }

    /**
     * Method: List<String> listFileNameFromFolder(String path)
     */
    @Test
    public void testlistFileNameFromFolder() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        List<String> list = FileManager.listFileNameFromFolder(path);
        List<String> alist = new ArrayList<String>();
        alist.add("cc.txt");
        alist.add("测试内容.txt");
        alist.add("测试内容GBK.txt");
        alist.add("测试内容UTF8.txt");
        assertEquals(list, alist);
    }

    /**
     * Method: List<String> listFolderAndFileFromFolder(File path, List<String> list)
     */
    @Test
    public void testListFolderAndFileFromFolder() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        List<String> list = FileManager.listFolderAndFileFromFolder(new File(path));
        List<String> alist = new ArrayList<String>();
        alist.add("cc.txt");
        alist.add("新建文件夹\\");
        alist.add("测试内容.txt");
        alist.add("测试内容GBK.txt");
        alist.add("测试内容UTF8.txt");
        assertEquals(list, alist);
    }

    /**
     * Method: List<String> listAllFolderAndFileFromFolder(File path, List<String> list)
     */
    @Test
    public void testListAllFolderAndFileFromFolder() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        List<String> list = new ArrayList<String>();
        FileManager.listAllFolderAndFileFromFolder(new File(path), path, list);
        List<String> alist = new ArrayList<String>();
        alist.add("cc.txt");
        alist.add("新建文件夹\\");
        alist.add("新建文件夹\\新建文件1.txt");
        alist.add("新建文件夹\\新建文件2.txt");
        alist.add("新建文件夹\\新建文件3.txt");
        alist.add("测试内容.txt");
        alist.add("测试内容GBK.txt");
        alist.add("测试内容UTF8.txt");
        assertEquals(list, alist);
    }

    /**
     * Method: copyFile(String oldpath, String newpath)
     */
    @Test
    public void testCopyFile() throws Exception {
        String path = basepath + separator + "test1" + separator + "dsds";
        String oldfile = path + separator + "测试内容UTF8.txt";
        String newfile1 = path + separator + "测试内容UTF81.txt";
        String newfile2 = basepath + separator + "test1" + separator + "测试内容UTF82.txt";
        FileManager.copyFile(oldfile, newfile1, 0);
        FileManager.copyFile(oldfile, newfile2, 0);
        FileManager.writeStrToFile(oldfile, "新的内容", Constants.UTF8);
        FileManager.copyFile(oldfile, newfile1, 0);
        FileManager.copyFile(oldfile, newfile2, 1);
        List<String> list = new ArrayList<String>();
        FileManager.listAllFolderAndFileFromFolder(new File(path), path, list);
        List<String> alist = new ArrayList<String>();
        alist.add("cc.txt");
        alist.add("新建文件夹\\");
        alist.add("新建文件夹\\新建文件1.txt");
        alist.add("新建文件夹\\新建文件2.txt");
        alist.add("新建文件夹\\新建文件3.txt");
        alist.add("测试内容.txt");
        alist.add("测试内容GBK.txt");
        alist.add("测试内容UTF8.txt");
        alist.add("测试内容UTF81.txt");
        assertEquals(list, alist);
    }

    /**
     * Method: copyFolder(String oldpath, String newpath)
     */
    @Test
    public void testCopyFolder() throws Exception {
        String oldpath = basepath + separator + "test1" + separator + "dsds";
        String newpath = basepath + separator + "test1" + separator + "dsds1";
        FileManager.copyFolder(oldpath, newpath);
        File ffold = new File(newpath);
        File ffnew = new File(newpath);
        assertTrue(ffold.exists());
        List<String> listold = new ArrayList<String>();
        listold = FileManager.listAllFolderAndFileFromFolder(ffold, oldpath, listold);
        List<String> listnew = new ArrayList<String>();
        listnew = FileManager.listAllFolderAndFileFromFolder(ffnew, oldpath, listnew);
        assertEquals(listold, listnew);
    }

    /**
     * Method: moveFile(String oldpath, String newpath)
     */
    @Test
    public void testMoveFile() throws Exception {
        String oldpath = basepath + separator + "test1" + separator + "dsds" + separator + "cc.txt";
        String newpath = basepath + separator + "test1" + separator + "ss" + separator + "dd.txt";
        File ffold = new File(oldpath);
        FileManager.moveFile(oldpath, newpath);
        assertFalse(ffold.exists());
        File ffnew = new File(newpath);
        assertTrue(ffnew.exists());
    }

    /**
     * Method: moveFolder(String oldpath, String newpath)
     */
    @Test
    public void testMoveFolder() throws Exception {
        String oldpath = basepath + separator + "test1" + separator + "dsds";
        String newpath = basepath + separator + "test1" + separator + "ss";
        File ffold = new File(oldpath);
        List<String> olist = new ArrayList<>();
        FileManager.listAllFolderAndFileFromFolder(ffold, oldpath, olist);
        FileManager.moveFolder(oldpath, newpath);
        File ffnew = new File(newpath);
        List<String> nlist = new ArrayList<>();
        FileManager.listAllFolderAndFileFromFolder(ffnew, newpath, nlist);
        assertFalse(ffold.exists());
        assertTrue(ffnew.exists());
        assertEquals(olist, nlist);
    }

    /**
     * Method: getFileMd5(String oldpath, String newpath)
     */
    @Test
    public void testGetFileMd5() throws Exception {
        File file = new File(basepath + separator + "test1" + separator + "dsds" + separator + "cc.txt");
        String  md5 = FileManager.getFileMd5(file);
        assertEquals(md5,"298007873705211094981943431744292596860");
    }

    /**
     * Method: getFileSha1(String oldpath, String newpath)
     */
    @Test
    public void testGetFileSha1() throws Exception {
        File file = new File(basepath + separator + "test1" + separator + "dsds" + separator + "cc.txt");
        String  sha1 = FileManager.getFileSha1(file);
        assertEquals(sha1,"1083024615871448623911289451483312220212831417777");
    }
} 

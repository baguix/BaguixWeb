package com.baguix.utils.file;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * FileCompressor Tester.
 *
 * @author <Scott>
 */
public class FileCompressorTest {
    private static final Logger logger = LoggerFactory.getLogger(FileCompressorTest.class);
    private String zip = "";
    private String file = "";
    private String file1 = "";
    private String dir = "";
    private String edir = "";

    @Before
    public void before() throws Exception {
        zip = PathTool.getClassesPath() + "\\test.zip";
        dir = PathTool.getClassesPath() + "\\docxls";
        file = PathTool.getClassesPath() + "\\docxls\\template1.doc";
        edir = PathTool.getClassesPath() + "\\docxls_unzip";
        file1 = PathTool.getClassesPath() + "\\docxls\\template2.doc";
    }

    @After
    public void after() throws Exception {
        FileManager.delFile(zip);
        FileManager.delFolder(edir);
    }

    /**
     * Method: zipFolder(File zipfile, File path)
     */
    @Test
    public void testZipFolderFile() throws Exception {
        File z = new File(zip);
        File d = new File(dir);
        FileCompressor.zipFolder(z, d);
        assertTrue(z.exists());
    }

    /**
     * Method: zipFolder(String zipfile, String path)
     */
    @Test
    public void testZipFolderStr() throws Exception {
        FileCompressor.zipFolder(zip, dir);
        File z = new File(zip);
        assertTrue(z.exists());
    }

    /**
     * Method: zipWholeFolder(File zipfile, File path)
     */
    @Test
    public void testZipWholeFolderFile() throws Exception {
        File z = new File(zip);
        File d = new File(dir);
        FileCompressor.zipWholeFolder(z, d);
        assertTrue(z.exists());
    }

    /**
     * Method: zipWholeFolder(String zipfile, String path)
     */
    @Test
    public void testZipWholeFolderStr() throws Exception {
        FileCompressor.zipWholeFolder(zip, dir);
        File z = new File(zip);
        assertTrue(z.exists());
    }

    /**
     * Method: zipFolderInclude(File, File, String)
     */
    @Test
    public void testZipFolderInclude() throws Exception {
        File z = new File(zip);
        File d = new File(dir);
        FileCompressor.zipFolderInclude(z, d, "test*.doc");
        assertTrue(z.exists());
    }

    /**
     * Method: zipFolderExclude(File, File, String)
     */
    @Test
    public void testZipFolderExclude() throws Exception {
        File z = new File(zip);
        File d = new File(dir);
        FileCompressor.zipFolderExclude(z, d, "**/test*.doc");
        assertTrue(z.exists());
    }

    /**
     * Method: unZip(File zipfile, File path)
     */
    @Test
    public void testUnZipFolderFile() throws Exception {
        FileCompressor.zipFolder(zip, dir);
        File z = new File(zip);
        File src = new File(dir);
        File d = new File(edir);
        FileCompressor.unZip(z, d);
        assertTrue(d.exists() && d.isDirectory());
        List<String> srclist = new ArrayList<>();
        srclist = FileManager.listAllFolderAndFileFromFolder(src, src.getParent(), srclist);
        List<String> deslist = new ArrayList<>();
        deslist = FileManager.listAllFolderAndFileFromFolder(src, src.getParent(), deslist);
        assertEquals(srclist, deslist);
    }

    /**
     * Method: unZipFolder(String zipfile, String path)
     */
    @Test
    public void testUnZipFolderStr() throws Exception {
        FileCompressor.zipFolder(zip, dir);
        FileCompressor.unZip(zip, edir);
        File src = new File(dir);
        File unzipdir = new File(edir);
        assertTrue(unzipdir.exists() && unzipdir.isDirectory());
        List<String> srclist = new ArrayList<>();
        srclist = FileManager.listAllFolderAndFileFromFolder(src, src.getParent(), srclist);
        List<String> deslist = new ArrayList<>();
        deslist = FileManager.listAllFolderAndFileFromFolder(src, src.getParent(), deslist);
        assertEquals(srclist, deslist);
    }

    /**
     * Method: zipFile(File zipfile, File path)
     */
    @Test
    public void testZipFileFile() throws Exception {
        File z = new File(zip);
        File f1 = new File(file);
        File f2 = new File(file1);
        File f3 = new File("C:\\Windows\\notepad.exe");
        File f4 = new File("C:\\test1\\dsds");
        FileCompressor.zipFile(z, f1, f2, f3, f4);
        assertTrue(z.exists());
    }

    /**
     * Method: zipFile(String zipfile, String path)
     */
    @Test
    public void testZipFileStr() throws Exception {
        FileCompressor.zipFile(zip, file, file1, "C:\\Windows\\notepad.exe", "C:\\test1\\dsds");
        File z = new File(zip);
        assertTrue(z.exists());
    }
} 

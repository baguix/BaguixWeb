package com.baguix.utils.file;

import com.baguix.utils.doc.HtmlTool;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
* PathTool Tester. 
* 
* @author Scott(SG)
*/ 
public class PathToolTest { 
    private static final Logger logger = LoggerFactory.getLogger(PathToolTest.class);
    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    }

    /**
     * Method: getPath(Class clazz)
     */
    @Ignore
    public void testGetPathClazz() throws Exception {
        String path = PathTool.getPath(PathToolTest.class);
        String OS = System.getProperty("os.name").toLowerCase();
        String act;
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            act = "E:\\baguix\\baguix-utils\\target\\test-classes\\com\\baguix\\utils\\file";
        } else {
            act = "/mnt/baguix/baguix-utils/target/test-classes/com/baguix/utils/file";
        }
        assertEquals(act,path);
    }
    
    /**
     * Method: getPath(Object object)
     */
    @Ignore
    public void testGetPathObject() throws Exception {
        String path = PathTool.getPath(new HtmlTool());
        String OS = System.getProperty("os.name").toLowerCase();
        String act;
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            act = "E:\\baguix\\baguix-utils\\target\\test-classes\\com\\baguix\\utils\\doc";
        } else {
            act = "/mnt/baguix/baguix-utils/target/test-classes/com/baguix/utils/doc";
        }
        assertEquals(act,path);
    } 
    
    /**
     * Method: getWebroot()
     */
    @Ignore
    public void testGetWebroot() throws Exception {
        String path = PathTool.getWebRootPath();
        String OS = System.getProperty("os.name").toLowerCase();
        String act;
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            act = "E:\\baguix\\baguix-utils";
        } else {
            act = "/mnt/baguix/baguix-utils";
        }
        assertEquals(act,path);
    } 
    
    /**
     * Method: getWebinf()
     */
    @Ignore
    public void testGetWebinf() throws Exception {
        String path = PathTool.getWebInfPath();
        String OS = System.getProperty("os.name").toLowerCase();
        String act;
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            act = "E:\\baguix\\baguix-utils\\target";
        } else {
            act = "/mnt/baguix/baguix-utils/target";
        }
        assertEquals(act,path);
    } 
    
    /**
     * Method: getClassesPath()
     */
    @Ignore
    public void testGetClassesPath() throws Exception {
        String path = PathTool.getClassesPath();
        String OS = System.getProperty("os.name").toLowerCase();
        String act;
        if (OS.indexOf("windows") >= 0 || OS.indexOf("dos") >= 0) {
            act = "E:\\baguix\\baguix-utils\\target\\test-classes";
        } else {
            act = "/mnt/baguix/baguix-utils/target/test-classes";
        }
        assertEquals(act,path);
    }

    /**
     * Method: getJreHomePath()
     */
    @Test
    public void testGetJreHomePath() throws Exception {
        String path = PathTool.getJreHomePath();
        String act = System.getProperty("java.home");
        assertEquals(act, path);
    }
} 

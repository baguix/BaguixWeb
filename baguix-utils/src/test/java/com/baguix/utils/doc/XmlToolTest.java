package com.baguix.utils.doc;

import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import com.thoughtworks.xstream.XStream;
import org.jdom2.Element;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 
import static org.junit.Assert.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/** 
* XmlTool Tester. 
* 
* @author Scott(SG)
*/ 
public class XmlToolTest { 
    private static final Logger logger = LoggerFactory.getLogger(XmlToolTest.class);
    private String folderpath = "";
    private String filepath = "";
    private String filepath1 = "";
    private String filepath2 = "";
    @Before
    public void before() throws Exception {
        folderpath = PathTool.getClassesPath()+"/files";
        String content = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<test success='true'>\n" +
                "<Shop><sid>1</sid><name>baguix1</name></Shop>\n" +
                "<Shop><sid>2</sid><name>baguix2</name></Shop>\n" +
                "</test>";
        filepath = PathTool.getClassesPath() + "/files/test1.xml";
        File f = new File(filepath);
        if (!f.exists()) {
            FileManager.newTextFile(filepath, content);
        }
        filepath1 = PathTool.getClassesPath() + "/files/test2.xml";
        File f1 = new File(filepath1);
        if (!f1.exists()) {
            FileManager.newTextFile(filepath1, content);
        }
        filepath2 = PathTool.getClassesPath() + "/files/test3.xml";
        File f2 = new File(filepath2);
        if (!f2.exists()) {
            FileManager.newTextFile(filepath2, content);
        }
    } 
    
    @After
    public void after() throws Exception {
        FileManager.cleanFolder(PathTool.getClassesPath()+"/files");
    } 
    
    /**
     * 
     * Method: getXMLRoot(String file) 
     * 
     */ 
    @Test
    public void testGetXMLRoot() throws Exception {
        Element root = XmlTool.getXMLRoot(filepath);
        String root_name = root.getName();
        String success = root.getAttribute("success").getValue();
        assertEquals(root_name,"test");
        assertEquals(success,"true");
    } 
    
    /**
     * 
     * Method: getFolderXMLRoot(String path) 
     * 
     */ 
    @Test
    public void testGetFolderXMLRoot() throws Exception {
        Map<String,Element> xmls = XmlTool.getFolderXMLRoot(folderpath);
        Set<String> keys = xmls.keySet();
        int i=0;
        for(String k : keys) {
            Element root  = xmls.get(k);
            String root_name = root.getName();
            String success = root.getAttribute("success").getValue();
            assertEquals(root_name, "test");
            assertEquals(success, "true");
            i++;
        }
        assertEquals(3,i);
    }

    /**
     *
     * Method: getElementsByXPath(String,String)
     *
     */
    @Test
    public void testGetElementsByXPathString() throws Exception {
        List<Element> list = XmlTool.getElementsByXPath(filepath,"//name");
        for(int i=0;i<list.size();i++) {
            String v = list.get(i).getValue();
            assertEquals(v, "baguix" + new Integer(i + 1).toString());
        }
    }

    /**
     *
     * Method: getElementsByXPath(Element,String)
     *
     */
    @Test
    public void testGetElementsByXPathElement() throws Exception {
        Element root = XmlTool.getXMLRoot(filepath);
        List<Element> list = XmlTool.getElementsByXPath(root,"//name");
        for(int i=0;i<list.size();i++) {
            String v = list.get(i).getValue();
            assertEquals(v,"baguix"+new Integer(i+1).toString());
        }
    }

    @Ignore
    public void testObj2Xml() throws Exception {
        // 构造一个StudentBean对象
        UserBean user = new UserBean();
        user.setUser("李四");
        // 将StudentBean对象写到XML文件
        String fileName = "c:/user.xml";
        XmlTool.obj2Xml(user, fileName);
    }


    @Ignore
    public void testObj2XmlWithCDATA() throws Exception {
        // 构造一个StudentBean对象
        UserBean user = new UserBean();
        user.setUser("<name>李四</name>");
        // 将StudentBean对象写到XML文件
        String fileName = "c:/user.xml";
        XmlTool.obj2XmlWithCDATA(user, fileName);
    }

    @Ignore
    public void testXml2Obj() throws Exception {
        String fileName = "c:/user.xml";
        UserBean user = (UserBean) XmlTool.xml2Object(fileName);
        logger.info(user.getUser());
    }

    @Ignore
    public void testObj2XMLXStream() throws Exception {
        // 构造一个StudentBean对象
        UserBean user = new UserBean();
        user.setUser("<![CDATA[<user id='张三'>]]>");
        XStream xs = new XStream();
        // 构造输出XML文件的字节输出流
        File outFile = new File("c:/user.xml");
        // 根节点<com.baguix.utils.doc.UserBean>--><userbean>
        xs.alias("userbean", UserBean.class);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(outFile));
        xs.toXML(user, bos);
    }

    @Ignore
    public void testXML2ObjXStream() throws Exception {
        // 构造一个StudentBean对象
        XStream xs = XStreamCDATA.initXStream();
        // 构造输出XML文件的字节输出流
        File inFile = new File("c:/user.xml");
        // 根节点<com.baguix.utils.doc.UserBean>--><userbean>
        xs.alias("userbean", UserBean.class);
        BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(inFile));
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
        UserBean user = (UserBean)xs.fromXML(reader);
        logger.info(user.getUser());
    }
} 

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.doc;

import com.baguix.utils.data.Constants;
import com.baguix.utils.file.FileManager;
import com.thoughtworks.xstream.XStream;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <b>XML文件操作类</b><br>
 *
 * @author Scott(SG)
 */
public class XmlTool {
    // 单例
    private static XmlTool instance;

    private static XmlTool getInstance() {
        if (instance == null) {
            instance = new XmlTool();
        }
        return instance;
    }

    // 隐藏构造器
    private XmlTool() {

    }

    /**
     * <b>获取一个XML文件的根节点</b><br>
     *
     * @param file 文件
     * @return org.jdom2.Element XML文件的根节点
     */
    public static Element getXMLRoot(String file) {
        Element root = null;
        if (file != null && !"".equals(file)) {
            File f = new File(file);
            if (f.exists() && f.isFile()) {
                SAXBuilder sb = new SAXBuilder();
                try {
                    Document doc = sb.build(f).getDocument();
                    root = doc.getRootElement();
                } catch (JDOMException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return root;
    }

    /**
     * <b>获取文件夹中所有XML文件的根节点</b><br>
     *
     * @param path 文件夹路径
     * @return 返回Map，key是文件名
     */
    public static Map<String, Element> getFolderXMLRoot(String path) {
        Map<String, Element> result = new HashMap<String, Element>();
        if (path != null && !"".equals(path)) {
            File dir = new File(path);
            if (dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                for (File f : files) {
                    String ext = FileManager.getFileExtension(f);
                    if (ext != null && "xml".equals(ext)) {
                        SAXBuilder sb = new SAXBuilder();
                        try {
                            Document doc = sb.build(f).getDocument();
                            Element root = doc.getRootElement();
                            String key = f.getName().substring(0, f.getName().indexOf(ext) - 1);
                            result.put(key, root);
                        } catch (JDOMException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }


            }
        }
        return result;
    }

    /**
     * <b>根据Xpath获取XML文件中若干节点</b><br>
     *
     * @param file  文件
     * @param xpath 文件
     * @return 符合XPATH搜索表达式的节点
     * @see XmlTool#getElementsByXPath(Element, String)
     */
    public static List<Element> getElementsByXPath(String file, String xpath) {
        List<Element> list = new ArrayList<Element>();
        if (file != null && !"".equals(file)) {
            Element root = getXMLRoot(file);
            list = getElementsByXPath(root, xpath);
        }
        return list;
    }

    /**
     * <b>根据Xpath获取根节点root中若干节点</b><br>
     * 说明：请参考<a href="http://www.w3school.com.cn/xpath/xpath_syntax.asp">XPath教程</a>
     *
     * @param root  文件
     * @param xpath xpath表达式
     * @return 符合XPATH搜索表达式的节点
     */
    public static List<Element> getElementsByXPath(Element root, String xpath) {
        List<Element> list = new ArrayList<Element>();
        if (root != null) {
            XPathExpression<Element> xpath_exp = XPathFactory.instance().compile(xpath, Filters.element());
            list = xpath_exp.diagnose(root, false).getResult();
        }
        return list;
    }

    /**
     * <b>根据Xpath获取根节点root中若干节点</b><br>
     * 说明：定义有namespace的xml文档无法直接获得节点。需要根据定义的namespace获得。<br>
     * 例如：String xpath1 = "/ns:beans/ns:bean";<br>
     * XPathExpression<Element> xpath_exp = XPathFactory.instance().compile(<br>
     * xpath1, Filters.element(),null, Namespace.getNamespace("ns","http://www.springframework.org/schema/beans"));
     *
     * @param root      文件
     * @param xpath     xpath表达式
     * @param namespace namespace的uri，例如：http://www.springframework.org/schema/beans
     * @return 符合XPATH搜索表达式的节点
     */
    public static List<Element> getElementsByXPath(Element root, String xpath, String namespace) {
        List<Element> list = new ArrayList<Element>();
        if (root != null) {
            XPathExpression<Element> xpath_exp = XPathFactory.instance().compile(xpath, Filters.element(), null,
                    Namespace.getNamespace("ns", namespace));
            list = xpath_exp.diagnose(root, false).getResult();
        }
        return list;
    }

    /**
     * <b>java对象写成XML文件</b><br>
     * 采用XStream框架<br>
     *
     * @param obj     JavaBean
     * @param xmlFile 输出的xml文件路径
     * @throws FileNotFoundException
     */
    public static void obj2Xml(Object obj, String xmlFile) {
        try {
            XStream xs = XStreamCDATA.initXStream();
            File outFile = new File(xmlFile);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(outFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos, Constants.UTF8));
            xs.processAnnotations(obj.getClass());
            xs.autodetectAnnotations(true);
            xs.toXML(obj, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>java对象写成XML文件</b><br>
     * 采用XStream框架<br>
     *
     * @param obj     JavaBean
     * @param alias   根节点别名
     * @param xmlFile 输出的xml文件路径
     */
    public static void obj2Xml(Object obj, String alias, String xmlFile) {
        try {
            XStream xs = XStreamCDATA.initXStream();
            File outFile = new File(xmlFile);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(outFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos, Constants.UTF8));
            xs.alias(alias, obj.getClass());
            xs.toXML(obj, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>java对象写成XML文件，包含CDATA</b><br>
     * 采用XStream框架<br>
     *
     * @param obj     JavaBean
     * @param xmlFile 输出的xml文件路径
     */
    public static void obj2XmlWithCDATA(Object obj, String xmlFile) {
        try {
            XStream xs = XStreamCDATA.writeTextFieldCDATA();
            File outFile = new File(xmlFile);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(outFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos, Constants.UTF8));
            xs.processAnnotations(obj.getClass());
            xs.autodetectAnnotations(true);
            xs.toXML(obj, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>java对象写成XML文件，包含CDATA</b><br>
     * 采用XStream框架<br>
     *
     * @param obj     JavaBean
     * @param alias   根节点别名
     * @param xmlFile 输出的xml文件路径
     */
    public static void obj2XmlWithCDATA(Object obj, String alias, String xmlFile) {
        try {
            XStream xs = XStreamCDATA.writeTextFieldCDATA();
            File outFile = new File(xmlFile);
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(outFile));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(bos, Constants.UTF8));
            xs.alias(alias, obj.getClass());
            xs.toXML(obj, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * <b>XML文件映射为obj</b><br>
     * 采用XStream框架<br>
     *
     * @param xmlFile 输入的xml路径文件
     * @return Object对象
     */
    public static Object xml2Object(String xmlFile) {
        Object obj = null;
        try {
            XStream xs = new XStream();
            File inFile = new File(xmlFile);
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(inFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis, Constants.UTF8));
            obj = xs.fromXML(reader);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * <b>XML文件映射为obj</b><br>
     * 采用XStream框架<br>
     *
     * @param xmlFile 输入的xml路径文件
     * @param c       对象的class
     * @param alias   根节点别名
     * @return Object对象
     */
    public static Object xml2Object(String xmlFile, Class c, String alias) {
        Object obj = null;
        try {
            XStream xs = new XStream();
            File inFile = new File(xmlFile);
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(inFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis, Constants.UTF8));
            xs.alias(alias, c);
            obj = xs.fromXML(reader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return obj;
    }
}

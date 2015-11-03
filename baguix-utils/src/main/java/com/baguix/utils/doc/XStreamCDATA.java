/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/4.
 */
package com.baguix.utils.doc;

import java.io.Reader;
import java.io.Writer;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.io.xml.XppReader;

/**
 * <b>XStream扩展XML的CDATA读写功能</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class XStreamCDATA {
    protected static String PREFIX_CDATA = "<![CDATA[";
    protected static String SUFFIX_CDATA = "]]>";

    /**
     * <b>XStream生成器</b><br>
     * 说明：<br>
     * 如果Bean中的数据以&lt;![CDATA[]]&gt;包着，<br>
     * 则写XML的时候包含非转义的&lt;![CDATA[]]&gt;，<br>
     * 但读取时不包含&lt;![CDATA[]]&gt;。<br>
     * 例如：<br>
     * user.getName = "&lt;![CDATA[张三]]&gt;"<br>
     * xs.toXML(user, bos);<br>
     * xml文件中的内容为--->&lt;name&gt;&lt;![CDATA[张三]]&gt;&lt;/name&gt;<br>
     * User user = xs.fromXML(bis);<br>
     * user.getName = "张三"<br>
     *
     * @return XStream实例
     */
    public static XStream initXStream() {
        XStream xstream = new XStream(
                new XppDriver() {
                    public HierarchicalStreamWriter createWriter(Writer out) {
                        return new PrettyPrintWriter(out) {
                            protected void writeText(QuickWriter writer, String text) {
                                if (text.startsWith(PREFIX_CDATA)
                                        && text.endsWith(SUFFIX_CDATA)) {
                                    writer.write(text);
                                } else {
                                    super.writeText(writer, text);
                                }
                            }
                        };
                    }
                }
        );

        return xstream;
    }

    /**
     * <b>XStream生成器</b><br>
     * 说明：<br>
     * Bean中所有属性均以&lt;![CDATA[]]&gt;包着写入XML
     *
     * @return XStream实例
     */
    public static XStream writeAllFieldCDATA() {
        XStream xstream = new XStream(
                new XppDriver() {
                    public HierarchicalStreamWriter createWriter(Writer out) {
                        return new PrettyPrintWriter(out) {
                            protected void writeText(QuickWriter writer, String text) {
                                super.writeText(writer, PREFIX_CDATA + text + SUFFIX_CDATA);
                            }
                        };
                    }
                }
        );
        return xstream;
    }

    /**
     * <b>XStream生成器</b><br>
     * 说明：<br>
     * Bean中所有文本属性均以&lt;![CDATA[]]&gt;包着写入XML，<br>
     * 而整数、浮点型数据不包&lt;![CDATA[]]&gt;<br>
     *
     * @return XStream实例
     */
    public static XStream writeTextFieldCDATA() {
        XStream xstream = new XStream(
                new XppDriver() {
                    public HierarchicalStreamWriter createWriter(Writer out) {
                        return new PrettyPrintWriter(out) {
                            boolean cdata = false;

                            public void setValue(String text) {
                                if (text != null && !"".equals(text)) {
                                    //浮点型判断
                                    Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
                                    //整型判断
                                    Pattern patternFloat = Pattern.compile("[0-9]+");
                                    //如果是整数或浮点数 就不要加[CDATA[]了
                                    if (patternInt.matcher(text).matches() || patternFloat.matcher(text).matches()) {
                                        cdata = false;
                                    } else {
                                        cdata = true;
                                    }
                                }
                                super.setValue(text);
                            }

                            protected void writeText(QuickWriter writer, String text) {
                                if (cdata) {
                                    writer.write(PREFIX_CDATA);
                                    writer.write(text);
                                    writer.write(SUFFIX_CDATA);
                                } else {
                                    writer.write(text);
                                }
                            }
                        };
                    }
                }
        );

        return xstream;
    }
}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/29.
 */
package com.baguix.utils.file;

import com.baguix.utils.data.Converter;
import com.baguix.utils.data.ValueTool;

import java.io.File;
import java.io.InputStream;

/**
 * <b>类的说明</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class JarFile {
    /**
     * <b>获得JAR包的某个文件</b><br>
     * @param obj 对象
     * @param filePath 相对于对象所在jar包的文件路径
     * @return 文件 （InputStream）
     */
    public static InputStream getJarFileIS(Object obj, String filePath) {
        InputStream is=obj.getClass().getResourceAsStream(filePath);
        return is;
    }

    /**
     * <b>获得JAR包的某个文件</b><br>
     * @param obj 对象
     * @param filePath 相对于对象的文件路径
     * @return webapp根目录
     */
    public static File getJarFile(Object obj, String filePath) {
        InputStream is = getJarFileIS(obj, filePath);
        File file = Converter.is2File(is, filePath);
        return file;
    }
}

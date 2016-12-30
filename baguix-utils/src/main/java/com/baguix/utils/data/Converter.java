/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/29.
 */
package com.baguix.utils.data;

import java.io.*;

/**
 * <b>对象转换器</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class Converter {

    /**
     * <b>String 转为 InputStream</b><br>
     * @param str 要转换的字符串
     * @return 输入流（InputStream）
     */
    public static InputStream str2Is(String str){
        InputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

    /**
     * <b>InputStream 转为 String</b><br>
     * @param is 输入流
     * @return 字符串
     */
    public static String is2Str(InputStream is){
        String result = "";
        if(is!=null) {
            try {
                ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
                byte[] b = new byte[1024];
                int i;
                while ((i = is.read(b)) > 0) {
                    outputstream.write(b, 0, i);
                }
                result = outputstream.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * <b>File 转为 InputStream</b><br>
     * @param file 要转换的文件
     * @return 输入流（InputStream）
     */
    public static InputStream file2Is(File file){
        if(file !=null && file.exists()) {
            try {
                InputStream is = new FileInputStream(file);
                return is;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * <b>InputStream 转为 File</b><br>
     * @param is 输入流
     * @param filePath 文件（File）
     */
    public static File is2File(InputStream is, String filePath){
        File file = null;
        if(filePath!=null && !"".equals(filePath)) {
            try {
                file = new File(filePath);
                OutputStream os = new FileOutputStream(file);
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * <b>int[] 转为 字符串</b><br>
     *     例如： {1,2,3,4,5} - "1,2,3,4,5"
     * @param in 输入流
     * @return String 以英文逗号隔开的字符串
     */
    public static String intArray2Str(int[] in){
        StringBuilder sb = new StringBuilder();
        sb.append(in[0]);
        for(int i=1;i<in.length;i++){
            sb.append(","+in[i]);
        }
        return sb.toString();
    }

}

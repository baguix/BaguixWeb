/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/25.
 */
package com.baguix.utils.security;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * <b>字符串压缩器</b><br>
 *
 * @author Scott(SG)
 */
public class StringCompressor {
    // 单例模式
    private static StringCompressor instance;
    private static StringCompressor getInstance(){
        if(instance == null){
            instance = new StringCompressor();
        }
        return instance;
    }

    // 隐藏构造器
    private StringCompressor(){
    }

    /**
     * <b>使用gzip进行压缩</b><br>
     * 注意：
     * <span style='color:red'> 并非所有的压缩都会使字符串变短，只有重复较多的长字符串压缩才见效。 </span>
     * @param str 要压缩的字符串
     * @return String 已压缩的字符串
     */
    public static String gzip(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Base64 b64  = new Base64();
        String result = b64.encodeToString(out.toByteArray());
        return result;
    }

    /**
     * <b>使用gzip进行解压缩</b>
     *
     * @param compressedstr
     * @return
     */
    public static String ungzip(String compressedstr) {
        String decompressed = "";
        if (compressedstr != null && !"".equals(compressedstr)) {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = null;
            GZIPInputStream ginzip = null;
            byte[] compressed = null;
            try {
                Base64 b64  = new Base64();
                compressed = b64.decode(compressedstr.getBytes());
                in = new ByteArrayInputStream(compressed);
                ginzip = new GZIPInputStream(in);
                byte[] buffer = new byte[1024];
                int offset = -1;
                while ((offset = ginzip.read(buffer)) != -1) {
                    out.write(buffer, 0, offset);
                }
                decompressed = out.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (ginzip != null) {
                    try {
                        ginzip.close();
                    } catch (IOException e) {
                    }
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                    }
                }
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                    }
                }
            }
        }
        return decompressed;
    }
}

package com.baguix.utils.security;

import com.baguix.utils.data.Constants;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Jasypt是个Java类包。
 * 它为开发人员提供一种简单的方式来为项目增加加密功能，包括：密码认证，文本和对象加密。
 * 它可与Spring、Hibernate集成。
 * 与Spring的集成请查看spring.xml配置文件
 * 与Hibernate的集成请查看sys.model.db.core.TUser类。
 * <p/>
 * Description：
 *
 * @author Scott
 *         2014-8-16
 */
public class EncryptTool {
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    // 单例模式
    private static EncryptTool instance;

    private static EncryptTool getInstance() {
        if (instance == null) {
            instance = new EncryptTool();
        }
        return instance;
    }

    // 隐藏构造函数
    private EncryptTool() {

    }

    /**
     * md5/sha-1 加密（不可逆）
     *
     * @param str       要加密的字符串
     * @param algorithm 加密算法名称：md5或者sha-1，不区分大小写
     * @return String加密后的字符串
     */
    public static String encrypt(String str, String algorithm) {
        if (str == null || "".equals(str.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithm == null || "".equals(algorithm.trim())) {
            algorithm = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithm);
            m.update(str.getBytes("UTF8"));
            byte s[] = m.digest();
            return EnDecodeTool.byte2Hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    /**
     * <b>二次加密</b><br>
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     */
    public static String md5AndSha(String str) {
        return sha(md5(str));
    }

    /**
     * <b>md5加密</b><br>
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     */
    public static String md5(String str) {
        return encrypt(str, "md5");
    }

    /**
     * <b>sha1加密</b><br>
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     */
    public static String sha(String str) {
        return encrypt(str, "sha-1");
    }

    /**
     * <b>简单加密（可逆）</b><br>
     * 注意：同一字符串每次运行该加密程序，得到的密文均不同。
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     */
    public static String basicEncrypt(String str) {
        String result = "";
        if (str != null && !"".equals(str)) {
            BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
            textEncryptor.setPassword(Constants.COPYRIGHT + "@" + Constants.AUTHORIZATION);
            result = textEncryptor.encrypt(str);
        }
        return result;
    }

    /**
     * <b>简单解密（可逆）</b><br>
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     * @see EncryptTool#basicEncrypt(String)
     */
    public static String basicDecrypt(String str) {
        String result = "";
        if (str != null && !"".equals(str)) {
            BasicTextEncryptor encryptor = new BasicTextEncryptor();
            encryptor.setPassword(Constants.COPYRIGHT + "@" + Constants.AUTHORIZATION);
            result = encryptor.decrypt(str);
        }
        return result;
    }

    /**
     * <b>强加密（可逆）</b><br>
     * 注意：<br>
     * 需要安装JDK的JCE文件。
     * 下载地址如：<a href="http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html">Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 7</a>
     * 下载后覆盖到JDK目录下的jre/lib/security目录下，替换local_policy.jar和US_export_policy.jar两个文件。<br>
     * 也可从本项目的tools文件夹中获取JDK7的文件。
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     */
    public static String strongEncrypt(String str) {
        String result = "";
        if (str != null && !"".equals(str)) {
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(Constants.COPYRIGHT + "@" + Constants.AUTHORIZATION);
            result = textEncryptor.encrypt(str);
        }
        return result;
    }

    /**
     * <b>强加密（可逆）</b><br>
     *
     * @param str 要加密的字符串
     * @return String加密后的字符串
     */
    public static String strongDecrypt(String str) {
        String result = "";
        if (str != null && !"".equals(str)) {
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(Constants.COPYRIGHT + "@" + Constants.AUTHORIZATION);
            result = textEncryptor.decrypt(str);
        }
        return result;
    }

}

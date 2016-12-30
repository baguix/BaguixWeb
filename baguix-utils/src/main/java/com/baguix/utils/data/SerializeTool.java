package com.baguix.utils.data;

import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;

/**
 * Created by Scott on 2016/12/9.
 */
public class SerializeTool {
    /**
     * 序列化对象
     * @param object 对象
     * @return byte[] 转义后的字符串
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {

        }
        return null;
    }

    //反序列化
    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException | IOException e) {

        }
        return null;
    }
}

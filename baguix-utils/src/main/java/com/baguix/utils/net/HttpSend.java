/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/4.
 */
package com.baguix.utils.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * <b>http提交</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class HttpSend {

    /**
     * <b>提交</b><br>
     *
     * @param str 提交的地址，例如：http://api.smsbao.com/sms?u=username&p=123456&m=13878155452&c=%dd
     * @return 返回的值。
     */
    public String send(String str) throws Exception {
        URL url = new URL(str);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection
                .getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        // 读取返回值，进行判断
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}

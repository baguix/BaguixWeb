/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/26.
 */
package com.baguix.utils.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <b>命令行执行工具类</b><br>
 *
 * @author Scott(SG)
 */
public class CmdTool {
    // 单例模式
    private static CmdTool instance;
    private static CmdTool getInstance() {
        if (instance == null) {
            instance = new CmdTool();
        }
        return instance;
    }

    // 隐藏构造函数
    private CmdTool() {

    }

    public static String exec(String command){
        StringBuffer msg = new StringBuffer();
        try {
            Process process = Runtime.getRuntime().exec("cmd /c "+command);
            InputStreamReader in = new InputStreamReader(process.getInputStream(), "GBK");
            BufferedReader br = new BufferedReader(in);
            String s;
            while ( (s = br.readLine())!= null) {
                s += "\n";
                msg.append(s);
            }
            process.destroy();
            br.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            msg.append("远程执行CMD命令出错");
        }
        return msg.toString();
    }
}

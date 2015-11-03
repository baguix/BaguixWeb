/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/4.
 */
package com.baguix.utils.net.smsbao;

import com.baguix.utils.data.Constants;
import com.baguix.utils.net.HttpSend;
import com.baguix.utils.security.EnDecodeTool;

import java.text.MessageFormat;

/**
 * <b>短信宝接口</b><br>
 * 官方网站： http://www.smsbao.com/
 * 客服电话： 400-009-0465
 * 具体收费情况请参考官方网站。
 *
 * @author Scott(SG)
 * @since 1.0
 */
public class SmsSender {

    /**
     * @param user     在本短信宝平台注册的用户名
     * @param password 平台登录密码
     * @param mobile   目标手机号码，多个手机号码用半角逗号分割
     * @param content  发送内容
     * @return 返回 发送是否成功的提示内容
     */
    public String sendMessage(String user, String password, String mobile, String content) {
        String msg = "";
        // 短信宝要求密码MD5后的值(32位，不区分大小写)
        String pwd = new SmsBaoMD5().Md5(password);
        // 短信宝发送内容要求采用UTF-8 URL ENCODE
        String text = "";
        try {
            EnDecodeTool.URIEncoding(content, Constants.UTF8);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        MessageFormat urlmf = new MessageFormat("http://www.smsbao.com/sms?u={0}&p={1}&m={2}&c={3}");

        String url = urlmf.format(new String[]{user, pwd, mobile, text});
        HttpSend httpSend = new HttpSend();
        try {
            String result = httpSend.send(url);
            int r = Integer.valueOf(result);
            switch (r) {
                case 0:
                    msg = "发送成功";
                    break;
                case 30:
                    msg = "密码错误";
                    break;
                case 40:
                    msg = "账号不存在";
                    break;
                case 41:
                    msg = "余额不足";
                    break;
                case 42:
                    msg = "帐号过期";
                    break;
                case 43:
                    msg = "IP地址限制";
                    break;
                case 50:
                    msg = "内容含有敏感词";
                    break;
                case 51:
                    msg = "手机号码不正确";
                    break;
                default:
                    msg = "未知错误: " + r;
                    break;
            }
        }
        catch (Exception e) {
            throw new SmsException("程序异常导致短信发送失败。");
        }
        return msg;
    }
}

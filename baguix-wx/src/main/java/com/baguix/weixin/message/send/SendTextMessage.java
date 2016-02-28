package com.baguix.weixin.message.send;

import com.baguix.weixin.message.AbstractMessage;

/**
 * 微信:
 * 消息管理----发送消息-被动回复消息----文本消息
 * (注：从服务器发给手机的消息，即服务器发送)
 * Created by ZhiXin9i on 2016/2/22.
 */
public class SendTextMessage extends AbstractMessage {
    /**
     * 回复文本消息:
     * <xml>
     *     <ToUserName><![CDATA[toUser]]></ToUserName>
     *     <FromUserName><![CDATA[fromUser]]></FromUserName>
     *     <CreateTime>12345678</CreateTime>
     *     <MsgType><![CDATA[text]]></MsgType>
     *     <Content><![CDATA[你好]]></Content>
     * </xml>
     */

    private String Content;

    // Setter && Getter
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}

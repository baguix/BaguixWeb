package com.baguix.weixin.message.receive;

import com.baguix.weixin.message.AbstractMessage;

/**
 * 微信:
 * 消息管理----接收消息-接收普通消息----文本消息
 * (注：从手机发给服务器，即服务器接收)
 * Created by ZhiXin9i on 2016/2/22.
 */
public class ReceiveTextMsg extends AbstractMessage {
    /**
     * 接收文本消息
     * <xml>
     *     <ToUserName><![CDATA[toUser]]></ToUserName> —— 开发者微信号
     *     <FromUserName><![CDATA[fromUser]]></FromUserName> —— 发送方帐号（一个OpenID）
     *     <CreateTime>1348831860</CreateTime> —— 消息创建时间 （整型）
     *     <MsgType><![CDATA[text]]></MsgType> —— text
     *     <Content><![CDATA[this is a test]]></Content> —— 文本消息内容
     *     <MsgId>1234567890123456</MsgId> —— 消息id，64位整型
     * </xml>
     *
     */

    private String Content;

    private String MsgId ;

    // Setter && Getter
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getMsgId() {
        return MsgId;
    }

    public void setMsgId(String msgId) {
        MsgId = msgId;
    }
}

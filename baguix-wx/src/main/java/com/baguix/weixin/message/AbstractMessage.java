package com.baguix.weixin.message;

import java.util.Date;

/**
 * 微信消息抽象类
 * Created by ZhiXin9i on 2016/2/22.
 */
public abstract class AbstractMessage {
    /**
     *  当普通微信用户向公众账号发消息时，微信服务器将POST消息的XML数据包到开发者填写的URL上。
     *  根据微信官方文档，消息分为以下7种（2016年2月）：
     *      1.文本消息（MsgType=text）
     *      2.图片消息（MsgType=image）
     *      3.语音消息（MsgType=voice）
     *      4.视频消息（MsgType=video）
     *      5.小视频消息（MsgType=shortvideo）
     *      6.地理位置消息（MsgType=location）
     *      7.链接消息（MsgType=url）
     *
     *  它们的xml数据格式中，共同之处是：
     *  <xml>
     *      <ToUserName><![CDATA[toUser]]></ToUserName>
     *      <FromUserName><![CDATA[fromUser]]></FromUserName>
     *      <CreateTime>1348831860</CreateTime>
     *      <MsgType><![CDATA[text]]></MsgType>
     *      <MsgId>1234567890123456</MsgId>
     *  </xml>
     */

    protected String ToUserName ;
    protected String FromUserName ;
    protected long CreateTime ;
    protected String MsgType ;

    // Setter && Getter
    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}

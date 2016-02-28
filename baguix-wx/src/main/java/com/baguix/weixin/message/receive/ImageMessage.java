package com.baguix.weixin.message.receive;

import com.baguix.weixin.message.AbstractMessage;

/**
 * 微信:图片消息
 * Created by ZhiXin9i on 2016/2/22.
 */
public class ImageMessage extends AbstractMessage {
    /**
     * <xml>
     *     <ToUserName><![CDATA[toUser]]></ToUserName> —— 开发者微信号
     *     <FromUserName><![CDATA[fromUser]]></FromUserName> —— 发送方帐号（一个OpenID）
     *     <CreateTime>1348831860</CreateTime> —— 消息创建时间 （整型）
     *     <MsgType><![CDATA[image]]></MsgType> —— image
     *     <PicUrl><![CDATA[this is a url]]></PicUrl> —— 图片链接
     *     <MediaId><![CDATA[media_id]]></MediaId> —— 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
     *     <MsgId>1234567890123456</MsgId> —— 消息id，64位整型
     * </xml>
     */

    private String PicUrl;
    private String MediaId;

    // Setter && Getter
    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}

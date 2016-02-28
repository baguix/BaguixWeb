package com.baguix.weixin.message.send;

import com.baguix.weixin.message.AbstractMessage;

import javax.mail.search.SentDateTerm;
import java.util.List;

/**
 * 微信:
 * 消息管理----发送消息-被动回复消息----图文消息
 * (注：从服务器发给手机的消息，即服务器发送)
 * Created by ZhiXin9i on 2016/2/22.
 */
public class SendNewsMessage extends AbstractMessage {
    /**
     * 回复图文消息:
     * <xml>
     *     <ToUserName><![CDATA[toUser]]></ToUserName>
     *     <FromUserName><![CDATA[fromUser]]></FromUserName>
     *     <CreateTime>12345678</CreateTime>
     *     <MsgType><![CDATA[news]]></MsgType>
     *     <ArticleCount>2</ArticleCount>
     *     <Articles>
     *         <item>
     *             <Title><![CDATA[title1]]></Title>
     *             <Description><![CDATA[description1]]></Description>
     *             <PicUrl><![CDATA[picurl]]></PicUrl>
     *             <Url><![CDATA[url]]></Url>
     *         </item>
     *         <item>
     *             <Title><![CDATA[title]]></Title>
     *             <Description><![CDATA[description]]></Description>
     *             <PicUrl><![CDATA[picurl]]></PicUrl>
     *             <Url><![CDATA[url]]></Url>
     *         </item>
     *     </Articles>
     * </xml>
     */

    private List<SendNewsItem> Articles;
    private int ArticleCount;

    // Setter && Getter

    public List<SendNewsItem> getArticles() {
        return Articles;
    }

    public void setArticles(List<SendNewsItem> articles) {
        Articles = articles;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }
}

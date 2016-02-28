package com.baguix.web.action.weixin;

import com.baguix.utils.data.ReflectTool;
import com.baguix.utils.doc.XStreamCDATA;
import com.baguix.utils.security.EncryptTool;
import com.baguix.weixin.message.receive.ReceiveTextMsg;
import com.baguix.weixin.message.send.SendNewsItem;
import com.baguix.weixin.message.send.SendNewsMessage;
import com.baguix.weixin.message.send.SendTextMessage;
import com.thoughtworks.xstream.XStream;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by ZhiXin9i on 2016/2/21.
 */

@Controller
@RequestMapping(value = "/weixin")

public class TokenAct {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TokenAct.class);

    //　微信Token配置
    private static final String token = "baguixweb";

    @Autowired
    private  HttpServletRequest request;

    @RequestMapping(method = { RequestMethod.GET }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String weixin(){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if(checkSingature(signature,timestamp,nonce)){
            return echostr;
        }
        return "";
    }

    @RequestMapping(method = { RequestMethod.POST }, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String message(){
        Map<String,String> map  = WXUtils.xml2Map(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");

        if("text".equals(MsgType)){
            SendNewsItem news1 = new SendNewsItem();
            news1.setTitle("新闻1");
            news1.setDescription("新闻摘要1");
            news1.setPicUrl("http://www.baguix.com/images/bg.jpg");
            news1.setUrl("http://www.baguix.com/images/bg.jpg");
            SendNewsItem news2 = new SendNewsItem();
            news2.setTitle("新闻2");
            news2.setDescription("新闻摘要2");
            news2.setPicUrl("http://www.baguix.com/images/bg.jpg");
            news2.setUrl("http://www.baguix.com/images/bg.jpg");
            SendNewsMessage news = new SendNewsMessage();
            List<SendNewsItem> newsList = new ArrayList<>();
            newsList.add(news1);
            newsList.add(news2);
            news.setToUserName(FromUserName);
            news.setFromUserName(ToUserName);
            news.setMsgType("news");
            news.setArticleCount(2);
            news.setArticles(newsList);
            news.setCreateTime(new Date().getTime());

            // 生成xml
            String result;
            XStream xs = XStreamCDATA.initXStream();
            xs.alias("xml", SendNewsMessage.class);
            xs.alias("item", SendNewsItem.class);
            result = xs.toXML(news);
            return result;

//            SendTextMessage msg = new SendTextMessage();
//            msg.setFromUserName(ToUserName);
//            msg.setToUserName(FromUserName);
//            msg.setMsgType("text");
//            msg.setCreateTime(new Date().getTime());
//            msg.setContent("你那好吗?--->"+Content);
//            return WXUtils.obj2XMl(msg);
        }

        return "";
    }

    private boolean checkSingature(String signature, String timestamp, String nonce){
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        StringBuilder sb =  new StringBuilder();
        for(int i=0 ; i<arr.length;i++){
            sb.append(arr[i]);
        }
        String tmp = EncryptTool.sha(sb.toString());
        return tmp.equals(signature);
    }
}

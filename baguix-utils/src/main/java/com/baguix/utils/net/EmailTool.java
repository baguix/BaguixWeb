/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/8/21.
 */
package com.baguix.utils.net;


import com.baguix.utils.doc.PropertiesTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import com.baguix.utils.security.EncryptTool;
import org.apache.commons.mail.HtmlEmail;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <b>Email工具类/b><br>
 * @author Scott(SG)
 */
public class EmailTool {
    public void send(EmailBean info) throws Exception{
        HtmlEmail email = new HtmlEmail();
        email.setHostName(info.getSmtp());
        email.setAuthentication(info.getUser(),info.getPassword());
        List<String> mailto = info.getMailTo();
        for(String to : mailto) {
            email.addTo(to);
        }
        email.setFrom(info.getUser());
        email.setSubject(info.getMailSubject());
        email.setMsg(info.getMailContent());
        email.setCharset("UTF-8");
        email.setSSLOnConnect(true);
        email.setStartTLSEnabled(true);
        email.send();
    }

    public void writeConfig(String smtp, String user, String pwd){
        Map<String,String> map = new HashMap<>();
        map.put("smtp",EncryptTool.strongEncrypt(smtp));
        map.put("user", EncryptTool.strongEncrypt(user));
        map.put("password",EncryptTool.strongEncrypt(pwd));
        PropertiesTool pt = new PropertiesTool();
        String confPath = PathTool.getClassesPath()+ FileManager.getFileSeparator()+"email.properties";
        if(!new File(confPath).exists()){
            FileManager.newTextFile(confPath,"");
        }
        pt.map2File(confPath, map, "Baguix System Email Setting");
    }
}

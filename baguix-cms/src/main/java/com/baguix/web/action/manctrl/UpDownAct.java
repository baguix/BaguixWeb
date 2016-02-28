/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/24.
 */
package com.baguix.web.action.manctrl;

import com.alibaba.fastjson.JSON;
import com.baguix.utils.data.NowDateTool;
import com.baguix.utils.file.FileManager;
import com.baguix.utils.file.PathTool;
import com.baguix.web.model.page.UploadedMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>上传下载Action</b><br>
 *
 * @author Scott(SG)
 */
@Controller
public class UpDownAct {
    private static final Logger logger = LoggerFactory.getLogger(UpDownAct.class);

    @RequestMapping(value = "/manctrl/uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public String uploadSiteWarterMarkImg(@RequestParam("file")MultipartFile[] files, HttpServletRequest request) {
        String uptype = request.getParameter("uptype");
        String path = request.getParameter("path");
        String picwidth = request.getParameter("picwidth");
        String picheight = request.getParameter("picheight");
        String thumbw = request.getParameter("thumbw");
        String thumbh = request.getParameter("thumbh");

        List<String> urls = new ArrayList<String>();
        if(files!=null&&files.length>0){
            // 存储位置前缀
            String webFolder = PathTool.getWebRootPath();
            String url = "/ueditor/jsp/upload/" + path + NowDateTool.getDateFilePath();
            String phyDir= webFolder + url;
            //创建目录位置
            FileManager.newFolder(phyDir);
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                // 文件原名
                String oriFileName = file.getOriginalFilename();
                // 扩展名
                String ext = oriFileName.substring(oriFileName.indexOf("."),oriFileName.length());
                // 生成文件名
                String filename =  "/"+ NowDateTool.now("HH-mm-ss-SSS")+ext;
                //保存文件
                saveFile(file, phyDir + filename);
                urls.add(url  + filename);
            }
        }

        UploadedMsg m = new UploadedMsg();
        m.setSuccess(true);
        m.setUrls(urls);
        return JSON.toJSONString(m);
    }

    private boolean saveFile(MultipartFile file, String savePath) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 转存文件
                file.transferTo(new File(savePath));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/24.
 */
package com.baguix.web.action.manctrl;

import com.alibaba.fastjson.JSON;
import com.baguix.utils.data.ValueTool;
import com.baguix.web.common.cache.SysData;
import com.baguix.web.model.easyui.Messager;
import com.baguix.web.model.page.manctrl.ImageInfo;
import com.baguix.web.model.page.manctrl.SiteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

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
    public String siteInfoSave(@RequestParam("file")MultipartFile[] files, HttpServletRequest request) {
        String uptype = request.getParameter("uptype");
        String path = request.getParameter("path");
        String picwidth = request.getParameter("picwidth");
        String picheight = request.getParameter("picheight");
        String thumbw = request.getParameter("thumbw");
        String thumbh = request.getParameter("thumbh");

        if(files!=null&&files.length>0){
            //循环获取file数组中得文件
            for(int i = 0;i<files.length;i++){
                MultipartFile file = files[i];
                //保存文件
                saveFile(file, path);
            }
        }

        Messager m = new Messager();
        m.setMsg("网站基本信息修改成功！");
        m.setSuccess(true);
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

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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <b>网站设置Action</b><br>
 *
 * @author Scott(SG)
 */
@Controller
public class SiteConfigAct {
    private static final Logger logger = LoggerFactory.getLogger(SiteConfigAct.class);

    @RequestMapping(value = "/manctrl/siteinfo")
    public String siteInfo(Model model) {
        SiteInfo si = SysData.siteInfo;
        Map<String, String> siteinfo = si.toHashMap(si);
        for (Map.Entry<String, String> entry : siteinfo.entrySet()) {
            model.addAttribute(entry.getKey(), ValueTool.null2String(entry.getValue(), ""));
        }
        return "manctrl/setting/siteinfo";
    }

    @RequestMapping(value = "/manctrl/siteinfo-save", method = RequestMethod.POST)
    @ResponseBody
    public String siteInfoSave(SiteInfo si, HttpServletRequest request) {
        si.toXMLFile(si);
        SysData.siteInfo = si;
        ServletContext context = request.getSession().getServletContext();
        Map<String, String> siteinfo = si.toHashMap(si);
        // 遍历siteinfo.properties
        for (Map.Entry<String, String> entry : siteinfo.entrySet()) {
            context.setAttribute(entry.getKey(), entry.getValue());
        }
        logger.debug("系统Application域重新缓存了siteinfo数据。");
        Messager m = new Messager();
        m.setMsg("网站基本信息修改成功！");
        m.setSuccess(true);
        return JSON.toJSONString(m);
    }

    @RequestMapping(value = "/manctrl/imageinfo")
    public String imageInfo(Model model) {
        logger.debug("imageinfo");
        ImageInfo ii = SysData.imageInfo;
        Map<String, String> info = ii.toHashMap(ii);
        for (Map.Entry<String, String> entry : info.entrySet()) {
            model.addAttribute(entry.getKey(), ValueTool.null2String(entry.getValue(), ""));
        }
        return "manctrl/setting/imageinfo";
    }

    @RequestMapping(value = "/manctrl/imageinfo/logoup")
    public String logoUpload(Model model){
        logger.debug("logoUpload");
        ImageInfo ii = SysData.imageInfo;
        Map<String, String> info = ii.toHashMap(ii);
        for (Map.Entry<String, String> entry : info.entrySet()) {
            model.addAttribute(entry.getKey(), ValueTool.null2String(entry.getValue(), ""));
        }
        return "manctrl/setting/thumbup";
    }

    @RequestMapping(value = "/manctrl/imageinfo/logoslector")
    public String logoSlector(Model model){
        logger.debug("logoSlector");
        return "manctrl/setting/thumbslector";
    }

    @RequestMapping(value = "/manctrl/imageinfo-save", method = RequestMethod.POST)
    @ResponseBody
    public String imageInfoSave(ImageInfo ii, HttpServletRequest request) {
        ii.toXMLFile(ii);
        SysData.imageInfo = ii;
        logger.debug("系统重新缓存了imageinfo数据。");
        Messager m = new Messager();
        m.setMsg("图片处理参数修改成功！");
        m.setSuccess(true);
        return JSON.toJSONString(m);
    }
}

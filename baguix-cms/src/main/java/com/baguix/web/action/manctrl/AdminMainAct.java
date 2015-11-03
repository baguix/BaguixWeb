/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/20.
 */
package com.baguix.web.action.manctrl;

import com.alibaba.fastjson.JSON;
import com.baguix.web.common.cache.SysData;
import com.baguix.web.model.db.manctrl.TNavMenu;
import com.baguix.web.model.page.manctrl.LeftTree;
import com.baguix.web.service.manctrl.NavMenuServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>后台main页面的action</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */

@Controller
public class AdminMainAct {
    private static final Logger logger = LoggerFactory.getLogger(AdminMainAct.class);

    @Autowired
    private NavMenuServiceI navMenuService;

    @RequestMapping(value = "/manctrl/main")
    public String login(Model model) {
        model.addAttribute("sitename", SysData.siteInfo.getTitle() + "欢迎您");
        logger.info("用户来访");
        return "manctrl/main";
    }

    @RequestMapping(value = "/manctrl/deflefttree", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deflefttree() {
        String json = SysData.lefttree;
        return json;
    }

    @RequestMapping(value = "/manctrl/lefttree", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String lefttree(String pid) {
        String json = JSON.toJSONString(navMenuService.getLeftTree(pid));
        return json;
    }

    @RequestMapping(value = "/manctrl/tree_ptext", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String leftTreeByPText(String ptext) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("Text", ptext);
        TNavMenu m =  navMenuService.getByHql("from TNavMenu t where t.title = :Text ", params);
        String json = JSON.toJSONString(m.getParent().getId());
        return json;
    }
}

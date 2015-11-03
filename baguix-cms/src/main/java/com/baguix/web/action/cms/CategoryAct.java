/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/10/18.
 */
package com.baguix.web.action.cms;

import com.alibaba.fastjson.JSON;
import com.baguix.web.model.db.cms.TCategory;
import com.baguix.web.model.easyui.Messager;
import com.baguix.web.model.page.cms.Category;
import com.baguix.web.service.cms.CategoryServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>文章栏目Action</b><br>
 *
 * @author Scott(SG)
 * @since 1.0
 */
@Controller
public class CategoryAct {
    private static final Logger logger = LoggerFactory.getLogger(CategoryAct.class);
    @Autowired
    private CategoryServiceI categoryService;

    @RequestMapping(value = "/manctrl/cms/category")
    public String category(Model model) {
        logger.debug("文章栏目");
        return "manctrl/category/manager";
    }

    @RequestMapping(value = "/manctrl/cms/category-list")
    @ResponseBody
    public String categoryList(Model model) {
        TCategory troot = new TCategory();
        troot = categoryService.getById("news");
        Category root = new Category();
        categoryService.changeModel(troot, root);
        root.setCtime(null);
        root.setIconCls("icon-home");
        root.setChildren(categoryService.getSubCategoryTree("news"));
        List<Category> rl = new ArrayList();
        rl.add(root);
        String json = JSON.toJSONStringWithDateFormat(rl, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    @RequestMapping(value = "/manctrl/cms/category-switch")
    @ResponseBody
    public String categorySwitch(Model model) {
        Messager m = new Messager();
        m.setMsg("栏目新增成功！");
        m.setSuccess(true);
        return JSON.toJSONString(m);
    }

    @RequestMapping(value = "/manctrl/cms/category-save")
    @ResponseBody
    public String categorySave(Model model) {
        Messager m = new Messager();
        m.setMsg("栏目新增成功！");
        m.setSuccess(true);
        return JSON.toJSONString(m);
    }

    @RequestMapping(value = "/manctrl/cms/category-update")
    @ResponseBody
    public String categoryUpate(Model model) {
        Messager m = new Messager();
        m.setMsg("栏目修改成功！");
        m.setSuccess(true);
        return JSON.toJSONString(m);
    }

    @RequestMapping(value = "/manctrl/cms/category-delete")
    @ResponseBody
    public String categoryDelete(Model model) {
        Messager m = new Messager();
        m.setMsg("栏目删除成功！");
        m.setSuccess(true);
        return JSON.toJSONString(m);
    }
}

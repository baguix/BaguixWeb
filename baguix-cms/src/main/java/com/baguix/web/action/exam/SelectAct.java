package com.baguix.web.action.exam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baguix.web.action.BaseAct;
import com.baguix.web.common.cache.SysData;
import com.baguix.web.model.db.exam.TOption;
import com.baguix.web.model.db.exam.TSelect;
import com.baguix.web.model.page.core.DictItem;
import com.baguix.web.model.page.exam.Option;
import com.baguix.web.model.page.exam.Select;
import com.baguix.web.service.exam.SelectServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Scott on 2016/3/26.
 */

@Controller
public class SelectAct extends BaseAct {
    private static final Logger logger = LoggerFactory.getLogger(SelectAct.class);
    @Autowired
    private SelectServiceI service;

    // 试题管理页
    @RequestMapping(value = "exam/question/manager")
    public String manager(){
        return "exam/question";
    }

    // 单选题管理页
    @RequestMapping(value = "exam/question/select/list")
    @ResponseBody
    public String selectManager(Select select){
        List<Select> list = service.listAll(select.getKid());
        String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    // 单选题新增页
    @RequestMapping(value = "exam/question/select/add")
    public String selectAdd(@RequestParam("kid") String kid, @RequestParam("kids") String kids, Model model){
        model.addAttribute("kid",kid);
        model.addAttribute("kids",kids);
        return "exam/question/select/add";
    }

    // 单选题修改页
    @RequestMapping(value = "exam/question/select/edit")
    public String selectEdit(Model model, String id){
        Select select = service.getSelect(id);
        model.addAttribute(select);
        return "exam/question/select/edit";
    }

    // 单选题新增处理
    @RequestMapping(value = "exam/question/select/add_deal")
    @ResponseBody
    public String selectAddDeal(Select select) throws UnsupportedEncodingException {
        String json;
        String o = select.getOptionstr();
        if(o!=null ) {
            String opt = UriUtils.decode(select.getOptionstr(), "UTF-8");
            List<Option> options = JSONArray.parseArray(opt, Option.class);
            select.setOption(options);
            select = service.add(select);
            json = getMessager("新增试题成功!",select);
        }else{
            json = getMessager("选项为空，请查看问题",null);
        }
        return json;
    }

    // 单选题编辑处理
    @RequestMapping(value = "exam/question/select/edit_deal")
    @ResponseBody
    public String selectEditDeal(Select select) throws UnsupportedEncodingException {
        String json;
        String o = select.getOptionstr();
        if(o!=null ) {
            String opt = UriUtils.decode(select.getOptionstr(), "UTF-8");
            List<Option> options = JSONArray.parseArray(opt, Option.class);
            select.setOption(options);
            select = service.edit(select);
            json = getMessager("修改试题成功!",select);
        }else{
            json = getMessager("选项为空，请查看问题",null);
        }
        return json;
    }

    // 单选题选项datagrid
    @RequestMapping(value = "exam/question/select/options")
    @ResponseBody
    //public String selectOptions(Select select) throws UnsupportedEncodingException {
    public String selectOptions(String id) throws UnsupportedEncodingException {
        List<Option> opts= service.getOptions(id);
        String json = JSON.toJSONStringWithDateFormat(opts, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    // 单选题管理页
    @RequestMapping(value = "exam/question/select/view")
    public String selectViewer(@RequestParam("id") String id, Model model){
        String html = service.viewQPContent(id, 0);
        model.addAttribute("content", html);
        return "exam/question/select/view";
    }
}

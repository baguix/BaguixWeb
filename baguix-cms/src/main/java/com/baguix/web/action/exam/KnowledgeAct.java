package com.baguix.web.action.exam;

import com.alibaba.fastjson.JSON;
import com.baguix.utils.doc.FreemarkerTool;
import com.baguix.utils.file.PathTool;
import com.baguix.web.action.BaseAct;
import com.baguix.web.model.page.exam.Knowledge;
import com.baguix.web.service.exam.KnowledgeServiceI;
import freemarker.template.TemplateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;

/**
 * Created by Scott on 2016/3/26.
 */

@Controller
public class KnowledgeAct extends BaseAct {
    private static final Logger logger = LoggerFactory.getLogger(KnowledgeAct.class);
    @Autowired
    private KnowledgeServiceI service;

    // 知识点管理页
    @RequestMapping(value = "exam/knowledge/manager")
    public String manager(){
        return "exam/knowledge";
    }

    // 知识点列表json
    @RequestMapping(value = "exam/knowledge/list")
    @ResponseBody
    public String list(){
        Knowledge root = new Knowledge();
        root.setId("000");
        root.setText("全部知识点");
        root.setLevel(-1);
        root.setIconCls("icon-quickstart");
        root.setChildren(service.getTree(null));
        List<Knowledge> tree = new ArrayList<>();
        tree.add(root);
        String json = JSON.toJSONStringWithDateFormat(tree, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    // 增加知识点
    @RequestMapping(value = "exam/knowledge/add")
    @ResponseBody
    public String add(Knowledge knowledge){
        knowledge.setCtime(new Date());
        service.add(knowledge);
        String json = getMessager("知识点成功增加!",null);
        return json;
    }

    // 修改知识点
    @RequestMapping(value = "exam/knowledge/edit")
    @ResponseBody
    public String edit(Knowledge knowledge){
        service.edit(knowledge);
        String json = getMessager("知识点成功修改!",null);
        return json;
    }

    // 删除知识点
    @RequestMapping(value = "exam/knowledge/del")
    @ResponseBody
    public String del( @RequestParam("id") String id){
        service.del(id);
        String json = getMessager("知识点成功删除!",null);
        return json;
    }

    // 导出知识点
    @RequestMapping(value = "exam/knowledge/export")
    @ResponseBody
    public String export(Model model){
        List<Knowledge> tree = service.getTree(null);
        String html = getHTML(tree);
        Map<String,String> m = new HashMap<String,String>();
        m.put("knowledge",html);
        FreemarkerTool ft = new FreemarkerTool();
        String page = PathTool.getWebRootPath()+"/tk/knowledge.html";
        String template = PathTool.getClassesPath()+"/fm_template/exam/knowledge.ftl";
        try {
            ft.genFileByFTL(template,m,page);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        model.addAttribute("knowledge",html);
        String json = getMessager("知识点已经成功导出为网页，是否察看？",null);
        return json;
    }

    private String getHTML(List<Knowledge> tree){
        StringBuilder html = new StringBuilder();
        for(Knowledge k : tree){
            int level = k.getLevel()>7?8:k.getLevel()+1;
            html.append("<div class='title"+level+"'>");
            html.append(k.getText());
            html.append("</div>");
            List<Knowledge> child = k.getChildren();
            if(child!=null && child.size()>0){
                html.append(getHTML(child));
            }else{
                html.append("<div>");
                html.append(k.getContent());
                html.append("</div>");
            }
        }
        return html.toString();
    }
}

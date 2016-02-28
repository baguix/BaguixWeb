/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/24.
 */
package com.baguix.web.action.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baguix.utils.security.EnDecodeTool;
import com.baguix.web.action.BaseAct;
import com.baguix.web.model.db.core.TDict;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.model.easyui.DataGrid;
import com.baguix.web.model.easyui.DataList;
import com.baguix.web.model.easyui.Messager;
import com.baguix.web.model.page.core.Dict;
import com.baguix.web.model.page.core.DictClass;
import com.baguix.web.model.page.core.DictItem;
import com.baguix.web.service.core.DictClassServiceI;
import com.baguix.web.service.core.DictItemServiceI;
import com.baguix.web.service.core.DictServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>字典管理Action</b><br>
 *
 * @author Scott(SG)
 */
@Controller
public class DictAct extends BaseAct {
    private static final Logger logger = LoggerFactory.getLogger(DictAct.class);
    @Autowired
    private DictServiceI dictService;
    @Autowired
    private DictClassServiceI dictClassService;

    @Autowired
    private DictItemServiceI dictItemService;

    @RequestMapping(value = "/core/dict/manager")
    public String dictManager() {
        return "core/dict/manager";
    }

    @RequestMapping(value = "/core/dict/add")
    public String dictAdd() {
        return "core/dict/add";
    }

    @RequestMapping(value = "/core/dict/edit")
    public String dictEdit(@RequestParam("id") String id, @RequestParam("index")int index,Model model) {
        if( id!=null && !"".equals(id)){
            TDict t = dictService.getById(id);
            Dict dict = new Dict();
            BeanUtils.copyProperties(t, dict,"dcid");
            dict.setDcid(t.getDictClass().getId());
            dict.setDctitle(t.getDictClass().getTitle());
            model.addAttribute(dict);
        }
        return "core/dict/edit";
    }

    /**
     * 列表显示 Action
     */
    @RequestMapping(value = "/core/dict/class", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictClass() {
        List<DictClass> list = dictClassService.list();
        String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    @RequestMapping(value = "/core/dict/list", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictList(Dict dict) {
        DataGrid grid = dictService.datagrid(dict);
        String json = JSON.toJSONStringWithDateFormat(grid, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    @RequestMapping(value = "/core/dict/items", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictItems(DictItem dictItem) {
        //DataGrid grid = dictItemService.datagrid(dictItem);
        List<DictItem> grid=dictItemService.getTree(dictItem.getDictid(),null);

        DictItem root = new DictItem();
        root.setId("000");
        root.setTitle("字典");
        root.setLevel(-1);
        root.setIconCls("icon-dictionary");
        root.setChildren(grid);
        List<DictItem> tree = new ArrayList<DictItem>();
        tree.add(root);
        String json = JSON.toJSONStringWithDateFormat(tree, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    @RequestMapping(value = "/core/dict/type", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictType() {
        List<TDictItem> list = dictItemService.find("from TDictItem as t where t.dict.id='dicttype' order by rank asc");
        List<DataList> dl = new ArrayList<DataList>();
        for (TDictItem e : list){
            DataList d = new DataList();
            d.setId(e.getValue());
            d.setText(e.getTitle());
            dl.add(d);
        }
        String json = JSON.toJSONStringWithDateFormat(dl, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    /**
     * 树型组合框要加载的数据
     * @param dictId 字典名称
     * @return json格式的响应
     */
    @RequestMapping(value = "/core/dict/data/{dictId}", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictData(@PathVariable("dictId") String dictId) {
        List<DictItem> list = dictItemService.getTree(dictId,null);
        String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
        return json;
    }

    /**
     * 处理 Action
     */
    @RequestMapping(value = "/core/dict/add_deal", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictAddDeal(Dict dict) {
        Dict d = dictService.add(dict);
        String msg = getMessager("成功新增字典!",d);
        return msg;
    }

    @RequestMapping(value = "/core/dict/edit_deal", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictEditDeal(Dict dict) {
        Dict d = dictService.edit(dict);
        String msg = getMessager("成功编辑字典!",d);
        return msg;
    }

    @RequestMapping(value = "/core/dict/del_deal", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String dictDelDeal(@RequestParam("id") String id) {
        dictService.delete(id);
        String msg = getMessager("成功删除字典!",null);
        return msg;
    }

    @RequestMapping(value = "/core/dict/itemsave", produces="text/html;charset=UTF-8")
    @ResponseBody
    public String itemSave(@RequestParam("e") String e, @RequestParam("d") String d) throws UnsupportedEncodingException {
        String erows = UriUtils.decode(e,"UTF-8");
        String drows = UriUtils.decode(d,"UTF-8");

        // 编辑
        if(erows != null && !"".equals(erows) && !"[]".equals(erows)) {
            List<DictItem> list = JSONArray.parseArray(erows, DictItem.class);
            for (DictItem di : list) {
                dictItemService.addOrEdit(di);
            }
        }
        // 删除
        if(drows != null && !"".equals(drows) && !"[]".equals(drows)) {
            List<DictItem> list = JSONArray.parseArray(drows, DictItem.class);
            for (DictItem di : list) {
                dictItemService.remove(di.getId());
            }
        }
        String json = getMessager("字典项成功保存!",null);
        return json;
    }
}

package com.baguix.web.taglib.easyui;

import com.alibaba.fastjson.JSON;
import com.baguix.web.model.db.core.TDictItem;
import com.baguix.web.model.page.core.DictItem;
import com.baguix.web.service.core.DictItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

public class DictionaryTemplate {
    private DictItemServiceI dictItemService;

    // 类型：单选列表（如：●是  ○否）
    public String getRadioListCode(String dictId) {
        StringBuffer code = new StringBuffer();
        // 只选第一层
        List<DictItem> list = dictItemService.getTreeByLevel(dictId,null,1);
        int i = 0;
        for (DictItem di : list) {
            i++;
            code.append("<label><input type='radio' id='[$id$]_" + i
                    + "' name='[$name$]' value='" + di.getValue() + "' style='[$style$]' />" + di.getTitle()
                    + "</label>");
            code.append("&nbsp;\n");
        }
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = String([$init$]);");
        code.append("$(\"input[name='[$name$]']\").each(function(){");
        code.append("if( [$name$]_value==$(this).val() ){");
        code.append("$(this).prop('checked', true);");
        code.append("}else{");
        code.append("$(this).prop('checked',false);");
        code.append("}});");
        code.append("</script>");
        code.append("initEnd");
        return code.toString();
    }

    // 类型：多选列表（如：■男装、■红色、□衬衣）
    public String getCheckboxListCode(String dictId){
        StringBuffer code = new StringBuffer();
        // 只选第一层
        List<DictItem> list = dictItemService.getTreeByLevel(dictId,null,1);
        int i=0;
        for(DictItem di : list){
            i++;
            code.append("<label><input type='checkbox' id='[$id$]_"+i+"' name='[$name$]' value='"+di.getValue()+"' style='[$style$]' />"+di.getTitle()+"</label>");
            code.append("&nbsp;\n");
        }
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = String([$init$]).split(',');");
        code.append("$(\"input[name='[$name$]']\").each(function(){");
        code.append("if( SS_inarray([$name$]_value, $(this).val()) ){");
        code.append("$(this).prop('checked', true);");
        code.append("}else{");
        code.append("$(this).prop('checked',false);");
        code.append("}});");
        code.append("</script>");
        return code.toString();
    }

    //ComboBox单选(EasyUI风格)
    public String getSingleComboboxCode(String dictId){
        StringBuffer code = new StringBuffer();
        code.append("<input id='[$id$]' name='[$name$]' style='[$style$]' class='easyui-combobox' data-options=\"valueField: 'value', textField: 'text',panelHeight:'auto',data:");

        //取数据
        code.append("[");
        // 只选第一层
        List<DictItem> list = dictItemService.getTreeByLevel(dictId,null,1);

        for(DictItem di : list){
            code.append("{'value':'");
            code.append(di.getValue());
            code.append("','text':'");
            code.append(di.getTitle());
            code.append("'}");
        }
        code.append("]");
        code.append("\" />");
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = [$init$];");
        code.append("$('#[$id$]').combobox({'value': [$name$]_value});");
        code.append("</script>");
        return code.toString().replace("}{", "},{");
    }

    //ComboBox多选(EasyUI风格)
    public String getMultiComboboxCode(String dictId){
        StringBuffer code = new StringBuffer();
        code.append("<input id='[$id$]' name='[$name$]' style='[$style$]' class='easyui-combobox'");
        code.append("data-options=\"multiple:true, valueField: 'value', textField: 'text',panelHeight:'auto',data:");

        //取数据
        code.append("[");
        // 只选第一层
        List<DictItem> list = dictItemService.getTreeByLevel(dictId,null,1);
        for(DictItem di : list){
            code.append("{'value':'");
            code.append(di.getValue());
            code.append("','text':'");
            code.append(di.getTitle());
            code.append("'}");
        }
        code.append("]");
        code.append("\" />");
        code.append("initBegin");
        code.append("<script type='text/javascript'>");
        code.append("[$name$]_value = [$init$];");
        code.append("$('#[$id$]').combobox({'value': [$name$]_value});");
        code.append("</script>");
        return code.toString().replace("}{", "},{");
    }

    //ComboBoxTree单选(EasyUI风格)
    public String getSingleComboTreeCode(String dictId){
        StringBuffer code = new StringBuffer();
        List<DictItem> list = dictItemService.getTree(dictId,null);
        String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss");
        code.append("<select id=\"cc\" class=\"easyui-combotree\" style=\"width:200px;\"");
        code.append("data-options=\"");
        code.append("url:''");
        code.append("\"");
        code.append("></select>");
        return code.toString();
    }

    //ComboBoxTree多选(EasyUI风格)
    public String getMultiComboTreeCode(String dictId){
        return "";
    }

    public DictItemServiceI getDictItemService() {
        return dictItemService;
    }

    public void setDictItemService(DictItemServiceI dictItemService) {
        this.dictItemService = dictItemService;
    }
}

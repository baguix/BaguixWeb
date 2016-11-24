package com.baguix.web.action.exam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baguix.web.action.BaseAct;
import com.baguix.web.model.page.exam.MultiSelect;
import com.baguix.web.model.page.exam.Option;
import com.baguix.web.model.page.exam.Select;
import com.baguix.web.service.exam.SelectServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by Scott on 2016/3/26.
 */

@Controller
public class MultiSelectAct extends BaseAct {
    private static final Logger logger = LoggerFactory.getLogger(MultiSelectAct.class);


    // 多选题管理页
    @RequestMapping(value = "exam/question/multiselect/list")
    @ResponseBody
    public String multiselectManager(MultiSelect multiSelect){
        return "";
    }
}

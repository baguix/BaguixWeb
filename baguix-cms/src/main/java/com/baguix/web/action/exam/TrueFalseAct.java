package com.baguix.web.action.exam;

import com.baguix.web.action.BaseAct;
import com.baguix.web.model.page.exam.MultiSelect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Scott on 2016/3/26.
 */

@Controller
public class TrueFalseAct extends BaseAct {
    private static final Logger logger = LoggerFactory.getLogger(TrueFalseAct.class);


    // 判断题管理页
    @RequestMapping(value = "exam/question/truefalse/list")
    @ResponseBody
    public String trueFalseManager(){
        return "";
    }
}

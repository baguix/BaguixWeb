package com.baguix.web.action.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Scott on 2016/3/26.
 */

@Controller
public class ExamMainAct {
    private static final Logger logger = LoggerFactory.getLogger(ExamMainAct.class);
    //普通的action
    @RequestMapping(value = "exam/main")
    public String login(Model model){
        return "exam/main";
    }
}

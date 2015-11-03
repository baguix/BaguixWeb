package com.baguix.web.action;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.baguix.web.model.page.testMsg;

import javax.validation.Valid;

/**
 * Created by Administrator on 2015/7/10.
 */

@RestController
public class restAct {
    @RequestMapping("/hello/{player:[a-z]{1,3}}/{id}")
    @ResponseBody
    public testMsg tdo(@PathVariable String player,@PathVariable @Valid String id){
        testMsg msg = new testMsg(player,"您好"+player);
        return msg;
    }
}

/*
 * Copyright(c) www.baguix.com, All Rights Reserved!
 * Created by BaguixStudio on 2015/9/24.
 */
package com.baguix.web.action.manctrl;

import com.google.code.kaptcha.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * <b>登录 Action 类</b><br>
 *
 * @author Scott(SG)
 */
@Controller
public class LoginAct {
    private static final Logger logger = LoggerFactory.getLogger(AdminMainAct.class);

    @RequestMapping(value="/manctrl/login", method= RequestMethod.POST)
    public String captchatest(String authcode, HttpServletRequest request){
        String code = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        logger.debug("post={} code={}",authcode,code);
        if(authcode != null && code.equals(authcode)) {
            String uid = request.getParameter("username");
            String pwd = request.getParameter("password");
            try {
                SecurityUtils.getSubject().login(new UsernamePasswordToken(uid, pwd));
            }
            catch (AuthenticationException e){
                logger.debug("用户和密码不正确：{}",uid);
                return "login";
            }
        }
        return "manctrl/go";
    }
}

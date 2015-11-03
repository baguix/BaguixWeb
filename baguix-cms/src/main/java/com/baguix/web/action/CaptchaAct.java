package com.baguix.web.action;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * Created by Scott on 2015/7/25.
 */

@Controller
@RequestMapping("/")
public class CaptchaAct {
    private Producer captchaProducer = null;
    private static final Logger logger = LoggerFactory.getLogger(CaptchaAct.class);

    @Autowired
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }

    @RequestMapping("/captcha")
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }

    @RequestMapping(value = "/captchatest", method = RequestMethod.POST)
    public String captchatest(String kaptcha, HttpServletRequest request) {
        Object syscode = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (syscode != null) {
            String code = (String) syscode;
            logger.info("post={} code={}", kaptcha, code);
        }
        return "main";
    }
}

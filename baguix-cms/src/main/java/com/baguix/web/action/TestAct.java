package com.baguix.web.action;

import com.baguix.web.common.cache.SysData;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import com.baguix.web.model.page.UserLoginBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Scott on 2015/7/8.
 */

@Controller

public class TestAct {

    private static final Logger logger = LoggerFactory.getLogger(TestAct.class);
    //普通的action
    @RequestMapping(value = "/login")
    public String login(Model model){
        model.addAttribute("sitename", SysData.siteInfo.getTitle()+"欢迎您");
        logger.info("用户来访");
        return "login";
    }

    // 接收来自客户端的Form提交model方式
    @RequestMapping(value = "/auth")
    public String auth(UserLoginBean user){
        logger.info("登录类ID={}",user.getUsername());
        return "main";
    }

    // 向客户端输出文字
    //@RequestMapping(value = "/back", produces="text/html;charset=UTF-8")
    @RequestMapping(value = "/back")
    @ResponseBody
    public String back(){
        return "自定义的内容";
    }

    // 跳向下一action地址(带少量参数, 自定义地址), go-to
    @RequestMapping("/go/{path:[a-z]{1,3}}")
    public String go(@PathVariable String path){
        return "redirect:/to/{path}";
    }

    @RequestMapping("/to/{path:[a-z]{1,3}}")
    @ResponseBody
    public String to(@PathVariable String path){
        return "跳到了:"+path;
    }

    // 跳向下一action地址(带多个参数, 刷新后被清空), go-to
    @RequestMapping("/goto")
    public String go_to(RedirectAttributesModelMap model){
        UserLoginBean user = new UserLoginBean();
        user.setUsername("中文名");
        user.setPassword("password");
        // 导入map
        model.addFlashAttribute("user", user);
        model.addFlashAttribute("message","消息");
        logger.info(model.getFlashAttributes().get("message").toString());
        return "redirect:/from";
    }

    @RequestMapping("/from")
    @ResponseBody
    public UserLoginBean from(@ModelAttribute("user") UserLoginBean user, @ModelAttribute("message") String msg){
        logger.info(user.getUsername());
        logger.info(msg);
        return user;
    }

    @RequestMapping("/upload")
    public String upload(){
        return "upload";
    }

    @RequestMapping("/upfile")
    @ResponseBody
    public String upfile(@RequestParam("desc") String desc, @RequestParam("file")MultipartFile file){
        try{
            if(!file.isEmpty()){
                logger.info("{}上传中",desc);
                file.transferTo(new File("d:/fileupload/"+file.getOriginalFilename()));
                return "上传成功";
            }
        }
        catch (IllegalStateException e){
            logger.error("非法状态错误");
            e.printStackTrace();
        }
        catch ( IOException e){
            logger.error("文件I/O错误");
            e.printStackTrace();
        }
        return "上传失败";
    }

    // 文件的下载
    @RequestMapping("download")
    public ResponseEntity<byte[]> download() throws IOException {
        File file = new File("d:/PLSQLDeveloper.zip");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "dict.txt");
        headers.setContentLength(file.length());
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
                headers, HttpStatus.CREATED);
    }



    // 像servlet一样使用HttpServletRequest和HttpServletResponse
    @RequestMapping("/sev")
    public String sev(HttpServletRequest request, HttpServletResponse response){
        String  id = request.getParameter("id");
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter out = response.getWriter();
            out.write(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

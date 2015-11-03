package com.baguix.web.model.page;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Scott on 2015/7/11.
 */
public class UserLoginBean implements Serializable {
    // 用户名
    private String username;
    // 密码
    private String password;
    // 验证码
    private String vercode;

    // 构造器
    public UserLoginBean(){

    }
    public UserLoginBean(String username, String password, String vercode) {
        this.username = username;
        this.password = password;
        this.vercode = vercode;
    }

    // Setter and Getter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVercode() {
        return vercode;
    }

    public void setVercode(String vercode) {
        this.vercode = vercode;
    }
}

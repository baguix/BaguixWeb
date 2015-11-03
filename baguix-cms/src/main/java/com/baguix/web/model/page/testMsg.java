package com.baguix.web.model.page;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/10.
 */
public class testMsg  implements Serializable {

    @Size(min=0, max=2, message = "姓名长度3-30")
    private String name;
    @Size(min=20, max=22, message = "文字长度不超过")
    private String text;

    public testMsg(String name, String text) {
        this.name = name;
        this.text = text;
    }

    // Setter and Getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

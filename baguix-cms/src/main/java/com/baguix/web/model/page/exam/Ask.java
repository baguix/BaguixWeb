package com.baguix.web.model.page.exam;

import java.io.Serializable;

/**
 * 问题
 * Created by Scott on 2016/3/19.
 */


public class Ask implements Serializable {
    //序列化ID
    private static final long serialVersionUID = 48L;

    // 标识符
    private String id;
    //内容
    private String content;
    //答案
    private String answer;

    // Setter && Getter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

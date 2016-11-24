package com.baguix.web.model.page.exam;


/**
 * 选择题的选项
 * Created by Scott on 2016/3/19.
 */
public class Option {
    //序列化ID
    private static final long serialVersionUID = 48L;
    // 标识符
    private String id;
    // 选项编号: A、B、C、D、E
    private String text;
    // 选项文字/内容
    private String content;
    // 是否答案
    private boolean selected;
    // 答案字符串
    private String  answer;
    // 摘要文字
    private String  summary;

    // Getter && Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean getSelected() {
        return selected;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}

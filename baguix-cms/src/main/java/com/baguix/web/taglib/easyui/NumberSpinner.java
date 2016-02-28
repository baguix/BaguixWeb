package com.baguix.web.taglib.easyui;

/**
 * Created by ZhiXin9i on 2016/2/5.
 */
public class NumberSpinner  extends BaseEasyUIInput {
    private static final long serialVersionUID = 48L;
    private String required;
    public int doStartTag() {
        try {
            String html = getUiHtml("numberspinner");
            pageContext.getOut().write(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.EVAL_BODY_BUFFERED;
    }

    //Setter && Getter
    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }
}

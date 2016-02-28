package com.baguix.web.taglib.easyui;

/**
 * Created by ZhiXin9i on 2016/2/4.
 */
public class TextBox extends BaseEasyUIInput {
    private static final long serialVersionUID = 48L;

    public int doStartTag() {
        try {
            String html = getUiHtml("textbox");
            pageContext.getOut().write(html);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.EVAL_BODY_BUFFERED;
    }
}

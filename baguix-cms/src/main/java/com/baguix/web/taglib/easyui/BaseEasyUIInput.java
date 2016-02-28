package com.baguix.web.taglib.easyui;

import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * Created by ZhiXin9i on 2016/2/5.
 */
public abstract class BaseEasyUIInput extends BodyTagSupport {
    protected String id="";
    protected String val="";
    protected String dataoption="";
    protected String css="";
    protected String attr="";

    protected String getUiHtml(String componentName){
        StringBuilder html  = new StringBuilder();
        html.append("<input class=\"easyui-"+componentName+"\" ");
        html.append("id=\""+getId()+"\" name=\""+getId()+"\" ");
        if (dataoption!=null && !"".equals(dataoption)){
            html.append("data-options=\""+getDataoption()+"\" ");
        }
        if (val!=null && !"".equals(val)){
            html.append("value=\""+getVal()+"\" ");
        }
        if (attr!=null && !"".equals(attr)){
            html.append(getAttr());
        }
        if (css!=null && !"".equals(css)){
            html.append("style=\""+getCss()+"\" ");
        }else{
            html.append("style=\"width:100%;\" ");
        }
        html.append("/>");
        return html.toString();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getDataoption() {
        return dataoption;
    }

    public void setDataoption(String dataoption) {
        this.dataoption = dataoption;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }
}

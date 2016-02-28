package com.baguix.web.taglib.easyui;

import java.util.UUID;

/**
 * Created by Scott on 2016/2/4.
 * 使用方法：
 * <script>
 *     var tttcolor=[
 *     "EEEEEE", "DDDDDD", "CCCCCC", "BBBBBB", "999999", "000000",
 *     "FF99FF", "CC00FF", "CC6600", "FF3399", "CC3399", "990099"
 *     ];
 * </script>
 * <ss:euColorBox id="ttt" val="#ffff00" colors="tttcolor"/>
 * 或用默认颜色组：
 * <ss:euColorBox id="markTextColor" val="#ffff00"/>
 */
public class ColorBox extends BaseEasyUIInput {
    private static final long serialVersionUID = 48L;
    private String colors = "";

    public int doStartTag() {
        try {
            setCss("width:100px");
            StringBuilder html=new StringBuilder();
            String cpName =UUID.randomUUID().toString();
            html.append(getUiHtml("textbox"));
            html.append("<input type=\"text\" id=\""+cpName+"\" name=\""+cpName+"\"" +"/>");
            html.append("<script type=\"text/javascript\">");
            String colorArray = "";
            if(colors!=null && "".equals(colors)) {
                html.append("   var " + getId() + "Colors=[\n" +
                        "            \"EEEEEE\", \"DDDDDD\", \"CCCCCC\", \"BBBBBB\", \"999999\", \"000000\",\n" +
                        "            \"FF0000\", \"00FF00\", \"0000FF\", \"FFFF00\", \"00FFFF\", \"FF00FF\",\n" +
                        "            \"FFCC00\", \"FF9900\", \"FF3300\", \"FF0000\", \"CC0000\", \"CC6600\",\n" +
                        "           \"99CC00\", \"99CC99\", \"00CC00\", \"009900\", \"999900\", \"339999\",\n" +
                        "            \"00CCFF\", \"0099FF\", \"0033FF\", \"0000CC\", \"3399CC\", \"663399\",\n" +
                        "            \"FF99FF\", \"CC00FF\", \"CC6600\", \"FF3399\", \"CC3399\", \"990099\"\n" +
                        "        ];");
                colorArray = getId()+"Colors";
            }
            else{
                colorArray = getColors();
            }
            html.append("$('#"+cpName+"').colorPicker({");
            html.append("   pickerDefault: '" + getVal() + "'," );
            html.append("   colors: "+colorArray+",");
            html.append("   showHexField: false});");
            html.append("$('#"+cpName+"').change(function(){");
            html.append("    $('#"+getId()+"').textbox('setValue',$('#"+cpName+"').val());");
            html.append(" });");
            html.append("</script>");
            pageContext.getOut().write(html.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.EVAL_BODY_BUFFERED;
    }
    // Setter && Getter
    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }
}

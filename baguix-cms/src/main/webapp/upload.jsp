<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="org.apache.commons.codec.binary.Base64" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    String file = request.getParameter("image");
    String[] a = file.split(",");
    String image = a[1];
    if (image != null) {
        try {
            //Base64解码
            byte[] b = Base64.decodeBase64(image);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmms");
            String filename = fmt.format(new Date());
            String imgFilePath = "d:/" + filename + ".jpg";
            OutputStream fout = new FileOutputStream(imgFilePath);
            fout.write(b);
            fout.flush();
            fout.close();
        } catch (Exception e) {
            out.println(e.getStackTrace());
        }
    }
%>
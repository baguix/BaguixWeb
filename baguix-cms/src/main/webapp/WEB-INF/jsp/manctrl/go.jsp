<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%
    final String url = request.getContextPath() + "/manctrl/main.do";
    response.sendRedirect(response.encodeURL(url));
%>

<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java"  session="false" %>
<%--

    BaguixWeb版权所有。

--%>

<%
    final String queryStr = request.getQueryString();
    final String url = request.getContextPath() + "/login.jsp" + (queryStr != null ? "?" + queryStr : "");
    response.sendRedirect(response.encodeURL(url));
%>
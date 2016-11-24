<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <title>考试系统</title>
    <jsp:include page="../manctrl/maininc.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/tk/css/index.css">
</head>

<body class="easyui-layout" style="visibility:hidden">
<div data-options="region:'north',border:false" style="height:50px;background:#e4f1fe;padding:5px 20px">
    <div id="logo">
        <span class="title">考试系统</span>
        <span class="ver">v1.0</span>
    </div>
</div>
<div data-options="region:'west',split:true,title:'系统菜单'" style="width:200px;padding:5px;">
    <div id="index_maintree"></div>
</div>
<div data-options="region:'south',border:false" style="height:30px;background:#e4f1fe;padding:5px;text-align: center;">
    Nn36z Suguang 版权所有©2015 (Copyright © 2016 Nn36z Suguang Corporation, All Rights Reserved)
</div>
<div data-options="region:'center'" style="overflow: hidden;">
    <div id="index_maintab"></div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/exam/main.js"></script>
</body>
<jsp:include page="../manctrl/tools.jsp"></jsp:include>
</html>

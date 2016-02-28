<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>BaguixWeb</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/easyui/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/easyui/myicon.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/myjslib.js"></script>
</head>

<body>
    <ss:SysDict dictname="yesno" name="a1" id="a1" init="false"/><br/>
    <ss:SysDict dictname="artprop" name="a2" id="a2" init="'最新,推荐'"/><br/>
    <ss:SysDict dictname="writer" name="a3" id="a3" init="'佚名'"/><br/>
</body>
</html>
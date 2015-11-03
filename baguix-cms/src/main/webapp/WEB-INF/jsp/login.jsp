<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>${sitename}</h2>
<form action="<%=request.getContextPath()%>/shiro/auth.do" method="POST">
    姓名：<input type="text" name="username"/><br/>
    密码：<input type="text" name="password"/><br/>
    验证码：<input type="text" name="vercode"/><br/>
    &nbsp;&nbsp;<input type="submit" value="确认"/>
</form>
</body>
</html>
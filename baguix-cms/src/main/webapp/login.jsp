<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <title>Baguix用户中心</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/bootstrap/css/bootstrap-theme.min.css" >
    <link href="${pageContext.request.contextPath}/css/pub/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="container">
    <div class="header col-md-12">
        <a class="col-md-4" href="#"><img src="${pageContext.request.contextPath}/images/logo.png"></a>
        <nav class="col-md-8 navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <span class="navbar-brand"><span class="glyphicon glyphicon-book"></span></span>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="#">安装手册</a></li>
                        <li><a href="#">使用说明</a></li>
                        <li><a href="#">视频教程</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
                               aria-expanded="false">关于我们 <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">开发成员</a></li>
                                <li class="divider"></li>
                                <li><a href="#">发展规划</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
            <!-- /.container-fluid -->
        </nav>
    </div>
    <div class="clearfix"></div>
    <div class="main col-md-12">
        <div class="col-md-8">
            <img class="col-md-12" src="${pageContext.request.contextPath}/images/bg.jpg">
        </div>
        <div class="col-md-4">
            <form class="form-horizontal login-form" role="form"
                  action="${pageContext.request.contextPath}/manctrl/login.do" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <h3>用户登录</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="one-signin col-md-12">
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="input-icon-container">
                                    <i class="fa glyphicon glyphicon-user"></i>
                                    <input type="text" class="form-control" name="username" id="username">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="input-icon-container">
                                    <i class="fa glyphicon glyphicon-lock"></i>
                                    <input type="password" class="form-control" name="password" id="password">
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-6">
                                <input type="authcode" class="form-control" name="authcode" id="authcode">
                            </div>
                            <div class="col-md-6">
                                <img id="kaptchaImage" src="${pageContext.request.contextPath}/captcha.do" width="80"
                                     height="34"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox"> 自动登录
                                    </label>
                                    &nbsp;&nbsp; | &nbsp;&nbsp;
                                    <a href="${pageContext.request.contextPath}/forpassword.html" class="text-center">
                                        忘记密码了？ </a>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-12 text-right">
                                <input type="submit" value="登录" class="btn btn-default">
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="button" value="注册" class="btn btn-default">
                            </div>
                        </div>
                        <div class="col-md-12 text-center">
                            <label class="margin-bottom-15">
                                您还可以使用以下方式登录 ：
                            </label>
                            <a href="#"><img src="${pageContext.request.contextPath}/images/weibo.gif"></a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="#"><img src="${pageContext.request.contextPath}/images/qq.gif"></a>

                            <div style="margin-top:5px;">
                                请点击相应图标进入登录页面。
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
        <div class="footer">
            Baguix工作室版权所有©2015 (Copyright © 2015 baguixing Corporation, All Rights Reserved)<br>
            电话：0771-22211111 传真：0771-22211111 邮箱：123456789@qq.com
        </div>
    </div>
    <!-- /.container -->
</div>
<!-- /.container -->
</form>

<script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pub/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pub/login/placeholder.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pub/login/login.js"></script>
</body>
</html>
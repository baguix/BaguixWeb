<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>BaguixWeb</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <jsp:include page="maininc.jsp"></jsp:include>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/main.js"></script>
</head>
<body style="visibility:hidden">
<div id="main_layout">
    <div id="main_layout_banner" data-options="region:'north'"
         style="min-width:1050px;height:92px;background:url(${pageContext.request.contextPath}/images/manctrl/main/bg.png) repeat-x;">
        <div id="RIGHT"
             style="text-align:right;height:60px;width:600px;float:right;background:url(${pageContext.request.contextPath}/images/manctrl/main/right.png)">
            <p>
                <br/>
                &nbsp;|&nbsp;
                <a href="http://www.baguix.com">Baguix Studio</a>&nbsp;|&nbsp;
                <a>修改密码</a>&nbsp;|&nbsp;
                <shiro:hasRole name="管理员">（超级管理员）</shiro:hasRole>
            </p>
        </div>
        <div id="main_layout_north_logo" style="z-index:6;position:absolute;left:0px;top:0px;height:60px;">
            <img src="${pageContext.request.contextPath}/images/manctrl/main/logo.png"/>
        </div>
        <div class="cls"></div>
        <ss:AdminMenuBar id="admin"></ss:AdminMenuBar>
    </div>

    <div data-options="region:'east',title:'工作助手',split:true" style="width:200px;text-align:center;">
        <jsp:include page="helper.jsp"></jsp:include>
    </div>

    <div id="main_layout_lefttree" data-options="region:'west',split:true,title:'系统菜单'" style="width:150px;">
        <div id="main_layout_west_lefttree" style="margin:10px 0px 0px -10px;"></div>
    </div>

    <div id="main_layout_center" data-options="region:'center'" style="background:#eee;overflow: hidden;">
        <div id="main_layout_center_tabs" style="overflow: hidden;">
            <jsp:include page="welcome.jsp"></jsp:include>
        </div>
    </div>

    <div id="main_layout_footer" data-options="region:'south',border:false"
         style="background:#E6EEF8;height:26px;padding:5px;text-align:center;">
        版权所有：${applicationScope.Unit}
        &nbsp;&nbsp;地址：${applicationScope.UnitAddress}
        &nbsp;&nbsp;技术支持：<a href="tencent://message/?uin=8555323&Site=&Menu=yes">QQ</a>
        &nbsp;&nbsp;ICP备案号：<a href="http://www.miibeian.gov.cn">${applicationScope.icp}</a>
    </div>
</div>
<script type="text/javascript">
    <!--
    var page = window.location.href;
    var logout_page = page.substring(0, window.location.href.lastIndexOf("/")) + "/logout.action";
    var main_layout;
    var main_tabs;
    var main_tabs_index = 0;//统计tab数
    var main_tree;
    $(function () {
        main_layout = $('#main_layout').layout({fit: true, border: false});//main_layout
        main_layout.layout('collapse', 'east');

        main_tree = $('#main_layout_west_lefttree').tree({url: '${pageContext.request.contextPath}/manctrl/deflefttree.do'});//main_tree

        main_tabs = $('#main_layout_center_tabs').tabs({
            fit: true,
            border: false,
            tools: [{
                iconCls: 'icon-cancel',

                handler: function () {
                    var tabnum = main_tabs_index;
                    for (i = tabnum; i > 0; i--) {
                        main_tabs.tabs('close', i);
                    }
                    main_tabs.tabs('select', 0);
                }
            }, {
                iconCls: 'icon-help',
                handler: function () {
                    main_tree.tree({url: '${pageContext.request.contextPath}/manctrl/deflefttree.do'});
                    main_layout.layout('expand', 'east');
                }
            }],//tools
            onAdd: function (title, index) {
                main_tabs_index++;
            },//onAdd
            onBeforeClose: function (title, index) {
                main_tabs_index--;
                if (main_tabs_index > 1) {
                    main_tabs.tabs('select', index - 1);
                }
            },//onBeforeClose
            onSelect: function (title, index) {
                if (title == "首页") {
                    main_tree.tree({url: '${pageContext.request.contextPath}/manctrl/deflefttree.do'});
                }
                else {
                    tabmenu(title);
                }
            }//onSelect
        });//main_tabs

        //EasyUI的渲染完全加载后才显示body
        $('body').css('visibility', 'visible');
    });//function

    //-->
</script>

<!-- 常用工具 -->
<jsp:include page="tools.jsp"></jsp:include>
<!-- 修改密码 -->
<jsp:include page="chpwd.jsp"></jsp:include>

</body>
</html>
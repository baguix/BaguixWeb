<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>知识点</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <jsp:include page="../manctrl/maininc.jsp"></jsp:include>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/easyui/datagrid-detailview.js"></script>
    <!-- ueditor -->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.simpleconfig.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/lang/zh-cn/zh-cn.js"></script>
</head>

<body class="easyui-layout" style="visibility:hidden">
<div data-options="region:'west',split:true,title:'知识点',border:false" style="width:250px;">
    <table id="knowledge_tree"></table>
    <input type="hidden" type="text" id="kid"/>
</div>
<div data-options="region:'center',border:false">
    <div id="question_tab" class="easyui-tabs" data-options="fit:true,border:false">
        <div title="单选题">
            <table id="question_select_grid"></table>
        </div>
        <div title="多选题">
            <table id="question_multiselect_grid"></table>
        </div>
        <div title="判断题">
            <table id="question_truefalse_grid"></table>
        </div>
        <div title="填空题">
            <table id="question_fillblank_grid"></table>
        </div>
    </div>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/exam/question.js"></script>
</body>

</html>

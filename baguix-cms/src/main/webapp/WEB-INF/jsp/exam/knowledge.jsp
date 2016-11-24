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
    <!-- ueditor -->
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.myconfig.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/lang/zh-cn/zh-cn.js"></script>

</head>

<body class="easyui-layout" style="visibility:hidden">
<div data-options="region:'center',border:false">
    <table id="knowledge_grid"></table>
</div>
<div id="knowledge_add_dlg" class="easyui-dialog" title="增加知识点" style="width:800px;height:600px;padding:20px;"
     data-options="closed:true">
        <table class="gridtable">
            <tr>
                <td>标题：</td>
            </tr>
            <tr>
                <td>
                    <input id="text" name="text" class="easyui-textbox" data-options="required:true" style="width:720px">
                </td>
            </tr>
            <tr>
                <td>排序值</td>
            </tr>
            <tr>
                <td><input id="rank" name="rank" class="easyui-numberspinner" style="width:80px;" data-options="min:10,max:1000,editable:true,increment:10"></td>
            </tr>
            <tr>
                <td>知识点内容：<span style="color:#f00;background:#ffffbf;border-radius:3px;">(注意：在最后一层知识点上定义的内容才有效。)</span></td>
            </tr>
            <tr>
                <td>
                    <script id="content" name="content" type="text/plain" style="width:720px;height:248px;"></script>
                    <script type="text/javascript">
                        var knowledge_add_dlg_editor = UE.getEditor('content');
                        knowledge_add_dlg_editor.addListener("ready",function(){
                            //初始化
                        });
                    </script>
                </td>
            </tr>
        </table>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/exam/knowledge.js"></script>
</body>

</html>

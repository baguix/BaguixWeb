<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>新增单选题</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <jsp:include page="../../../manctrl/maininc.jsp"></jsp:include>
    <!-- ueditor -->
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.simpleconfig.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jslib/ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- tageditor -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/tageditor/jquery.tag-editor.css">
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jslib/tageditor/jquery.caret.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/jslib/tageditor/jquery.tag-editor.js"></script>
</head>

<body class="easyui-layout" style="visibility:hidden">
<form id="exam_select_add_form" method="post">
    <div data-options="region:'center',border:false">
        <div class="easyui-accordion" data-options="region:'center',border:false,fit:true">
            <div title="题目正文" data-options="iconCls:'icon-article'" style="overflow:auto;padding:6px;">
                <script id="content" name="content" type="text/plain"
                        style="width:772px;height:100px;"></script>
                <script type="text/javascript">
                    var contentEditor = UE.getEditor('content');
                </script>
            </div>
            <div title="题目解析" data-options="iconCls:'icon-help'" style="overflow:auto;padding:7px;">
                <script id="analysis" name="analysis" type="text/plain"
                        style="width:772px;height:100px;"></script>
                <script type="text/javascript">
                    var analysisEditor = UE.getEditor('analysis');
                </script>
            </div>
            <div title="标签" data-options="iconCls:'icon-dictionary'" style="text-align:center;overflow:auto;padding:7px;">
                <ss:SysDict dictname="ExamTags" name="etags" id="etags" init="''"></ss:SysDict>
            </div>
        </div>
    </div>
    <div data-options="region:'south',border:false" style="height:225px;">
        <script type="text/javascript">
            var simpleMode = {
                lang: 'zh-cn',
                wordCount: false,
                autoHeightEnabled: false,
                toolbars: [[
                    'source', '|', 'undo', 'redo', '|',
                    'bold', 'italic', 'removeformat', 'formatmatch', 'autotypeset', '|',
                    'fontfamily', 'fontsize', '|',
                    'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|',
                    'simpleupload', 'insertimage', 'scrawl', 'snapscreen', 'template', 'it'
                ]]
                , zIndex: 9999
            };
            var adddlgbtn = [{
                text: '确定',
                handler: function () {
                    var flag = false;
                    var rows = $('#options').datagrid('getRows');
                    for (var i = 0; i < rows.length; i++) {
                        if (rows[i].answer == true) {
                            flag = true;
                        }
                    }
                    if (flag == true && ($('#isanswer').switchbutton("options").checked)) {
                        $.messager.confirm('操作确认', '该单选题已经存在答案项，您是否确认将此项设为答案项？', function (r) {
                            if (r) {
                                $('#options').datagrid('appendRow', {
                                    answer: $('#isanswer').switchbutton("options").checked,
                                    summary: cEditor.getContentTxt(),
                                    content: cEditor.getContent()
                                });
                                $('#optionDlg').dialog('close');
                            }
                        });
                    }
                    else {
                        $('#options').datagrid('appendRow', {
                            answer: $('#isanswer').switchbutton("options").checked,
                            summary: cEditor.getContentTxt(),
                            content: cEditor.getContent()
                        });
                        $('#optionDlg').dialog('close');
                    }
                }
            }, {
                text: '取消',
                handler: function () {
                    $('#optionDlg').dialog('close');
                }
            }];

            // 新增选项
            function newOption() {
                $('#isanswer').switchbutton("uncheck");
                cEditor.setContent("");
                $('#optionDlg').dialog({
                    closed: false,
                    modal: true,
                    buttons: adddlgbtn
                });
            }
            var editdlgbtn = [{
                text: '确定',
                handler: function () {
                    var flag = false;
                    var rows = $('#options').datagrid('getRows');
                    for (var i = 0; i < rows.length; i++) {
                        if (rows[i].answer == true) {
                            flag = true;
                        }
                    }
                    var editRow = $('#options').datagrid('getSelected');
                    var editRowIndex = $('#options').datagrid('getRowIndex', editRow);
                    if (flag == true && ($('#isanswer').switchbutton("options").checked)) {
                        $.messager.confirm('操作确认', '该单选题已经存在答案项，您是否确认将此项设为答案项？', function (r) {
                            if (r) {
                                $('#options').datagrid('updateRow', {
                                    index: editRowIndex,
                                    row: {
                                        answer: $('#isanswer').switchbutton("options").checked,
                                        summary: cEditor.getContentTxt(),
                                        content: cEditor.getContent()
                                    }
                                });
                                $('#optionDlg').dialog('close');
                            }
                        });
                    }
                    else {
                        $('#options').datagrid('updateRow', {
                            index: editRowIndex,
                            row: {
                                answer: $('#isanswer').switchbutton("options").checked,
                                summary: cEditor.getContentTxt(),
                                content: cEditor.getContent()
                            }
                        });
                        $('#optionDlg').dialog('close');
                    }
                }
            }, {
                text: '取消',
                handler: function () {
                    $('#optionDlg').dialog('close');
                }
            }];
            // 修改选项
            function editOption() {
                var editRow = $('#options').datagrid('getSelected');
                if (editRow) {
                    if (editRow.answer) {
                        $('#isanswer').switchbutton("check");
                    } else {
                        $('#isanswer').switchbutton("uncheck");
                    }
                    cEditor.setContent(editRow.content);
                    $('#optionDlg').dialog({
                        closed: false,
                        modal: true,
                        buttons: editdlgbtn
                    });
                } else {
                    $.messager.alert('提示', '“必须选中一个选项才能修改！');
                }
            }
            function removeOption() {
                var delRow = $('#options').datagrid('getSelected');
                var delIndex = $('#options').datagrid('getRowIndex');
                if (delRow) {
                    $('#options').datagrid('deleteRow', delIndex);
                } else {
                    $.messager.alert('提示', '“必须选中一个选项才能删除！');
                }
            }
        </script>
        <div id="toolbar">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true"
               onclick="newOption()">新增选项</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
               onclick="editOption()">编缉选项</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true"
               onclick="removeOption()">删除选项</a>
        </div>
        <table id="options" title="选项列表" class="easyui-datagrid"
               data-options="rownumbers:true,border:false,fit:true,fitColumns:true,singleSelect:true,toolbar:'#toolbar'"
               style="width:786px;height:210px;">
            <thead>
            <tr>
                <th field="summary" width="50">摘要</th>
                <th field="answer" width="5"
                    data-options="
                        formatter:function(value){
                            if(value){
                                return '<img src=\'${pageContext.request.contextPath}/jslib/easyui/myicons/yes.gif\'/>';
                            }else{
                                return '<img src=\'${pageContext.request.contextPath}/jslib/easyui/myicons/no.gif\'/>';
                            }
                        }"
                >是否答案
                </th>
            </tr>
            </thead>
        </table>
        <div id="optionDlg" class="easyui-dialog" title="新增选项" data-options="closed:true"
             style="width:750px;height:350px;padding:10px">
            <div style="margin-bottom:10px;">是否答案：<input id="isanswer" class="easyui-switchbutton"
                                                         data-options="onText:'是',offText:'否'"></div>
            <script id="C" name="C" type="text/plain" style="width:715px;height:160px;"></script>
            <script type="text/javascript">
                var cEditor = UE.getEditor('C', simpleMode);
            </script>
        </div>
    </div>
</form>
<script type="text/javascript">
    //======================================表单提交相关======================================
    //设置数据
    function setDatas() {

    }
    //提交表单
    function postForm() {
        var flag = false;
        var rows = $('#options').datagrid('getRows');
        for (var i = 0; i < rows.length; i++) {
            if ( (rows[i].answer == true) || (rows[i].answer == "true") ) {
                flag = true;
            }
        }
        if (flag == false) {
            $.messager.alert('提示', '必须设置一个答案选项！');
        } else {
            postaddForm();
        }
    }

    //提交表单
    function postaddForm(){
        var q={};
        q.content = contentEditor.getContent();
        q.analysis = analysisEditor.getContent();
        q.summary = SS_getStringSummary(contentEditor.getContentTxt(),20);
        q.optionstr = encodeURI(JSON.stringify($('#options').datagrid('getRows')));
        q.kid = '${kid}';
        q.knowledgestr = '${kids}';
        q.tags=$('#etags').val();
        $.post('${pageContext.request.contextPath}/exam/question/select/add_deal.do', q,
                function (data) {
                    if (data.success) {
                         // 在grid中插入新增行
                         parent.$('#question_select_grid').datagrid('insertRow',{
                         index:0,
                         row:data.obj
                         });
                    }
                    parent.parent.$.messager.show({
                        title: '提示',
                        msg: data.msg
                    });
                    // 关闭对话框
                    parent.parent.sys_Dialog.dialog('close');
                }
                , 'json');
    }
    //======================================系统弹窗事件Begin======================================
    //关闭函数
    function closeEvent() {
    }

    //按"确定"按钮函数
    function okEvent() {
        postForm();
    }
    function restoreEvent() {
    }
    // EasyUI的渲染完全加载后才显示body
    $.parser.onComplete = function () {
        $('body').css('visibility', 'visible');
    }
</script>
</body>

</html>

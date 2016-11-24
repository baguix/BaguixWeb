
// datagrid的工具栏
var toolbar = [
    '-', {
        text: '增加',
        iconCls: 'icon-add',
        handler: function () {
            knowledge_add();
        }
    }, '-', {
        text: '编辑',
        iconCls: 'icon-edit',
        handler: function () {
            knowledge_edit();
        }
    }, '-', {
        text: '删除',
        iconCls: 'icon-del',
        handler: function () {
            knowledge_remove();
        }
    }, '-', {
        text: '刷新',
        iconCls: 'icon-refresh',
        handler: function () {
            $('#knowledge_grid').treegrid('unselectAll');
            $('#knowledge_grid').treegrid('reload');
        }
    }, '-', {
        text: '导出',
        iconCls: 'icon-export',
        handler: function () {
            $.post('/exam/knowledge/export.do', {},
                function (data) {
                    $.messager.confirm('提示', data.msg, function(r) {
                        if (r) {
                            window.open('../../tk/knowledge.html');
                        }
                    })
                }
                , 'json');
        }
    }
];
$(function () {
    $('#knowledge_grid').treegrid({
        url: "/exam/knowledge/list.do",
        idField: 'id',
        fit: true,
        border: false,
        title: ' ',
        fitColumns: true,
        treeField: 'text',
        lines: true,
        columns: [[
            {title: '名称', field: 'text', width: 200},
            {title: '排序值', field: 'rank', width: 50,
                formatter: function(value){
                    if(value==0){
                        return "";
                    }
                    else{
                        return value;
                    }
                }
            }
        ]],
        toolbar: toolbar
    });
})

function knowledge_add() {
    var row = $('#knowledge_grid').treegrid('getSelected');
    if(row){
        $('#text').textbox('setText','');
        knowledge_add_dlg_editor.setContent('');
        $('#knowledge_add_dlg').dialog({
            closed: false,
            modal: true,
            buttons: adddlgbtn
        });
    }else{
        $.messager.alert('提示', '请选中一个父节点再新增！');
    }
}

function knowledge_edit() {
    var row = $('#knowledge_grid').treegrid('getSelected');
    if(row){
        if(row.id=='000'){
            $.messager.alert('提示', '“全部知识点”根节点不能修改！');
        }
        else{
            $('#text').textbox('setText', row.text);
            $('#rank').numberspinner('setValue', row.rank);
            knowledge_add_dlg_editor.setContent(row.content);
            $('#knowledge_add_dlg').dialog({
                closed: false,
                modal: true,
                buttons: editdlgbtn
            });
        }
    }else{
        $.messager.alert('提示', '请选中一个节点再编辑！');
    }
}

function knowledge_remove() {
    var row = $('#knowledge_grid').treegrid('getSelected');
    if(row){
        if(row.id=='000'){
            $.messager.alert('提示', '不能选中“全部知识点”根节点！');
        }
        else{
            var c = $('#knowledge_grid').treegrid('getChildren',row.id);
            if(c && c.length>0){
                $.messager.confirm('提示', '该节点含有子节点，您确定要删除吗?',function(r) {
                    if (r) {
                        delnote(row.id);
                    }
                })
            }else{
                $.messager.confirm('提示', '您确定要删除该知识点吗?',function(r) {
                    if (r) {
                        delnote(row.id);
                    }
                });
            }
        }
    }else{
        $.messager.alert('提示', '请选中一个节点再删除！');
    }
}
function delnote(id){
    $('#knowledge_grid').treegrid('remove', id);
    $.post('/exam/knowledge/del.do', {'id':id},
        function (data) {
            $.messager.show({title : '提示',	msg : data.msg	});
            $('#knowledge_grid').treegrid('reload');
        }
        , 'json');
}
var editingId;

// add对话框的底部按钮
var adddlgbtn = [{
    text: '确定',
    handler: function () {
        var row = $('#knowledge_grid').treegrid('getSelected');
        if (row) {
            var k={};
            k.pid = row.id;
            k.text = $('#text').textbox('getText');
            k.content = knowledge_add_dlg_editor.getContent();
            k.rank = $('#rank').numberspinner('getValue');
            k.level = row.level + 1;
            $.post('/exam/knowledge/add.do', k,
                function (data) {
                    $.messager.show({title : '提示',	msg : data.msg	});
                    $('#knowledge_grid').treegrid('reload');
                }
                , 'json');
        }
        $('#knowledge_add_dlg').dialog('close');
    }
}, {
    text: '取消',
    handler: function () {
        $('#knowledge_add_dlg').dialog('close');
    }
}];

// edit对话框的底部按钮
var editdlgbtn = [{
    text: '确定',
    handler: function () {
        var row = $('#knowledge_grid').treegrid('getSelected');
        if (row) {
            var k={};
            k.id = row.id;
            k.text = $('#text').textbox('getText');
            k.content = knowledge_add_dlg_editor.getContent();
            k.rank = $('#rank').numberspinner('getValue');
            $.post('/exam/knowledge/edit.do', k,
                function (data) {
                    $.messager.show({title : '提示',	msg : data.msg	});
                    $('#knowledge_grid').treegrid('reload');
                }
                , 'json');
        }
        $('#knowledge_add_dlg').dialog('close');
    }
}, {
    text: '取消',
    handler: function () {
        $('#knowledge_add_dlg').dialog('close');
    }
}];
// EasyUI的渲染完全加载后才显示body
$.parser.onComplete = function () {
    $('body').css('visibility', 'visible');
}

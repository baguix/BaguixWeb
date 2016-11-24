var toolbar = [
	'-', {
		text: '增加',
		iconCls: 'icon-add',
		handler: function() {
			question_add();
		}
	}, '-', {
		text: '编辑',
		iconCls: 'icon-edit',
		handler: function() {
			question_edit();
		}
	}, '-', {
		text: '删除',
		iconCls: 'icon-del',
		handler: function() {
			alert('cut')
		}
	}, {
		text: '导入试题',
		iconCls: 'icon-database',
		handler: function() {
			alert('cut')
		}
	}, '-'
];
function getOpt(url){
	var opt = {
		url: url,
		title: ' ',
		fit: true,
		border: false,
		singleSelect: true,
		rownumbers: true,
		striped: true,
		fitColumns: true,
		pagination: true,
		pageSize: 25,
		pageList: [25, 50, 100, 500],
		nowarp: false,
		singleSelect: false,
		checkOnSelect: true,
		selectOnCheck: false,
		idField: 'id',
		frozenColumns: [
			[{
				field: 'id',
				title: '编号',
				width: 30,
				checkbox: true
			}]
		],
		columns: [
			[{
				title: '试题摘要',
				field: 'summary',
				width: 200

			}, {
				title: '知识点',
				field: 'knowledgestr',
				width: 50,
                formatter: function(value) {
                    return "<span title='" + value + "' class='easyui-tooltip'>" + value + "</span>";
                }
			}, {
				title: '标签',
				field: 'tags',
				width: 50

			}, {
				title: '状态',
				field: 'status',
				width: 30,
				formatter: function(value, row) {
					switch(value) {
						case "SHOW":
							return "<span style='color:#090'>正常</span>";
							break;
						case "HIDE":
							return "<span style='color:#999'>隐藏</span>";
							break;
						case "DELETE":
							return "<span style='color:#f00'>已删</span>";
							break;
						default:
							return "<span style='color:#ccc'>"+value+"</span>";
					}
				}

			}]
		],
		toolbar: toolbar,
		view: detailview,
		detailFormatter: function(index, row) {
			return '<div class="ddv" style="padding:5px 0"></div>';
		},
		onExpandRow: function(index, row) {
			var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
			ddv.panel({
				border: false,
				cache: false,
				content: getIframe('select/view.do?id='+row.id),
				onLoad: function() {
					$('#question_select_grid').datagrid('fixDetailRowHeight', index);
				}
			});
			$('#question_select_grid').datagrid('fixDetailRowHeight', index);
		}
	};
	return opt;
}

function getIframe(href) {
	if (href) {
		var content = '<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" allowtransparency="yes" src="' + href + '" style="width:100%;height:100%;"></iframe>';
	} else {
		var content = '建设中';
	}
	return content;
}
$(function() {
	// 设置URL
	$('#question_select_grid').datagrid(getOpt("select/list.do"));
	$('#question_multiselect_grid').datagrid(getOpt("multiselect/list.do"));
	$('#question_truefalse_grid').datagrid(getOpt("truefalse/list.do"));
	$('#question_fillblank_grid').datagrid(getOpt("fillblank/list.do"));
	// 知识点树
	$('#knowledge_tree').tree({
		url: "/exam/knowledge/list.do",
		onClick: function(node) {
			$('#kid').val(node.id);
			var params = {kid: node.id};
			$('#question_select_grid').datagrid({
				queryParams: params
			});
			$('#question_multiselect_grid').datagrid({
				queryParams: params
			});
			$('#question_truefalse_grid').datagrid({
				queryParams: params
			});
			$('#question_fillblank_grid').datagrid({
				queryParams: params
			});
		}
	});
})

function question_add(){
	var data={type:'select',kid:$('#kid').val(),kids:$('#kids').val()};
	parent.parent.SysDialog('新增单选题', 'exam/question/select/add.do', 800, 600, data);
}

function question_edit(){
    var row = $('#question_select_grid').datagrid('getChecked');
    if (row) {
        if(row.length>1){
            $.messager.alert('提示', '“一次只能修改一道题目！');
        } else {
            var data = {type: 'select', kid: $('#kid').val(), id: row[0].id};
            parent.parent.SysDialog('编辑单选题', 'exam/question/select/edit.do', 800, 600, data);
        }
    } else{
        $.messager.alert('提示', '必须勾选一道题目才能修改！');
    }
}

// add对话框的底部按钮
var selectAddDlgBtn = [{
	text: '确定',
	handler: function () {
		var row = $('#question_select_grid').datagrid('getSelected');
		if (row) {
			var s={};
			$.post('/exam/select/add.do', s,
				function (data) {
					$.messager.show({title : '提示',	msg : data.msg	});
					$('#question_select_grid').datagrid('reload');
				}
				, 'json');
		}
		$('#selectAddDlg').dialog('close');
	}
}, {
	text: '取消',
	handler: function () {
		$('#knowledge_add_dlg').dialog('close');
	}
}];

// EasyUI的渲染完全加载后才显示body
$.parser.onComplete = function() {
	$('body').css('visibility', 'visible');
}
var toolbar = [
	'-', {
		text: '增加',
		iconCls: 'icon-add',
		handler: function() {
			alert('add')
		}
	}, '-', {
		text: '编辑',
		iconCls: 'icon-edit',
		handler: function() {
			alert('cut')
		}
	}, '-', {
		text: '删除',
		iconCls: 'icon-del',
		handler: function() {
			alert('cut')
		}
	}, '-', {
		text: '设置考试',
		iconCls: 'icon-setting',
		handler: function() {
			alert('cut')
		}
	},'-', {
		text: '导入考生',
		iconCls: 'icon-database',
		handler: function() {
			alert('cut')
		}
	},'-'
];
$(function() {
	$('#ontest_grid').datagrid({
		url: "../json/ontest.json",
		method: 'get',
		title: ' ',
		fit: true,
		border: false,
		singleSelect: true,
		rownumbers: true,
		pagination: true,
		pageSize: 25,
		pageList: [25, 50, 100],
		fitColumns: false,
		nowarp: false,
		idField: 'id',
		fitColumns: true,
		columns: [
			[{
				title: '考试名称',
				field: 'title',
				width: 200

			}, {
				title: '限时（分钟）',
				field: 'time',
				width: 80

			}, {
				title: '开始时间',
				field: 'start',
				width: 80

			}, {
				title: '结束时间',
				field: 'end',
				width: 80

			}, {
				title: '状态',
				field: 'state',
				width: 80,
				formatter: function(value, row) {
					if (value == "1") {
						return "<span style='color:#009900'>在线</span>";
					} else {
						return "<span style='color:#f00'>下线</span>";
					}
				}

			}, {
				title: '排序值',
				field: 'rank',
				width: 50
			}, {
				title: '操作',
				field: 'id',
				width: 100,
				formatter: function(value, row) {
					return "<a href='#' onclick='getExam()'>考试情况</a>";
				}
			}]
		],
		toolbar: toolbar
	});
})

function getExam() {
	parent.SysDialog("考试情况", "page/ontest/test.html", 800, 600, null);
}

function formatOperate(val, row) {
	var html = "";
	html += "<a href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-save'\">导入名单</a>";
	return html;
}

// EasyUI的渲染完全加载后才显示body
$.parser.onComplete  = function () {
	$('body').css('visibility', 'visible');
}
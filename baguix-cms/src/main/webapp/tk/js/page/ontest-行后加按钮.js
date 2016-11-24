var ontest_grid = $('#ontest_grid');
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
		iconCls: 'icon-remove',
		handler: function() {
			alert('cut')
		}
	}, '-'
];
$(function() {
	ontest_grid.datagrid({
		url: "../json/ontest.json",
		method: 'get',
		height: 550,
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
				width: 300,
				formatter: function(value, row) {
					var html = "";
					html += "<a class='impbtn'></a>";
					html += "&nbsp;&nbsp;";
					html += "<a class='settingbtn'></a>";
					return html;
				}
			}]
		],
		toolbar: toolbar,
		onLoadSuccess: function(data) {
			$('.impbtn').linkbutton({
				text: '试卷',
				iconCls: 'icon-edit',
				onClick: function(){
					alert('导入试卷');
				}
			});
			$('.settingbtn').linkbutton({
				text: '考生',
				iconCls: 'icon-edit',
				onClick: function(){
					alert('导入考生');
				}
			});
		}
	});
})

function formatOperate(val, row) {
	var html = "";
	html += "<a href=\"#\" class=\"easyui-linkbutton\" data-options=\"iconCls:'icon-save'\">导入名单</a>";
	return html;
}

// EasyUI的渲染完全加载后才显示body
$('body').css('visibility', 'visible');
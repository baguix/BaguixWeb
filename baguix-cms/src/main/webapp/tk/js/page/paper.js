var toolbar = [
	'-', {
		text: '增加',
		iconCls: 'icon-add',
		handler: function() {
			paper_grid_add();
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
	$('#paper_grid').datagrid({
		url: "../json/paper.json",
		method: 'get',
		fit: true,
		border: false,
		title: ' ',
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
				title: '试卷名称',
				field: 'title',
				width: 100

			}, {
				title: '卷面总分',
				field: 'score',
				width: 80

			}, {
				title: '题目信息',
				field: 'selectNumber',
				width: 200,
				formatter: function(value, row) {
					var html = "";
					if (row.selectNumber) {
						html += "单选题(" + row.selectNumber + "题，" + row.selectNumber*row.selectScore + "分)；";
					}
					if (row.multiSelectNumber) {
						html += "多选题(" + row.multiSelectNumber + "题，" + row.multiSelectNumber*row.multiSelectScore + "分)；";
					}
					if (row.trueFalseNumber) {
						html += "判断题(" + row.trueFalseNumber + "题，" + row.trueFalseNumber*row.trueFalseScore + "分)；";
					}
					if (row.fillBlankNumber) {
						html += "填空题(" + row.fillBlankNumber + "题，" + row.fillBlankNumber*row.fillBlankScore + "分)；";
					}
					return html.substring(0, html.length - 1);
				}

			}, {
				title: '创建时间',
				field: 'ctime',
				width: 50
			}]
		],
		toolbar: toolbar
	});
})

//新增
function paper_grid_add() {
	var row = {};
	parent.SysDialog("新增试卷", "page/paper/add.html", 800, 600, row);
}

// EasyUI的渲染完全加载后才显示body
$.parser.onComplete  = function () {
	$('body').css('visibility', 'visible');
}
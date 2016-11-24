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
	},'-', {
		text: '导入学生',
		iconCls: 'icon-database',
		handler: function() {
			alert('cut')
		}
	},'-', {
		text: '清空学生',
		iconCls: 'icon-remove',
		handler: function() {
			alert('cut')
		}
	},'-'
];

$(function() {
	$("#class_grid").datagrid({
		url: "../json/class.json",
		method: 'get',
		title: ' ',
		border: false,
		fit: true,
		singleSelect: true,
		rownumbers: true,
		pagination: true,
		pageSize: 25,
		pageList: [25, 50, 100],
		nowarp: false,
		idField: 'id',
		fitColumns: true,
		columns: [
			[{
				title: '班级名称',
				field: 'title',
				width: 100

			},{
				title: '人数',
				field: 'number',
				width: 100

			}, {
				title: '排序值',
				field: 'rank',
				width: 100
			}]
		],
		toolbar: toolbar
	});
})

// EasyUI的渲染完全加载后才显示body
$.parser.onComplete  = function () {
	$('body').css('visibility', 'visible');
}
var opts = {
	url: "../../json/paper/paper_select_grid.json",
	title: "所有试题",
	fit: true,
	border: false,
	method: 'get',
	nowarp: false,
	fitColumns: true,
	pagination: true,
	pageSize: 10,
	pageList: [10, 20, 30, 40, 50],
	singleSelect: false,
	checkOnSelect: true,
	selectOnCheck: false,
	rownumbers: true,
	striped: true,
	idField: 'id',
	frozenColumns: [
		[{
			field: 'id',
			title: '编号',
			width: 50,
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
			field: 'knowledge',
			width: 50

		}, {
			title: '标签',
			field: 'state',
			width: 50

		}]
	],
	toolbar: '#tb',
	view: detailview,
	detailFormatter: function(index, row) {
		return '<div class="ddv" style="padding:5px 0"></div>';
	},
	onExpandRow: function(index, row) {
		var ddv = $(this).datagrid('getRowDetail', index).find('div.ddv');
		ddv.panel({
			border: false,
			cache: false,
			href: 'datagrid21_getdetail.php?itemid=' + row.itemid,
			onLoad: function() {
				$('#questions_grid').datagrid('fixDetailRowHeight', index);
			}
		});
		$('#questions_grid').datagrid('fixDetailRowHeight', index);
	}
};

$(function() {
	$('#questions_grid').datagrid(opts);
})


// EasyUI的渲染完全加载后才显示body
$.parser.onComplete = function() {
	$('body').css('visibility', 'visible');
}
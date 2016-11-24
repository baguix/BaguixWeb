function getPaperScore() {
	selectNumber = $("#selectNumber").val();
	selectScore = $("#selectScore").val();
	multiSelectNumber = $("#multiSelectNumber").val();
	multiSelectScore = $("#multiSelectScore").val();
	trueFalseNumber = $("#trueFalseNumber").val();
	trueFalseScore = $("#trueFalseScore").val();
	fillBlankNumber = $("#fillBlankNumber").val();
	fillBlankScore = $("#fillBlankScore").val();
	score = selectNumber * selectScore + multiSelectNumber * multiSelectScore + trueFalseNumber * trueFalseScore + fillBlankNumber * fillBlankScore;
	$("#showScore").html(score);
}

var toolbar = [
	'-', {
		text: '题库选题',
		iconCls: 'icon-add',
		handler: function() {
			var row = {};
			parent.sysMaxDialog("系统题库", "page/paper/questions.html", 1000, 600, row);
		}
	}, '-', {
		text: '导入试题',
		iconCls: 'icon-edit',
		handler: function() {
			alert('cut');
		}
	}, '-'
];
var opts = {
	url: "../../json/paper/paper_select_grid.json",
	title: " ",
	fit: true,
	border: false,
	method: 'get',
	singleSelect: true,
	rownumbers: true,
	pagination: true,
	pageSize: 10,
	pageList: [10, 20, 30, 40, 50],
	nowarp: false,
	idField: 'id',
	fitColumns: true,
	columns: [
		[{
			title: '试题编号',
			field: 'id',
			width: 30

		}, {
			title: '试题摘要',
			field: 'summary',
			width: 200

		}, {
			title: '知识点',
			field: 'knowledge',
			width: 80

		}, {
			title: '是否必考',
			field: 'state',
			width: 30,
			formatter: function(value) {
				if (value) {
					return "必考";
				}
			}
		}]
	],
	toolbar: toolbar
};
$(function() {
	$('#paper_select_grid').datagrid(opts);
	$('#paper_multiselect_grid').datagrid(opts);
	$('#paper_truefalse_grid').datagrid(opts);
	$('#paper_fillblank_grid').datagrid(opts);
})


// EasyUI的渲染完全加载后才显示body
$.parser.onComplete  = function () {
	$('body').css('visibility', 'visible');
}
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table id="admin_singlepage_manager_grid"></table>

<script type="text/javascript">
	$(function() {
		$('#admin_singlepage_manager_grid').datagrid({
		    title : '单篇文章管理',
			url : '${pageContext.request.contextPath}/admin/singlepage!datagrid.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 25,
			pageList : [ 25, 50, 100, 200 ],
			sortName : 'ctime',
			sortOrder : 'desc',
			checkOnSelect : true,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 50,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'title',
				title : '文章标题',
				width : 300,
				sortable : true
			},{
				field : 'type',
				title : '属性',
				width : 120,
				formatter : function(value, row) {
					if(row!=null){
						var index = $('#admin_singlepage_manager_grid').datagrid('getRowIndex',row);
						var html = "";
						if(row.isaudit){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"audit\","+row.isaudit+","+index+");'>已审</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"audit\","+row.isaudit+","+index+");'>已审</a>　";
						}
						if(row.isnew){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"new\","+row.isnew+","+index+");'>最新</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"new\","+row.isnew+","+index+");'>最新</a>　";
						}
						if(row.ishomethumb){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"homethumb\","+row.ishomethumb+","+index+");'>专栏</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"homethumb\","+row.ishomethumb+","+index+");'>专栏</a>　";
						}
						if(row.isthumb){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"thumb\","+row.isthumb+","+index+");'>缩图</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"thumb\","+row.isthumb+","+index+");'>缩图</a>　";
						}
						if(row.isflash){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"flash\","+row.isflash+","+index+");'>幻灯</a><br/>";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"flash\","+row.isflash+","+index+");'>幻灯</a><br/>";
						}
						if(row.isimg){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"img\","+row.isimg+","+index+");'>滚图</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"img\","+row.isimg+","+index+");'>滚图</a>　";
						}
						if(row.ismarquee){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"marquee\","+row.ismarquee+","+index+");'>滚信</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"marquee\","+row.ismarquee+","+index+");'>滚信</a>　";
						}
						if(row.isrecom){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"recom\","+row.isrecom+","+index+");'>推荐</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"recom\","+row.isrecom+","+index+");'>推荐</a>　";
						}
						if(row.istop){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"top\","+row.istop+","+index+");'>置顶</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"top\","+row.istop+","+index+");'>置顶</a>　";
						}
						return html;
					}
					
				}
			},{
				field : 'ctime',
				title : '发布日期',
				width : 100,
				sortable : true
			}, {
				field : 'state',
				title : '状态',
				width : 20,
				formatter : function(value, row) {
					if(row!=null){
						var index = $('#admin_singlepage_manager_grid').datagrid('getRowIndex',row);
						var html = "";
						if(row.state){
							html+="<a onclick='javascript:ch(\""+row.id+"\",\"state\","+row.state+","+index+");'>显示</a>　";
						}
						else{
							html+="<a style='color:#ccc;' onclick='javascript:ch(\""+row.id+"\",\"state\","+row.state+","+index+");'>隐藏</a>　";
						}
						return html;
					}
				}
			} ] ],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-del',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					edit();
				}
			}, '-', {
				text : '预览',
				iconCls : 'icon-preview',
				handler : function() {
					$('#admin_singlepage_manager_grid').datagrid('reload');
				}
			}, '-', {
				text : '刷新列表',
				iconCls : 'icon-refresh',
				handler : function() {
					$('#admin_singlepage_manager_grid').datagrid('reload');
				}
			}, '-', {
				text : '查询',
				iconCls : 'icon-search',
				handler : function() {
					search();
				}
			}, '-', {
				text : '导出',
				iconCls : 'icon-export',
				handler : function() {
				}
			}, '-']
		});
	});
	function append() {
		$('#admin_singlepage_addDialog_if')[0].src='admin/singlepage/addf.jsp';
		$('#admin_singlepage_addDialog').dialog('open');
	}
	
	function ch(id,m,s,index) {
		$.post(
				"${pageContext.request.contextPath}/admin/singlepage!sw.action",
				{"swid":id,"swmethod":m,"swval":s},
				function(r){
					var o = jQuery.parseJSON(r);
					if (o.success) {
						$('#admin_singlepage_manager_grid').datagrid('updateRow',{
							index:index,
							row:o.obj
						});
					$.messager.show({
						title : '提示',
						msg : o.msg
						});
					}
					else{
						$.messager.show({
							title : '操作失败提示',
							msg : '网络连接失败！'
							});
					}
				}
		);
	}
	
</script>
 <div id="admin_singlepage_addDialog" class="easyui-dialog" 
data-options="
title:'新增单篇文章',
closed:true,
maximizable:true,
modal:true,
buttons:[{
		text : '确定',
		iconCls : 'icon-confirm',
		handler : function() {
		   $('#admin_singlepage_addDialog_if')[0].contentWindow.pForm();
		}
	}]
" style="width:1000px;height:648px;overflow:hidden;">
<iframe scrolling="auto" id='admin_singlepage_addDialog_if' frameborder="0"  src="" style="width:100%;height:100%;"></iframe>
</div>

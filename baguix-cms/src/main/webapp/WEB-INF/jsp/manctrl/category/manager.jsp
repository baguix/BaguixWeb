<%@ page language="java" pageEncoding="UTF-8"%>
<table id="manctrl_category_manager_grid"></table>

<script type="text/javascript">
	$(function() {
		$('#manctrl_category_manager_grid').treegrid({
		    title : '栏目管理',
		    url : '${pageContext.request.contextPath}/manctrl/cms/category-list.do',
		    fit : true,
			fitColumns : true,
			border : false,
		    idField: 'id',
			treeField: 'title',
			columns:[[
				{field:'title',title:'标题',width:300},
			    {
					field : 'type',
					title : '属性',
					width : 150,
					formatter : function(value, row) {
						if(row!=null){
							if(row.level>0){//不是系统目录时
								var index = $('#manctrl_category_manager_grid').datagrid('getRowIndex',row);
								var html = "";
								if((typeof(row.proper)!="undefined") && manctrl_category_manager_formatprop(row.proper.split(","),"导航")){
									html+="<a onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已取消导航项！\",\"导航\");'>导航</a>　";
								}
								else{
									html+="<a style='color:#ccc;' onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已设为导航！\",\"导航\");'>导航</a>　";
								}
								if((typeof(row.proper)!="undefined") && manctrl_category_manager_formatprop(row.proper.split(","),"菜单")){
									html+="<a onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已取消菜单项！\",\"菜单\");'>菜单</a>　";
								}
								else{
									html+="<a style='color:#ccc;' onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已设为菜单！\",\"菜单\");'>菜单</a>　";
								}
								if((typeof(row.proper)!="undefined") && manctrl_category_manager_formatprop(row.proper.split(","),"首页")){
									html+="<a onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已取消首页显示栏目！\",\"首页\");'>首页</a>　";
								}
								else{
									html+="<a style='color:#ccc;' onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已设为首页显示栏目！\",\"首页\");'>首页</a>　";
								}
								if((typeof(row.proper)!="undefined") && manctrl_category_manager_formatprop(row.proper.split(","),"专题")){
									html+="<a onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已取消专题栏目！\",\"专题\");'>专题</a>　";
								}
								else{
									html+="<a style='color:#ccc;' onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已设为专题栏目！\",\"专题\");'>专题</a>　";
								}
								return html;
							}
						}
					}
				},
				{field:'ctime',title:'创建时间',width:120},
				{
					field : 'isonline',
					title : '状态',
					width : 50,
					formatter : function(value, row) {
						if(row!=null){
							if(row.level>0){//不是系统目录时
								var html = "";
								if(row.isonline){
									html+="<a onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已下线隐藏！\",\"显示\");'>显示</a>　";
								}
								else{
									html+="<a style='color:#ccc;' onclick='javascript:manctrl_category_manager_ch(\""+row.id+"\",\"已上线并发布显示！\",\"隐藏\");'>隐藏</a>　";
								}
								return html;
							}
						}
					}
				}
			]],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					manctrl_category_manager_append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-del',
				handler : function() {
					manctrl_category_manager_remove();
				}
			}, '-', {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					manctrl_category_manager_edit();
				}
			}, '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					$('#manctrl_category_manager_grid').treegrid('load');
				}
			},  '-']
		});
	});
	
	//新增
	function manctrl_category_manager_append() {
		var row = $('#manctrl_category_manager_grid').treegrid('getSelected');
		if(row != null){
			parent.SysAddDialog("新增栏目","manctrl/category/add.action",640,550,row);
		}else{
			$.messager.alert('提示', '请选中一个栏目！');
		}
	}
	
	//删除
	function manctrl_category_manager_remove() {
		var row = $('#manctrl_category_manager_grid').treegrid('getSelected');
		var id = row.id;
		var child = $('#manctrl_category_manager_grid').treegrid('getChildren', id);
		if (row.level > 0) {
			if(child.length>0){
				$.messager.confirm(
						'确认',
						'您选中的栏目下有子栏目，请确认您是否要<span style=\'color:red;font-weight:bold;\'>删除当前选中的栏目及其子栏目</span>？',
						function(r) {
							if (r) {
								$.ajax({
									//临时删除（已测）url : '${pageContext.request.contextPath}/manctrl/category!del.action',
									url : '${pageContext.request.contextPath}/manctrl/category!remove.action',
									data : {'id': id},
									dataType : 'json',
									success : function(r) {
										$('#manctrl_category_manager_grid').treegrid('remove',id);
										$.messager.show({
											title : '提示',
											msg : r.msg
										});
									}
								});
							}
						});
			}
			else {
				$.messager.confirm(
						'确认',
						'您是否要删除当前选中的栏目？',
						function(r) {
							if (r) {
								$.ajax({
									//临时删除（已测）url : '${pageContext.request.contextPath}/manctrl/category!del.action',
									url : '${pageContext.request.contextPath}/manctrl/category!remove.action',
									data : {'id': id},
									dataType : 'json',
									success : function(r) {
										$('#manctrl_category_manager_grid').treegrid('remove',id);
										$.messager.show({
											title : '提示',
											msg : r.msg
										});
									}
								});
							}
						});
			}
		} else {
			$.messager.alert('提示', '此项不可删除，请选中其子栏目再进行删除！');
		}
	}
	
	//编辑
	function manctrl_category_manager_edit() {
		var row = $('#manctrl_category_manager_grid').treegrid('getSelected');
		if(row != null){
			parent.SysAddDialog("编辑栏目","manctrl/category/edit.action",640,550,row);
		}else{
			$.messager.alert('提示', '请选中一个栏目！');
		}
	}
	//属性格式化函数
	function manctrl_category_manager_formatprop(arr, value){
		//判断是不是在数组里
		for(i=0;i<arr.length;i++){
			if(value == arr[i].trim()){
				return true;
			}
		}
		return false;
	}
	
	//属性开关（允许或禁止）交换函数
	function manctrl_category_manager_ch(id,m,s,index) {
		$.post(
				"${pageContext.request.contextPath}/manctrl/category!sw.action",
				{"swid":id,"swmethod":m,"swval":s},
				function(r){
					var o = jQuery.parseJSON(r);
					if (o.success) {
						$('#manctrl_category_manager_grid').treegrid('update',{
							id: id,
							row : o.obj
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
	
	//属性开关（允许或禁止）交换函数
	function manctrl_category_manager_ch(id,m,s) {
		$.post(
				"${pageContext.request.contextPath}/manctrl/category!sw.action",
				{"swid":id,"swmsg":m,"swval":s},
				function(r){
					var o = jQuery.parseJSON(r);
					if (o.success) {
						$('#manctrl_category_manager_grid').treegrid('update',{
							id: id,
							row : o.obj
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
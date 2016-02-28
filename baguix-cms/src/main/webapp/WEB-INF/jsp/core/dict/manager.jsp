<%@ page language="java" pageEncoding="UTF-8"%>
<div class="easyui-layout" data-options="fit:true">
	<div data-options="region:'west',border:false,collapsed:false" style="width:180px;border-right:1px solid #95b8e7">
		<div id="core_dictclass_list"></div>
		<div id="core_dictclass_msg" style="border-top:1px solid #95b8e7;padding:10px;"></div>
		<input id="dictclass" type="hidden"/>
	</div>
	<div data-options="region:'center',border:false">
		<table id="core_dict_manager_grid"></table>
	</div>
</div>


<script type="text/javascript">
	$(function() {
		$('#core_dictclass_list').datalist({
			url: '${pageContext.request.contextPath}/core/dict/class.do',
			loadMsg: '加载中',
			height: 300,
			border:false,
			textField: 'title',
			valueField: 'id',
			lines: true,
			onClickRow: function(index,row) {
				$('#core_dictclass_msg').html("【"+row.title+"】：<br/>&nbsp;&nbsp;&nbsp;&nbsp;"+row.comment);
				$('#dictclass').val(row.id);
				$('#core_dict_manager_grid').datagrid('unselectAll');
				$('#core_dict_manager_grid').datagrid('load',{
					dcid: row.id
				});
			},
			onLoadError: function(){
				$.messager.alert('数据加载失败','数据加载错误，请查看网络是否连通。');
			}
		});

		$('#core_dict_manager_grid').datagrid({
		    title : '字典管理',
			url : '${pageContext.request.contextPath}/core/dict/list.do',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			pageSize : 25,
			pageList : [ 25, 50, 100, 200 ],
//			sortName : 'ctime',
//			sortOrder : 'desc',
			singleSelect: true,
			rownumbers: true,
			columns : [ [ {
				field : 'title',
				title : '字典标题',
				width : 200,
				sortable : true
			},{
				field : 'name',
				title : '字典名称',
				width : 150,
				sortable : true
			},{
				field : 'type',
				title : '字典类型',
				width : 100,
				sortable : true,
				formatter : function(value, row) {
					if(row!=null){
						var html = "";
						if((typeof(row.type)!="undefined")){
							switch(row.type)
							{
								case "RadioList":
									html+="单选列表";
									break;
								case "CheckboxList":
									html+="多选列表";
									break;
								case "SingleCombobox":
									html+="单选组合框";
									break;
								case "MultiCombobox":
									html+="多选组合框";
									break;
								case "SingleComboTree":
									html+="单选树形组合框";
									break;
								case "MultiComboTree":
									html+="多选树形组合框";
									break;
								default:
									html = "";
									break;
							}
						}
						return html;
					}
				}
			},{
				field : 'dctitle',
				title : '所属分类',
				width : 150
			},{
				field : 'ctime',
				title : '创建日期',
				width : 100,
				sortable : true
			} ] ],
			onLoadError: function(){
				$.messager.alert('数据加载失败','数据加载错误，请查看网络是否连通。');
			},
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					core_dict_manager_append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-del',
				handler : function() {
					core_dict_manager_remove();
				}
			}, '-', {
				text : '修改/增加字典项',
				iconCls : 'icon-edit',
				handler : function() {
					core_dict_manager_edit();
				}
			}, '-',  {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					$('#core_dict_manager_grid').datagrid('unselectAll');
					$('#core_dict_manager_grid').datagrid('reload');
				}
			}, '-']
		});
	});
	function core_dict_manager_append() {
		if($('#dictclass').val()==""){
			$.messager.alert('提示', '左边字典分类不能选"全部"！','info');
		}else{
			var v = new Object();
			SysDialog("新增字典","core/dict/add.do",500,400,v);
		}
	}

	function core_dict_manager_edit() {
		var row = $('#core_dict_manager_grid').datagrid('getSelected');
		if ( row ){
			var d=({});
			d.id= row.id;
			d.index=$('#core_dict_manager_grid').datagrid('getRowIndex',row);
			SysDialog("编辑字典","core/dict/edit.do",800,600,d);
		} else {
			$.messager.alert('提示', '请选择要编辑的记录！','info');
		}
	}

	function core_dict_manager_remove() {
		var row = $('#core_dict_manager_grid').datagrid('getSelected');
		if ( (typeof(row)==undefined) || (row==null) ){
			$.messager.alert('提示', '请选择要删除的记录！','info');
		}else{
			var index = $('#core_dict_manager_grid').datagrid('getRowIndex',row);
			$.messager.confirm('提示', '您确定要删除该字典吗?',function(r){
				if(r){
					$.ajax({
						url : '${pageContext.request.contextPath}/core/dict/del_deal.do',
						data : {'id': row.id},
						dataType : 'json',
						success : function(r) {
							$('#core_dict_manager_grid').datagrid('deleteRow', index);
							$.messager.show({
								title : '提示',
								msg : r.msg
							});
						}
					});
				}
			});
		}
	}

</script>
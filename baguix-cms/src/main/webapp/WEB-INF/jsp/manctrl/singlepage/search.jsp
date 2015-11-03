<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="admin_singlepage_search_searchDialog" style="width:600px;height:400px;">

<div id="admin_singlepage_search_tabs" class="easyui-tabs" data-options="fit:true,border:false">
    <div id="easyQuery" title="简单查询" style="padding:10px 30px 10px 30px;">
    <form id="admin_singlepage_search_searchForm" method="post">
			<table>
				<tr>
					<th><label for="id">编号:</label></th>
					<td><input name="id" class="easyui-validatebox" data-options="required:true" /></td>
					<th><label for="title">字典标签:</label>	</th>
					<td><input name="title" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><label for="comment">备注:</label></th>
					<td><input name="comment" class="easyui-validatebox" data-options="required:true" /></td>
					<th><label for="state">是否多选:</label>	</th>
					<td>
					<input name="state" type="checkbox" />
					<input id="json" name="json" type="hidden" />
					</td>
				</tr>
			</table>
		</form> 
    </div>
    
    <div id="cQuery" title="自定义查询">
	    <div style="padding:10px 10px 10px 10px;" >
		<span style="color:#0000ff;font-weight:bold;">提示：</span>
		增加一行即可增加一个查询条件，可以通过逻辑连接符[并且]和[或者]连接多个查询条件。
		</div>
		<div id="dict_condition"></div>
	    <script type="text/javascript">
		<!--
		    function getDictName(){
				var json = $('#admin_singlepage_search_searchForm #json').val();
				if(json!="")
		    		var j = eval("("+json+")");
		    		return j;
		    	}
		    	return "";
			}
			
			$('#dict_condition').datagrid({
			border:false,
			fit:true,
			singleSelect:true, 
			rownumbers:true,
			onClickRow:qonClickRow,
			columns:[[
			{field:'field',title:'字段名',width:100,
				editor:{
					type:'combobox',
					options:{
						data:getDictName(),
						valueField:'value',
						textField:'text',
						panelHeight:'auto'
					}
				},
				formatter:function (value,row){
					var column_title = getDictName();
				    for (var i = 0; i < column_title.length; i++) { 
				        if (column_title[i].value == value) { 
				            return column_title[i].text; 
				        } 
				    }
				}
			},
			{field:'rel',title:'关系符',width:100,
				editor:{
					type:'combobox',
					options:{
						data:[{"id":"1","value":"like","text":"包含"},{"id":"2","value":"=","text":"等于"},{"id":"3","value":">=","text":"大于或等于"},{"id":"4","value":">","text":"大于"},{"id":"5","value":"<=","text":"小于或等于"},{"id":"6","value":"<","text":"小于"},	{"id":"7","value":"<>","text":"不等于"},	{"id":"8","value":"before","text":"早于"},{"id":"9","value":"after","text":"晚于"}],
						valueField:'value',
						textField:'text',
						panelHeight:'auto',
						onSelect:function(row){
						   if(row.value =="before" || row.value=="after"){
						        var e = $('#dict_condition').datagrid('getColumnOption', 'value');
			                	e.editor = {type : 'datebox'};
						    }
						    else{
								var e = $('#dict_condition').datagrid('getColumnOption', 'value');
			                	e.editor = {type : 'text'};
						    }
			                $('#dict_condition').datagrid('endEdit', qeditIndex);
	                    	$('#dict_condition').datagrid('beginEdit', qeditIndex);
						}
					}
				},
				formatter:function (value,row){
					switch(value){
						case 'like':html="包含";break;
						case '=':html="等于";break;
						case '>=':html="大于或等于";break;
						case '>':html="大于";break;
						case '<=':html="小于或等于";break;
						case '<':html="小于";break;
						case '<>':html="不等于";break;
						case 'before':html="早于";break;
						case 'after':html="晚于";break;
						default:html="";
					}
					return html;
				}
			}
			,
			{field:'value',title:'数据值',width:100,
				editor:{
					type:'text',
					required:true
				}
			}
			,
			{field:'logic',title:'逻辑符',width:50,
				editor:{
					type:'combobox',
					options:{
						data:[{"id":"and","text":"并且"},{"id":"or","text":"或者"},{"id":"","text":"无"}],
						valueField:'id',
						textField:'text',
						panelHeight:'auto'
					}
				},
				formatter:function (value,row){
					switch(value){
						case 'and':html="并且";break;
						case 'or':html="或者";break;
						default:html="";
					}
					return html;
				}
			}
			]],
			toolbar : [
			{
				id : 'Add',
				text : '增加',
				iconCls : 'icon-add',
				plain : 'true',
				handler : function() {
					qappend();
				}
			},
			'-',
			{
				id : 'Delete',
				text : '删除',
				iconCls : 'icon-del',
				handler : function() {
					qremove();
				}
			},
			'-',
			{
				id : 'Confirm',
				text : '确定',
				iconCls : 'icon-ok',
				handler : function() {
					qsave();
				}
			},
			'-',
			{
				id : 'Reject',
				text : '撤销',
				iconCls : 'icon-undo',
				handler : function() {
				    qrejectChanges();
				}
			},
			'-'
			]
		 });
		 
		var qeditIndex = undefined;
		function qendEditing(){
			if (qeditIndex == undefined){return true;}
			if ($('#dict_condition').datagrid('validateRow', qeditIndex)){
				$('#dict_condition').datagrid('endEdit', qeditIndex);
				qeditIndex = undefined;
				return true;
			} else {
				return false;
			}
		}
		function qonClickRow(index){
				if (qendEditing()){
					$('#dict_condition').datagrid('selectRow', index)
							.datagrid('beginEdit', index);
					qeditIndex = index;
				}
		}
		
		function qappend(){
	        if (qendEditing()){
	            $('#dict_condition').datagrid('appendRow',{status:'P'});
	            qeditIndex = $('#dict_condition').datagrid('getRows').length-1;
	            $('#dict_condition').datagrid('selectRow', qeditIndex)
	                    .datagrid('beginEdit', qeditIndex);
	        }
	    }
		function qremove(){
	        if (qeditIndex == undefined){return;}
	        $('#dict_condition').datagrid('cancelEdit', qeditIndex)
	                .datagrid('deleteRow', qeditIndex);
	        qeditIndex = undefined;
	    }
	    function qsave(){
	        if (qendEditing()){
	            $('#dict_condition').datagrid('acceptChanges');
	        }
	    }
		function qrejectChanges(){
	        $('#dict_condition').datagrid('rejectChanges');
	        qeditIndex = undefined;
	    }
		//-->
        </script>
		</div>
    </div>

</div>

<script type="text/javascript">
<!--
$('#admin_singlepage_search_searchDialog').dialog({  
	closed:true,maximizable:true,modal:true,title:'查询',buttons:[{
		text : '确定',
		iconCls : 'icon-confirm',
		handler : function() {
			$('#admin_singlepage_search_searchForm').form('submit', {
				url : '${pageContext.request.contextPath}/dict!add.action',
				success : function(r) {
					var o = jQuery.parseJSON(r);
					if (o.success) {
						$('#admin_singlepage_search_manager_grid').datagrid('insertRow',{
							index:0,
							row:o.obj
						});
						$('#admin_singlepage_search_searchDialog').dialog('close');
					}
					$.messager.show({
						title : '提示',
						msg : o.msg
					});
				}
			});
		}
	}]
});
//-->
</script>
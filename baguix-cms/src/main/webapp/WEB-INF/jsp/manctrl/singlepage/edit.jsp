<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="admin_singlepage_edit_editDialog" style="width:500px;height:150px;">
	<form id="admin_singlepage_edit_editForm" method="post">
		<table>
			<tr>
				<th><label for="name">字典名称:</label></th>
				<td><input name="name" class="easyui-validatebox" data-options="required:true" /></td>
				<th><label for="title">字典标签:</label>	</th>
				<td><input name="title" class="easyui-validatebox" data-options="required:true" /></td>
			</tr>
			<tr>
				<th><label for="comment">备注:</label></th>
				<td><input name="comment" class="easyui-validatebox" data-options="required:true" /></td>
				<th><label for="state">是否多选:</label>	</th>
				<td>
				<input name="state" type="checkbox" />
				<input name="id" type="hidden" />
				<input id="rowindex" name="index" type="hidden" />
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
<!--
$('#admin_singlepage_edit_editDialog').dialog({
	closed:true,maximizable:true,modal:true,title:'编辑词典',buttons:[{
		text : '确定',
		iconCls : 'icon-confirm',
		handler : function() {
			$('#admin_singlepage_edit_editForm').form('submit', {
				url : '${pageContext.request.contextPath}/dict!edit.action',
				success : function(r) {
					var o = jQuery.parseJSON(r);
					if (o.success) {
					    var index = $('#rowindex').val();
						$('#admin_singlepage_edit_manager_grid').datagrid('updateRow',{
							index:index,
							row:o.obj
						});
						$('#admin_singlepage_edit_editDialog').dialog('close');
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
-->
</script>
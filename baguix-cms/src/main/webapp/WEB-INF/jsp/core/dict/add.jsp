<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld" %>
<!doctype html>
<html lang="zh-CN">
<head>
	<meta charset="UTF-8">
	<title>BaguixWeb</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<jsp:include page="../../manctrl/maininc.jsp"></jsp:include>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pub/form.css"/>
</head>

<body>
<div class="easyui-panel" style="padding:10px;width:90%;margin:0 auto;" data-options="fit:true,border:false">
	<form id="core_dict_add_form" method="post">
		<input id="dcid" name="dcid" type="hidden">
		<table class="gridtable" style="width:100%;margin:0 auto;">
			<tr>
				<td class="ct" align="right">字典标题：</td>
				<td class="cc">
					<ss:euTextBox id="title"/>
				</td>
			</tr>
			<tr>
				<td class="ct" align="right">字典名称：</td>
				<td class="cc">
					<ss:euTextBox id="name"/>
				</td>
			</tr>
			<tr>
				<td class="ct" align="right">字典类型：</td>
				<td class="cc">
					<input id="type" name="type">
					<script>
						$('#type').combobox({
							url:'${pageContext.request.contextPath}/core/dict/type.do',
							valueField:'id',
							textField:'text',
							width:142,
							formatter: function(row){
								var opts = $(this).combobox('options');
								return "<img style='width:120px;height:120px;' src='${pageContext.request.contextPath}/images/core/dict/"+ row[opts.valueField]+".gif'/>";
							}
						});
					</script>

				</td>
			</tr>
			<tr>
				<td class="ct" align="right">排序值：</td>
				<td class="cc">
					<ss:euTextBox id="rank"/>
				</td>
			</tr>
			<tr>
				<td class="ct" align="right">备注：</td>
				<td class="cc">
					<ss:euTextBox id="comment" dataoption="multiline:true" css="width:100%;height:130px;"/>
				</td>
			</tr>
		</table>
	</form>
</div>


<script type="text/javascript">
	$(function() {
		$('#core_dict_add_dictitemManager').treegrid({
			title: '字典项管理',
			idField:'id',
			treeField:'name',
			columns:[[
				{title:'名称',field:'name',width:200},
				{title:'值',field:'val',width:200},
				{title:'默认',field:'def',width:200}
			]]
		});
	});

	//======================================表单提交相关======================================
	//设置数据
	function setDatas(){
		$('#dcid').val(parent.$('#dictclass').val());
	}
	//提交表单
	function postForm(){
		setDatas();
		$('#core_dict_add_form').form('submit', {
			url : '${pageContext.request.contextPath}/core/dict/add_deal.do',
			success : function(r) {
				var o = jQuery.parseJSON(r);
				if(o.success){
					// 在grid中插入新增行
					parent.$('#core_dict_manager_grid').datagrid('insertRow',{
						index:0,
						row:o.obj
					});
					// 关闭对话框
					parent.sys_Dialog.dialog('close');
				}
				parent.$.messager.show({
					title : '提示',
					msg : o.msg
				});
			}
		});
	}

	//======================================系统弹窗事件Begin======================================
	//关闭函数
	function closeEvent(){
	}

	//按"确定"按钮函数
	function okEvent(){
		postForm();
	}
	function restoreEvent(){
	}
</script>

</body>
</html>
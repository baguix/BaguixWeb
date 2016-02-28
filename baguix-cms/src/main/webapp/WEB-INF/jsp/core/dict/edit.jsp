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
	<form id="core_dict_edit_form" method="post">
		<input id="id" name="id" type="hidden" value="${dict.id}">
		<input id="dcid" name="dcid" type="hidden" value="${dict.dcid}">
		<input id="dctitle" name="dctitle" type="hidden" value="${dict.dctitle}">
		<input id="dictrowindex" name="dictrowindex" type="hidden" />
		<table class="gridtable" style="width:100%;margin:0 auto;">
			<tr>
				<td class="ct" align="right">字典标题：</td>
				<td class="cc">
					<ss:euTextBox id="title" val="${dict.title}"/>
				</td>
				<td class="ct" align="right">字典名称：</td>
				<td class="cc">
					<ss:euTextBox id="name" val="${dict.name}"/>
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
							value: '${dict.type}',
							formatter: function(row){
								var opts = $(this).combobox('options');
								return "<img style='width:120px;height:120px;' src='${pageContext.request.contextPath}/images/core/dict/"+ row[opts.valueField]+".gif'/>";
							}
						});
					</script>

				</td>
				<td class="ct" align="right">排序值：</td>
				<td class="cc">
					<ss:euTextBox id="rank" val="${dict.rank}"/>
				</td>
			</tr>
			<tr>
				<td class="ct" align="right">备注：</td>
				<td class="cc" colspan="3">
					<ss:euTextBox id="comment" dataoption="multiline:true" css="width:100%;height:80px;" val="${dict.comment}"/>
				</td>
			</tr>
		</table>
		<br/>
		<div id="core_dict_edit_dictitemManager"></div>
	</form>
</div>


<script type="text/javascript">
	var backdata;
	$(function(){
		$('#core_dict_edit_dictitemManager').treegrid({
			url: '${pageContext.request.contextPath}/core/dict/items.do',
			queryParams: {
				dictid: '${dict.id}'
			},
			sortName: 'rank',
			title: '字典项管理',
			idField:'id',
			treeField:'title',
			height:310,
			fitColumns : true,
			singleSelect:true,
			columns:[[
				{
					field : 'title',
					title : '名称',
					width:200,
					editor:'text'
				},
				{
					field : 'value',
					title : '值',
					width:100,
					editor:'text'
				},
				{
					field : 'def',
					title : '默认',
					width:100,
					formatter : function(value, row) {
						if(row!=null) {
							var html = "";
							if ((typeof(row.def) != "undefined")) {
								switch (row.def) {
									case "true":
										html += "<img src='${pageContext.request.contextPath}/jslib/easyui/myicons/success.gif'/>";
										break;
									default:
										html = "";
										break;
								}
							}
							return html;
						}
					},
					editor:{
						type:'checkbox',
						options:{
							on: "true",
							off: "false"
						}
					}
				},
				{
					field : 'rank',
					title : '排序',
					width:100,
					editor:'text',
					formatter : function(value, row) {
						if(row!=null) {
							var html = "";
							if ((typeof(row.id) != "undefined")) {
								if(row.id != "000"){
									html = row.rank;
								}
							}
							return html;
						}
					}
				}
			]],
			toolbar : [ {
				text : '增加',
				iconCls : 'icon-add',
				handler : function() {
					core_dict_edit_dictitemManager_append();
				}
			}, '-', {
				text : '删除',
				iconCls : 'icon-del',
				handler : function() {
					core_dict_edit_dictitemManager_remove();
				}
			}, '-', {
				text : '编辑',
				iconCls : 'icon-edit',
				handler : function() {
					core_dict_edit_dictitemManager_edit();
				}
			}, '-', {
				text : '保存',
				iconCls : 'icon-save',
				handler : function() {
					core_dict_edit_dictitemManager_save();
				}
			},  '-', {
				text : '刷新',
				iconCls : 'icon-refresh',
				handler : function() {
					core_dict_edit_dictitemManager_refresh();
				}
			},  '-'],
			onBeginEdit: function(index,row){
				editing = true;
			}
		});
	});


	var editingId;
	var editing=false;
	// 新增
	function core_dict_edit_dictitemManager_append() {
		var row = $('#core_dict_edit_dictitemManager').treegrid('getSelected');
		if(row != null){
			if(editing){
				$('#core_dict_edit_dictitemManager').treegrid('endEdit',editingId);
			}
			editingId = Math.uuid();
			$('#core_dict_edit_dictitemManager').treegrid('append',{
				parent: row.id,
				data: [{
					id: editingId,
					title: '',
					val: '',
					def: '',
					pid: row.id=='000'?'':row.id,
					dictid: '${dict.id}',
					level: row.level+1,
					rank: 0
				}]
			});
			$('#core_dict_edit_dictitemManager').treegrid('beginEdit', editingId);
		}else{
			$.messager.alert('提示', '请选中一个项目！');
		}
	}
	// 编辑
	function core_dict_edit_dictitemManager_edit(){
		if(editing){
			$('#core_dict_edit_dictitemManager').treegrid('endEdit',editingId);
			editing = false;
		}
		var row = $('#core_dict_edit_dictitemManager').treegrid('getSelected');
		editingId = row.id;
		if(row != null) {
			if (row.id != "000") {
				$('#core_dict_edit_dictitemManager').treegrid('beginEdit', row.id);
			}
			else {
				$.messager.alert('提示', '请选中一个非“字典”项目！');
			}
		}else{
			$.messager.alert('提示', '请选中一个项目！');
		}
	}
	// 删除
	function core_dict_edit_dictitemManager_remove(){
		if(editing){
			$('#core_dict_edit_dictitemManager').treegrid('endEdit',editingId);
			editing = false;
		}
		var row = $('#core_dict_edit_dictitemManager').treegrid('getSelected');
		if(row.id=='000'){
			$.messager.alert('提示', '请选中一个非“字典”项目！');
		}else{
			$('#core_dict_edit_dictitemManager').treegrid('remove', row.id);
		}
	}
	// 保存
	function core_dict_edit_dictitemManager_save() {
		if(editing){
			$('#core_dict_edit_dictitemManager').treegrid('endEdit',editingId);
			editing = false;
		}
		$.messager.confirm('操作确认', '确定保存后，数据将<b style="color:red">无法恢复</b>！“取消”后可用“刷新”回到修改前的状态。',
			function (r) {
				if (r) {
					var erows = $('#core_dict_edit_dictitemManager').treegrid('getChanges', 'updated');
					var drows = $('#core_dict_edit_dictitemManager').treegrid('getChanges', 'deleted');
					var erowstr = encodeURI(JSON.stringify(erows));
					var drowstr = encodeURI(JSON.stringify(drows));

					$.post('${pageContext.request.contextPath}/core/dict/itemsave.do', {e: erowstr,	d: drowstr},
							function (data) {
								parent.$.messager.show({title : '提示',	msg : data.msg	});
								$('#core_dict_edit_dictitemManager').treegrid('acceptChanges');
								$('#core_dict_edit_dictitemManager').treegrid('reload');
							}
					, 'json');
				}
			});
	}

	//刷新
	function core_dict_edit_dictitemManager_refresh(){
		editing = false;
		$('#core_dict_edit_dictitemManager').treegrid('acceptChanges');
		$('#core_dict_edit_dictitemManager').treegrid('load');
	}

	//======================================表单提交相关======================================
		//提交表单
	function postForm(){
		$('#core_dict_edit_form').form('submit', {
			url : '${pageContext.request.contextPath}/core/dict/edit_deal.do',
			success : function(r) {
				var o = jQuery.parseJSON(r);
				if(o.success){
					parent.$('#core_dict_manager_grid').datagrid('updateRow',{index:${param.index}, row:o.obj});
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
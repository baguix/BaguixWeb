<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<<script type="text/javascript">
<!--
function SS_chpwd_showDialog(){
	$('#main_changePassword_opwd').val('');
	$('#main_changePassword_npwd').val('');
	$('#main_changePassword_rpwd').val('');
	$('#main_changePassword').dialog({closed:false});
}
//-->
</script>
<div id="main_changePassword" class="easyui-dialog"
	style="width:370px;height:210px;padding:20px;"
	data-options="title:'修改密码',modal:true,closed:true,
					buttons:[{
						text:'确定',
						handler:function(){
							$('#main_changePassword_form').form('submit', {
								url : '${pageContext.request.contextPath}/manctrl/changepwd.action',
								success : function(r) {
									var o = jQuery.parseJSON(r);
									if (o.success) {
										$('#main_changePassword').dialog({closed:true});
										$.messager.show({title : '提示', msg : o.msg});
									}
								}
							});
						}
					},{
						text:'关闭',
						handler:function(){ $('#main_changePassword').dialog({closed:true});}
					}]">
	<form id="main_changePassword_form" method="post"
		action="${pageContext.request.contextPath}/chpwd.action">
		<ul>
			<li style="margin-top:6px;"><span style="width:100px;text-align:right;">当前用户：</span>
				<input id="main_changePassword_uid"
					name="uid" value="${sessionScope.user_name}[${sessionScope.uid}]"
					style="width:240px" disabled />
			</li>
			<li style="margin-top:6px;"><span style="width:100px;text-align:right;">当前密码：</span>
				<input id="main_changePassword_opwd"
					name="opwd" type="password" class="easyui-validatebox"
					data-options="required:true,
							validType:'myRemote[\'manctrl/checkpwd.action\',\'opwd\',5]'"
					style="width:240px" />
			</li>
			<li style="margin-top:6px;"><span style="width:100px;text-align:right;">新的密码：</span>
				<input id="main_changePassword_npwd"
					name="npwd" type="password" class="easyui-validatebox"
					data-options="required:true,validType:['notEqPwd[\'#main_changePassword_opwd\']','passwordRule']"
					style="width:240px" />
			</li>
			<li style="margin-top:6px;"><span style="width:100px;text-align:right;">确认密码：</span>
				<input type="password"
					class="easyui-validatebox"
					data-options="required:true,validType:'eqPwd[\'#main_changePassword_npwd\']'"
					style="width:240px" />
			</li>
		</ul>
	</form>
</div>
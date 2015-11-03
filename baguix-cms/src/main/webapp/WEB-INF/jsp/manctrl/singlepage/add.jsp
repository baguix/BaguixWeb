<%@ page language="java" pageEncoding="UTF-8"%>
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
		    $('#admin_singlepage_addForm_content').val(admin_singlepage_addeditor.getContent());
		    $('#admin_singlepage_add_abstracts').html(admin_singlepage_addeditor.getContentTxt().substring(0,50)+'...');
			$('#admin_singlepage_addForm').form('submit', {
				url : '${pageContext.request.contextPath}/admin/singlepage!add.action',
				success : function(r) {
					var o = jQuery.parseJSON(r);
					if (o.success) {
						$('#admin_singlepage_manager_grid').datagrid('insertRow',{
							index:0,
							row:o.obj
						});
						$('#admin_singlepage_addDialog').dialog('close');
					}
					$.messager.show({
						title : '提示',
						msg : o.msg
					});
				}
			});
		}
	}]
" style="width:1000px;height:600px;"
>
<form id="admin_singlepage_addForm" method="post" novalidate>
     <table style="width:960px;margin:0 auto;">
			<tbody>
				<tr>
					<td width="100px"></td>
					<td width="860px"></td>
				</tr>
				<tr>
					<td align="right">文章标题：</td>
					<td><input type="text" name="title" style="width:450px;float:left;" size="100"></input>
					<span id="ticolorshow" style="margin-left:10px;width:20px;height:20px;display:block;float:left;background:#000"></span> 
					<input type="hidden" name="titlecolor" style="width:80px;" size="50" / >
					<a href="#" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-colorbox'">选择标题颜色</a>
					<span align="right">发布时间：</span>
					<input id="admin_singlepage_add_ctime" class="easyui-datetimebox"  name="ctime" style="width:150px">
					<input type="hidden" name="type" value="single" / ><input type="hidden" name="typeid" / >
					</td>
				</tr>
				<tr>
				<td align="right" valign="top">文章内容：</td>
				<td>
				    
    				<script id="admin_singlepage_add_editor" type="text/plain" style="width:800px;height:360px;"></script>
    					<script type="text/javascript">
    						var admin_singlepage_addeditor = UE.getEditor('admin_singlepage_add_editor');
    					</script>
    					<input id="admin_singlepage_addForm_content" name="content" type="text"/>
				</td>
				</tr>
				<tr>
					<td align="right">来源：</td>
					<td><input type="text" name="source" style="width:500px;" size="100"></input>
					<span><=</span>
					<select><option>aaaaaaaaa</option></select>
				</td>
				</tr>
					<td align="right">作者：</td>
					<td>
					<input type="text" name="writer" style="width:500px;" size="100"></input>
					<span><=</span>
					<select><option>bbbbbbbbbb</option></select>
					</td>
				</tr>
				<tr>
					<td align="right">外部链接：</td>
					<td><input type="text" name="url" style="width:400px;" size="100"></input>
					<span>查看次数：</span>
					<input type="text" name="readnum" style="width:100px;" size="100"></input> 
					</td>
				</tr>
				<tr>
	
					<td align="right">关键词标签：</td>
					<td>
					<input type="text" name="keyword" style="width:640px;" size="100"></input> 
					<div style="color:#0066CC;">自动获取</div>
					</td>
				</tr>
				<tr>
					<td align="right">内容摘要：</td>
					<td><s:textarea id="admin_singlepage_add_abstracts" label="abstracts" name="abstracts" cols="40" rows="10" style="width:640px; height:60px;"/>
					<div style="color:#0066CC;">自动获取</div>
					</td>
				</tr>
					<td align="right">文章属性：</td>
					<td>
						<label title=已审核的文章才会在前台显示><input id=isaudit onclick=checkaddition() value=1 checked type=checkbox name=isaudit>已审核</label>&nbsp;&nbsp;
						<label title=出现在前台最新消息处><input id=isnew onclick=checkaddition() value=1 type=checkbox name=isnew>最新消息</label>&nbsp;&nbsp;
						<label title=首页栏目显示图片文章需要此属性，同时所属栏目【在首页显示图片文章】需要开启><input id=ishomethumb onclick=checkaddition() value=1 type=checkbox name=ishomethumb>首页缩略图</label>&nbsp;&nbsp;
						<label title=列表页中显示><input id=isthumb onclick=checkaddition() value=1 type=checkbox name=isthumb>缩略图</label>&nbsp;&nbsp;
						<label title=首页左上角幻灯片><input id=isflash onclick=checkaddition() value=1 type=checkbox name=isflash>幻灯片</label><br />
						<label title=首页中部滚动图片><input id=isimg onclick=checkaddition() value=1 type=checkbox name=isimg>滚动图片</label>&nbsp;&nbsp;
						<label title=页头滚动信息><input id=ismarquee onclick=checkaddition() value=1 type=checkbox name=ismarquee>滚动信息</label>&nbsp;&nbsp;
						<label title=出现在精彩推荐/本类推荐里><input id=isrecom onclick=checkaddition() value=1 type=checkbox name=isrecom>推荐</label>&nbsp;&nbsp;
						<label title=出现在最新消息第一条及列表页前几条><input id=istop onclick=checkaddition() value=1 type=checkbox name=istop>置顶</label>&nbsp;&nbsp;
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</div>

<script type="text/javascript">

function getNow() { 
	var now = new Date(); 
	var Year = now.getFullYear();; 
	var Month = now.getMonth()+1; 
	var Day = now.getDate();
	var Hour = now.getHours(); 
	var Minute = now.getMinutes(); 
	var Second = now.getSeconds(); 
	var CurrentDate = ""; 
	CurrentDate += Month + "/"+Day+ "/"+Year+" "+Hour+":"+Minute+":"+Second; 
	return CurrentDate; 
}
</script>
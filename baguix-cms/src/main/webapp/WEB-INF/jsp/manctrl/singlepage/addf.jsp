<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>${applicationScope.sitename}</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="${applicationScope.keyword}">
	<meta http-equiv="description" content="${applicationScope.description}">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/jquery.easyui.min.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/locale/easyui-lang-zh_CN.js"></script>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/easyui/themes/default/easyui.css" type="text/css"></link>
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/easyui/themes/icon.css" type="text/css"></link>
  	<!-- my lib -->
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/ext.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/easyui-validate.js"></script>
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/myscript.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/myicon.css">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/mystyle.css" type="text/css"></link>
  	<!-- ueditor -->
  	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.myconfig.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/ueditor.all.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/jslib/ueditor/lang/zh-cn/zh-cn.js"></script>
  </head>
  <body>

<form id="admin_singlepage_addForm" method="post">
<div style="width:960px;margin:0 auto;">
    <ul>
    	<li class="formli">
    		<span class="formlabel">文章标题：</span>
    		<span class="forminput">
    		<input type="text" name="title" style="width:488px;" size="100"></input>
    		<img src="${pageContext.request.contextPath}/jslib/myicons/colorbox.gif" onclick="" title="选择标题颜色"/>
    		<input type="hidden" name="titlecolor" style="width:80px;" size="50" />
    		</span>
    		<span class="formlabel">发布时间：</span>
    		<span class="forminput">
    		<input id="admin_singlepage_add_ctime" class="easyui-datetimebox" name="ctime" style="width:200px">
			<input type="hidden" name="type" value="single" />
			<input type="hidden" name="typeid" />
			</span>
    	</li>
    	<li class="formli">
    		<span class="formlabel">文章来源：</span>
    		<span class="forminput">
			<select id="source" class="easyui-combobox" name="source" data-option="panelHeight:auto" style="width:280px;"><option>行政办公室</option><option>网管中心</option></select>
			</span>
    		<span class="formlabel">文章作者：</span>
    		<span class="forminput">
			<select id="writer" class="easyui-combobox" name="writer" data-option="panelHeight:auto" style="width:280px;"><option>行政办公室</option><option>网管中心</option></select>
			</span>
			<span class="formlabel">查看次数：</span>
    		<span class="forminput">
    		<input type="text" name="readnum" style="width:58px;" value="0" size="20"></input>
    		</span>
    	</li>
    	<li class="formli">
    		<span class="formlabel">文章内容：</span>
      		<span class="forminput">
       		<script id="admin_singlepage_add_editor" type="text/plain" style="width:800px;height:360px;"></script>
			<script type="text/javascript">
					var admin_singlepage_addeditor = UE.getEditor('admin_singlepage_add_editor');
					//增加输入事件admin_singlepage_addeditor.addListener( "selectionchange",function () {});
			</script>
			<input id="admin_singlepage_addForm_content" name="content" type="hidden"/>
      		</span>
    	</li>
    	<li class="formli">
    		<div class="height5"></div>
    		<span class="formlabel">外部链接：</span>
    		<span class="forminput">
    		<input type="text" name="url" style="width:798px;" size="100"></input>
    		</span>
    	</li>
    	<li class="formli">
    		<span class="formlabel">关键词：</span>
    		<span class="forminput">
    		<input type="text" name="keyword" style="width:745px;" size="100"></input>
    		<span><a href="javascript:void(0);" onclick="getKeyword()" style="color:#0066CC;">自动获取</a></span>
    		</span>
    	</li>
    	<li class="formli">
    		<span class="formlabel">内容摘要：</span>
    		<span class="forminput">
    		<s:textarea id="admin_singlepage_add_abstracts" name="abstracts" cols="11" rows="10" style="width:745px; height:60px;border: #95b8e7 1px solid;"></s:textarea>
    		<span><a href="javascript:void(0);" onclick="getAbstract()" style="color:#0066CC;">自动获取</a></span>
    		</span>
    		<br />
    	</li>
    	<li class="formli">
    		<div class="height5"></div>

    	</li>
    	<li class="formli">
    		<span class="formlabel">文章属性：</span>
    		<span class="forminput">
    		<s:checkbox id="isaudit" name="isaudit" value="true" fieldValue="true"/> 已审&nbsp;&nbsp;
    		<s:checkbox id="isnew" name="isnew" value="true" fieldValue="true"/> 最新&nbsp;&nbsp;
    		<s:checkbox id="ishomethumb" name="ishomethumb" value="true" fieldValue="true"/> 专栏&nbsp;&nbsp;
    		<s:checkbox id="isthumb" name="isthumb" value="true" fieldValue="true"/> 缩图&nbsp;&nbsp;
    		<s:checkbox id="isflash" name="isflash" value="true" fieldValue="true"/> 幻灯&nbsp;&nbsp;
    		<s:checkbox id="isimg" name="isimg" value="true" fieldValue="true"/> 滚图&nbsp;&nbsp;
    		<s:checkbox id="ismarquee" name="ismarquee" value="true" fieldValue="true"/> 滚信&nbsp;&nbsp;
    		<s:checkbox id="isrecom" name="isrecom" value="true" fieldValue="true"/> 推荐&nbsp;&nbsp;
    		<s:checkbox id="istop" name="istop" value="true" fieldValue="true"/> 置顶
			</span>
    	</li>
    	<li class="formli">
    		<span class="formlabel">是否发布：</span>
    		<span class="forminput">
    		<s:radio name="state" list="#{'true':'显示','false':'隐藏'}" listKey="key" listValue="value" value="'true'"/>
			</span>
    	</li>
    </ul>
	
</div>
</form>


<script type="text/javascript">
$('#admin_singlepage_addForm').form('clear');

//提交表单
function pForm(){
	
	setDatas();
	$('#admin_singlepage_addForm').form('submit', {
		url : '${pageContext.request.contextPath}/admin/singlepage!add.action',
		success : function(r) {
			var o = jQuery.parseJSON(r);
			if (o.success) {
				parent.$('#admin_singlepage_manager_grid').datagrid('insertRow',{
					index:0,
					row:o.obj
				});
				parent.$('#admin_singlepage_addDialog').dialog('close');
			}
			parent.$.messager.show({
				title : '提示',
				msg : o.msg
			});
		}
	});
}

//设置数据
function setDatas(){
	$('#admin_singlepage_addForm_content').val(admin_singlepage_addeditor.getContent());
	var abst = $('#admin_singlepage_add_abstracts').val();
	if(abst ==''){
		getAbstract();
	}
}

//自动获取摘要
function getAbstract() {
	$('#admin_singlepage_add_abstracts').val($.trim(admin_singlepage_addeditor.getContentTxt().substring(0,100))+'...');    
}

//获得当前时间
$('#admin_singlepage_add_ctime').datetimebox({  
	    value: getNow(),  
	    required: true,  
	    showSeconds: true  
});
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
</body>
</html>
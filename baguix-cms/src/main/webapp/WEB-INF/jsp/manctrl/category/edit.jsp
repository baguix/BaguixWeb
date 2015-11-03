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
  	<jsp:include page="../maininc.jsp"></jsp:include>
  	<style type="text/css">
  		/*easyui 修复combobox箭头显示不全*/
  		.combo-arrow{
			margin-left:-4px;
		}
  	</style>
  </head>
  <body>

<form id="manctrl_category_editForm" method="post">
<div style="margin:0 auto;">
    <ul>
    	<li class="formli">
    		<span class="addformlabel">栏目名称：</span>
    		<span class="forminput">
    		<input id="title" name="title" class="easyui-validatebox" data-options="required:true" style="width:500px;">
    		</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">打开模式：</span>
    		<span class="forminput">
			<input id="openmode" name="openmode" style="width:180px;" />
            </span>
        </li>
    	<li class="formli">
    		<span class="addformlabel">显示模式：</span>
    		<span class="forminput">
			<input id="showmode" name="showmode" style="width:180px;" />
            </span>
            <div id="preview"><img id="previewimg" src="${pageContext.request.contextPath}/manctrl/images/showmode/1.gif" style="border:0px;position:absolute;top:44px;left:287px;)"/></div>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">首页显示行数：</span>
    		<span class="forminput">
    		<input type="text" id="homenum" name="homenum" style="width:175px;" value="10" size="20" />
    		</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">子页显示行数：</span>
    		<span class="forminput">
    		<input type="text" id="shownum" name="shownum" style="width:175px;" value="10" size="20" />
    		</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">排序值：</span>
    		<span class="forminput">
    		<input type="text" id="rank" name="rank" style="width:175px;" value="10" size="20" />
    		</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">外部链接：</span>
    		<span class="forminput">
    		<input type="text" id="url" name="url" style="width:400px;" size="100" />
    		<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-news" onclick="uploadThumb()">选文章</a>
    		</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">关键词：</span>
    		<span class="forminput">
    		<s:textarea id="keyword" name="keyword" cols="11" rows="10" style="width:500px; height:60px;border: #95b8e7 1px solid;"></s:textarea>
    		</span>
    		<br />
    	</li>
    	<li class="formli">
    		<span class="addformlabel">描述：</span>
    		<span class="forminput">
    		<s:textarea id="desc" name="desc" cols="11" rows="10" style="width:500px; height:60px;border: #95b8e7 1px solid;"></s:textarea>
    		</span>
    		<br />
    	</li>
    	<li class="formli">
    		<div class="height5"></div>
    		<span class="addformlabel">缩略图：</span>
    		<span class="forminput">
    		<input type="text" id="thumb" name="thumb" style="width:330px;" size="100" ></input>
    		<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-upload" onclick="uploadThumb()">上传</a>
    		<a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-image" onclick="thumbSelector()">选择</a>
    		<input type="hidden" id="thumbstr" name="thumbstr"></input>
    		<img id="thumbshow" style="display:none;position:absolute;left:450px;width:120px;height:80px;"/>
    		<script type="text/javascript">
    		$('#thumb').hover(
    		    function(){
    		    	if($('#thumb').val()!=""){
    		    		$('#thumbshow').attr('src', $('#thumb').val());
        				$('#thumbshow').show();
    		    	}
    		   	},
    		   	function(){
    				$('#thumbshow').hide();
    		   	}
    		    );
    		</script>
    		</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">文章属性：</span>
    		<span class="formin">
    		<ss:SysDict dictname="categoryprop" name="proper" id="proper" init="backdata.proper"></ss:SysDict>
			</span>
    	</li>
    	<li class="formli">
    		<span class="addformlabel">是否发布：</span>
    		<span class="formin">
    		<ss:SysDict dictname="online" name="isonline" id="isonline" init="backdata.isonline"></ss:SysDict>
			</span>
    	</li>
    </ul>
	<input id="level" name="level" type="hidden" value="" />
	<input id="id" name="id" type="hidden" value="" />
	<input id="pid" name="pid" type="hidden" value="" />
</div>
</form>


<script type="text/javascript">
$('#openmode').combobox({
	valueField:'value',
    textField:'text',
    panelHeight:'auto',
	data: [{'value':'_blank','text':'新窗口(_blank)'},
	       {'value':'_self','text':'本窗口(_self)'}]
});
$('#showmode').combobox({
  	 valueField:'value',
     textField:'text',
     panelHeight:'auto',
  	 data: [{'value':'1','text':'列表框'},
         	  {'value':'2','text':'分类列表框'},
         	  {'value':'3','text':'横排分类列表框'},
         	  {'value':'4','text':'标题+摘要'},
         	  {'value':'5','text':'标题+摘要+缩略图1'},
         	  {'value':'6','text':'标题+摘要+缩略图2'},
         	  {'value':'7','text':'组图'}],
     onSelect: function(r){
                $('#previewimg').attr('src', '${pageContext.request.contextPath}/manctrl/images/showmode/'+r.value+'.gif');
               }
});

//提交表单
function pForm(){
	$('#manctrl_category_editForm').form('submit', {
		url : '${pageContext.request.contextPath}/manctrl/category!edit.action',
		success : function(r) {
			var o = jQuery.parseJSON(r);
			if (o.success) {
				//更新该行
				parent.$('#manctrl_category_manager_grid').treegrid('update',{id:o.obj.id, row:o.obj});
				//更新父节点的maxsubrank
				if(parseInt(backdata.maxsubrank)< $('#rank').val()){
					var prow = parent.$('#manctrl_category_manager_grid').treegrid('find',backdata.id);
					prow.maxsubrank = $('#rank').val();
					parent.$('#manctrl_category_manager_grid').treegrid('update',{id:prow.id, row:prow});
				}
				parent.sys_add_Dialog.dialog('close');
			}
			parent.$.messager.show({
				title : '提示',
				msg : o.msg
			});
		}
	});
}

//上传缩略图弹框
function uploadThumb() {
	parent.SysUploadDialog("缩略图上传","manctrl/category/thumbup.action","");
}

function thumbSelector() {
	parent.SysImageDialog("缩略图选择器","manctrl/category/thumbslector.action","",590,400);
}

//缩略图回设值
function setThumb(v) {
	$('#thumb').val(v);
	is = $('#thumbstr').val()+","+v;
	var arr = new Array();
	arr=is.split(",");
	arr.unique();
	is = arr.join(",");
	$('#thumbstr').val(is);
}

//缩略图选择器需要的函数（一）
function getUploadImgTitle(){
	name = $('#thumbstr').val();
	return name;
}
//缩略图选择器需要的函数（二）
function getUploadImgSrc(){
	name = $('#thumbstr').val();
	return name;
}


//======================================系统弹窗事件Begin======================================

	//打开窗口时执行以下程序
	var opened, backdata, target;
	if(opened){
		$('#level').val(backdata.level);
		$('#id').val(backdata.id);
		$('#pid').val(backdata.pid);
		$('#title').val(backdata.title);
		$('#homenum').val(backdata.homenum);
		$('#shownum').val(backdata.shownum);
		$('#rank').val(backdata.rank);
		$('#url').val(backdata.url);
		$('#keyword').html(backdata.keyword);
		$('#desc').html(backdata.desc);
		$('#thumb').val(backdata.thumb);
		$('#thumbstr').val(backdata.thumbstr);
		$('#ismenu').attr('checked', backdata.ismenu);
		$('#issubmenu').attr('checked', backdata.issubmenu);
		$('#ishome').attr('checked', backdata.ishome);
		$('#isspecial').attr('checked', backdata.isspecial);
		if(backdata.isonline){
			$('#isonlinetrue').attr('checked', true);
			$('#isonlinefalse').attr('checked', false);
		}
		else{
			$('#isonlinetrue').attr('checked', false);
			$('#isonlinefalse').attr('checked', true);
		}
		$('#openmode').combobox('setValue', backdata.openmode);
		$('#showmode').combobox('setValue', parseInt(backdata.showmode));
		$('#previewimg').attr('src', '${pageContext.request.contextPath}/manctrl/images/showmode/'+backdata.showmode+'.gif');
	}
	//关闭函数
	function closeEvent(){
	}
	
	//按"确定"按钮函数
	function okEvent(){
		pForm();
	}
//======================================系统弹窗事件End======================================

</script>

</body>
</html>
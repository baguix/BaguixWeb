<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>缩略图上传</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/plupload/queue/css/jquery.plupload.queue.css" type="text/css"></link>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/plupload.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/plupload.html4.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/plupload.html5.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/plupload.flash.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/queue/jquery.plupload.queue.js"></script>
  </head>
  <body style="padding: 0;margin: 0;">
    <div id="uploader">&nbsp;</div>
<script type="text/javascript">
//主窗口，回调时使用
var target;

//plUpload自带功能
var files = [];
var errors = [];

//服务器返回
var urls = '';
var newfiles = '';

var type = 'file';
var chunk = eval('${param.chunk}');
//指定缩略图的上传路径(相对于上传文件夹根目录)
var uploaddir = "thumb";
var max_file_size = '20mb';
var filters = {title : "图片文件(jpg,gif,png)", extensions : "jpg,gif,png"};
$("#uploader").pluploadQueue($.extend({
	runtimes : 'flash,html4,html5',
	url : '${pageContext.request.contextPath}/updownAction!uploadImg.action',
	//此处缩略图上传不裁切不缩小，完全按原图上传。所以uptype=0
	multipart_params: {'uptype': 0, 'path': uploaddir, 'picwidth': ${applicationScope.thumbWidth}, 'picheight': ${applicationScope.thumbHeight}, 'thumbw': 0, 'thumbh': 0},
	max_file_size : max_file_size,
	file_data_name:'file',
	unique_names:true,
	multi_selection: false,
	filters : [filters],
	flash_swf_url : '${pageContext.request.contextPath}/jslib/plupload/plupload.flash.swf',
	init:{
		FilesAdded: function (up, files) {
		    $.each(up.files, function (i, file) {
		        if (up.files.length <= 1) {
		            return;
		        }
		        up.removeFile(file);
		    });
		},
		FileUploaded:function(uploader,file,response){
			if(response.response){
				var rs = $.parseJSON(response.response);
				if(rs.status){
					files.push(file.name);
					urls = rs.urls;
					newfiles = rs.files;
				}else{
					errors.push(file.name);
				}
			}
		},
		UploadComplete:function(uploader,fs){
			var e= errors.length ? ",失败"+errors.length+"个("+errors.join("、")+")。" : "。";
			alert("上传完成！共"+fs.length+"个。成功"+files.length+e);
			target.window("close");
		}
	}
},(chunk ? {chunk_size:'1mb'} : {})));


//关闭窗口时的回调函数

function closeEvent(method){
	parent.main_dialog_iframe[0].contentWindow.setThumb(urls);
}

</script>
  </body>
</html>

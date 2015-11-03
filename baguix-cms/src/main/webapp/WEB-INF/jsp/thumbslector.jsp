<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图片选择器</title>
    <meta http-equiv="description" content="${applicationScope.description}">
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery.min.js"></script>
  	<!-- image picker -->
  	<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/imagepicker/imagepicker.js"></script>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/imagepicker/imagepicker.css">
  </head>
  <body>
  <div style="padding:10px 0px;height:310px;overflow:auto;">
  	<div id="c"></div>
  </div>
  <script type="text/javascript">
    var imgname, imgsrc;
    function genHTML(title, src) {
    	var html = "";
    	if(title!="" && (typeof(title)!="undefined")){
    		var t = title.split(",");
            var s = src.split(",");
            html = "<select id ='selectpic' class='image-picker show-html'><option value=''></option>";
            for (i = 0; i < t.length; i++) {
            	if(s[i]!=""){
            		html += "<option data-img-src='"+s[i]+"' value='"+s[i]+"'>"
           	             + t[i].substr(t[i].lastIndexOf("/")+1) + "</option>";
            	}
			}
			html += "</select>";
    	}else{
    		html+="<div style='font-size:12px;color:#f00;'>本文从未上传过图片，上传后才能进行选择！</div>";
    	}
    	return html;
	}
    
    $(function() {
		$("#c").html(genHTML(imgname, imgsrc));
		$("#selectpic").imagepicker({
             hide_select : true,
             show_label : true
		})
	});
    
    //关闭窗口时的回调函数
    function closeEvent(method){
    	 parent.maxdlg[0].contentWindow.setUploadImgTitle(imgname);
    	 parent.maxdlg[0].contentWindow.getUploadImgSrc(imgsrc);
    }
    
    //打开窗口时的回调程序，无法使用回调函数，此乃折中办法
    var opened,target;
    if(opened){
    	imgname = target.contentWindow.getUploadImgTitle();
    	imgsrc = target.contentWindow.getUploadImgSrc();
    }
    
    //按确定按钮时的回调函数
    function okEvent(method){
    	target.contentWindow.setThumb($("#selectpic").val());
    }
    
  	//按删除图片按钮时的回调函数
    function delEvent(method){
    	DelImg(imgname,imgsrc,$("#selectpic").val());
		$("#c").html(genHTML(imgname, imgsrc));
		$("#selectpic").imagepicker({
             hide_select : true,
             show_label : true
		})
    }
  	//从imgsrc里面删除一个图片
    function DelImg(title, src, svalue) {
    	if(svalue!="" && (typeof(svalue)!="undefined")){
            var s = src.split(",");
            var t = title.split(",");
            var news = new Array();
            var newt = new Array();
            for (i = 0; i < s.length; i++) {
            	if(s[i]!=svalue){
            		news.push(s[i]);
            		newt.push(t[i]);
            	}
			}
            imgname = newt.join(",");
            imgsrc = news.join(",");
    	}
	}
</script>
	
</body>
</html>


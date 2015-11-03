<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

	//根据iframe对象获取iframe的window对象
	function GetFrameWindow(frame){
		return frame && typeof(frame)=='object' && frame.tagName == 'IFRAME' && frame.contentWindow;
	}
	
	//根据iframe的id获取iframe的document对象
	function getIFrameDOM(id){  
	    return document.getElementById(id).contentDocument || document.frames[id].document;  
	}

	//====================================================上传窗口====================================================
	function SysUploadDialog(caption, url, basepath) {
		if (url != null) {
			var sys_upload_Dialog = $('<div style="overflow: hidden;"/>');
			var upladoer = $('<iframe/>');
			upladoer.attr({
				'src' : '${pageContext.request.contextPath}/' + url,
				width : '100%',
				height : '100%',
				frameborder : '0',
				scrolling : 'no'
			});
			sys_upload_Dialog.window({
				title : caption,
				height : 350,
				width : 550,
				modal : true,
				collapsible : false,
				maximizable : false,
				resizable : false,
				content : upladoer,
				onClose : function() {
					var fw = GetFrameWindow(upladoer[0]);
					fw.closeEvent();
				},
				onOpen : function() {
					var target = $(this);
					setTimeout(function(){
						var fw = GetFrameWindow(upladoer[0]);
						fw.target = target;
					},100);
				}
			});
		}
	}
	
	
	
	//====================================================系统图片选择弹窗====================================================
	function SysImageDialog(caption, url, basepath, w, h) {
		if (url != null) {
			var sys_image_Dialog = $('<div style="overflow: hidden;"/>');
			var imagedlg = $('<iframe/>');
			imagedlg.attr({
				'src' : '${pageContext.request.contextPath}/' + url,
				width : '100%',
				height : '100%',
				frameborder : '0',
				scrolling : 'no'
			});
			sys_image_Dialog.dialog({
				title : caption,
				height : h,
				width : w,
				modal : true,
				collapsible : false,
				maximizable : false,
				resizable : false,
				content : imagedlg,
				buttons:[{
					text : '删除图片',
					iconCls : 'icon-cancel',
					handler : function() {
						var fw = GetFrameWindow(imagedlg[0]);
						fw.delEvent();
					}
				},{
					text : '确定',
					iconCls : 'icon-confirm',
					handler : function() {
						var fw = GetFrameWindow(imagedlg[0]);
						fw.okEvent();
						sys_image_Dialog.dialog('close');
					}
				}],
				onClose : function() {
					var fw = GetFrameWindow(imagedlg[0]);
					fw.closeEvent();
				},
				onOpen : function() {
					var target = $(this);
					setTimeout(function(){
						var fw = GetFrameWindow(imagedlg[0]);
						fw.opened = 1;
						fw.target = target;
					},100);
				}
			});
		}
	}
	
	
	
	//====================================================新增/编辑弹窗====================================================
	var sys_add_Dialog = $('<div style="overflow: hidden;"/>');
	var adddlg = $('<iframe/>');

	function SysAddDialog(caption, url, w, h, data) {
		if (url != null) {
			adddlg.attr({
				'src' : '${pageContext.request.contextPath}/' + url,
				width : '100%',
				height : '100%',
				frameborder : '0'
			});
			sys_add_Dialog.dialog({
				title : caption,
				height : h,
				width : w,
				minimizable : false,
				maximizable : true,
				modal : true,
				content : adddlg,
				buttons:[{
					text : '确定',
					iconCls : 'icon-confirm',
					handler : function() {
						var fw = GetFrameWindow(adddlg[0]);
						fw.okEvent();
					}},
					{
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							sys_add_Dialog.dialog('close');
						}
				}],
				onClose : function() {
					var fw = GetFrameWindow(adddlg[0]);
					fw.closeEvent();
				},
				onOpen : function() {
					var target = $(this);
					setTimeout(function(){
						var fw = GetFrameWindow(adddlg[0]);
						fw.target = target;
						fw.opened = 1;
						fw.backdata = data;
					},100);
				}
			});
		}
	}
	
	var sys_max_Dialog = $('<div style="overflow: hidden;"/>');
	var maxdlg = $('<iframe />');

	function SysMaxDialog(caption, url, w, h, data) {
		var par ='';
		if (data != null){
			par += '?'+jQuery.param(data);
		}
		if (url != null) {
			maxdlg.attr({
				'src' : '${pageContext.request.contextPath}/' + url + par,
				width : '100%',
				height : '100%',
				frameborder : '0'
			});
			
			sys_max_Dialog.dialog({
				title : caption,
				height : h,
				width : w,
				maximized : true,
				minimizable : false,
				maximizable : true,
				modal : true,
				content : maxdlg,
				buttons:[{
					text : '确定',
					iconCls : 'icon-confirm',
					handler : function() {
						var fw = GetFrameWindow(maxdlg[0]);
						fw.okEvent();
					}},
					{
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							sys_max_Dialog.dialog('close');
						}
				}],
				onClose : function() {
					var fw = GetFrameWindow(maxdlg[0]);
					fw.closeEvent();
					maxdlg.attr({'src' : 'loading.jsp'});
				},
				onOpen : function() {
					var target = $(this);
					setTimeout(function(){
						var fw = GetFrameWindow(maxdlg[0]);
						fw.target = target;
					},100);
				},
				onRestore:function(){
					var fw = GetFrameWindow(maxdlg[0]);
					fw.restoreEvent();
				}
			});
		}
	}
	
	
	//====================================================系统查询弹窗====================================================
	var sys_search_Dialog = $('<div style="overflow: hidden;"/>');
	var searchdlg = $('<iframe/>');

	function SysSearchDialog(caption, url, w, h) {
		if (url != null) {
			searchdlg.attr({
				'src' : '${pageContext.request.contextPath}/' + url,
				width : '100%',
				height : '100%',
				frameborder : '0'
			});
			sys_search_Dialog.dialog({
				title : caption,
				height : h,
				width : w,
				minimizable : false,
				maximizable : true,
				modal : true,
				content : searchdlg,
				buttons:[
					{
						text : '查看全部',
						iconCls : 'icon-find',
						handler : function() {
							var fw = GetFrameWindow(searchdlg[0]);
							fw.findEvent();
							sys_search_Dialog.dialog('close');
						}
					},{
						text : '查询',
						iconCls : 'icon-search',
						handler : function() {
							var fw = GetFrameWindow(searchdlg[0]);
							fw.okEvent();
						}
					},{
						text : '取消',
						iconCls : 'icon-cancel',
						handler : function() {
							sys_search_Dialog.dialog('close');
						}
					}
				],
				onClose : function() {
					var fw = GetFrameWindow(searchdlg[0]);
					fw.closeEvent();
				},
				onOpen : function() {
					var target = $(this);
					setTimeout(function(){
						var fw = GetFrameWindow(searchdlg[0]);
						fw.target = target;
						fw.opened = 1;
					},100);
				}
			});
		}
	}
</script>
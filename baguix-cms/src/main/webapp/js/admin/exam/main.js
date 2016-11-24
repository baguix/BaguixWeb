var index_maintree = $('#index_maintree');
var index_maintab = $('#index_maintab');
$(function() {
	index_maintree.tree({
		url: "/tk/json/maintree.json",
		method: 'get',
		onClick: function(node) {
			var opts = {
				title: node.text,
				closable: true,
				content: getIframe(node.attributes.url),
				border: false,
				fit: true
			};
			addTab(opts, node.attributes.url);
		}
	});
	index_maintab.tabs({
		fit: true,
		border: false,
		onSelect: function(title, index) {
			var content = index_maintab.tabs('getSelected');
			if (content && content.find('iframe').length > 0) {
				var tab_if = content.find('iframe')[0];
				tab_if.contentWindow.location.href = tab_if.src;
			}
		}
	});
});

function addTab(opts, href) {
	if (index_maintab.tabs('exists', opts.title)) {
		index_maintab.tabs('select', opts.title);
		refreshTab({
			title: opts.title,
			href: href
		});
	} else {
		index_maintab.tabs('add', opts);
	}
}

function getIframe(href) {
	if (href) {
		var content = '<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="no" allowtransparency="yes" src="' + href + '" style="width:100%;height:99%;"></iframe>';
	} else {
		var content = '建设中';
	}
	return content;
}

function refreshTab(otps) {
	var content = otps.title ? index_maintab.tabs('getTab', otps.title) : index_maintab.tabs('getSelected');
	if (content && content.find('iframe').length > 0) {
		var tab_if = content.find('iframe')[0];
		var if_url = otps.href ? otps.href : tab_if.src;
		tab_if.contentWindow.location.href = if_url;
	}
}

//====================================================系统====================================================

//根据iframe对象获取iframe的window对象
function GetFrameWindow(frame) {
	return frame && typeof(frame) == 'object' && frame.tagName == 'IFRAME' && frame.contentWindow;
}

//根据iframe的id获取iframe的document对象
function getIFrameDOM(id) {
	return document.getElementById(id).contentDocument || document.frames[id].document;
}

//====================================================弹窗====================================================
var sys_Dialog = $('<div style="overflow: hidden;"/>');
var sysdlg = $('<iframe/>');

function SysDialog(caption, url, w, h, data) {
	// 组装参数
	var par = '';
	if (data != null) {
		par += '?' + jQuery.param(data);
	}
	if (url != null) {
		sysdlg.attr({
			'src': url + par,
			width: '100%',
			height: '98%',
			frameborder: '0'
		});
		sys_Dialog.dialog({
			title: caption,
			height: h,
			width: w,
			minimizable: false,
			maximizable: true,
			modal: true,
			content: sysdlg,
			buttons: [{
				text: '确定',
				iconCls: 'icon-confirm',
				handler: function() {
					var fw = GetFrameWindow(sysdlg[0]);
					fw.okEvent();
				}
			}, {
				text: '取消',
				iconCls: 'icon-cancel',
				handler: function() {
					sys_Dialog.dialog('close');
				}
			}],
			onClose: function() {
				var fw = GetFrameWindow(sysdlg[0]);
				fw.closeEvent();
			},
			onOpen: function() {
				var target = $(this);
				var fw = GetFrameWindow(sysdlg[0]);
				fw.target = target;
			}
		});
	}
}

var sys_Dialog1 = $('<div style="overflow: hidden;"/>');
var sysdlg1 = $('<iframe/>');

function SysDialog1(caption, url, w, h, data) {
	// 组装参数
	var par = '';
	if (data != null) {
		par += '?' + jQuery.param(data);
	}
	if (url != null) {
		sysdlg1.attr({
			'src': url + par,
			width: '100%',
			height: '98%',
			frameborder: '0'
		});
		sys_Dialog1.dialog({
			title: caption,
			height: h,
			width: w,
			minimizable: false,
			maximizable: true,
			modal: true,
			content: sysdlg1,
			buttons: [{
				text: '确定',
				iconCls: 'icon-confirm',
				handler: function() {
					var fw = GetFrameWindow(sysdlg1[0]);
					fw.okEvent();
				}
			}, {
				text: '取消',
				iconCls: 'icon-cancel',
				handler: function() {
					sys_Dialog1.dialog('close');
				}
			}],
			onClose: function() {
				var fw = GetFrameWindow(sysdlg1[0]);
				fw.closeEvent();
			},
			onOpen: function() {
				var target = $(this);
				var fw = GetFrameWindow(sysdlg1[0]);
				fw.target = target;
			}
		});
	}
}



var sys_MaxDialog = $('<div style="overflow: hidden;"/>');
var sysmaxdlg = $('<iframe/>');

function sysMaxDialog(caption, url, w, h, data) {
	// 组装参数
	var par = '';
	if (data != null) {
		par += '?' + jQuery.param(data);
	}
	if (url != null) {
		sysmaxdlg.attr({
			'src': url + par,
			width: '100%',
			height: '100%',
			frameborder: '0'
		});
		sys_MaxDialog.dialog({
			title: caption,
			height: h,
			width: w,
			maximized : true,
			minimizable: false,
			maximizable: true,
			modal: true,
			content: sysmaxdlg,
			buttons: [{
				text: '确定',
				iconCls: 'icon-confirm',
				handler: function() {
					var fw = GetFrameWindow(sysmaxdlg[0]);
					fw.okEvent();
				}
			}, {
				text: '取消',
				iconCls: 'icon-cancel',
				handler: function() {
					sys_MaxDialog.dialog('close');
				}
			}],
			onClose: function() {
				var fw = GetFrameWindow(sysmaxdlg[0]);
				fw.closeEvent();
			},
			onOpen: function() {
				var target = $(this);
				var fw = GetFrameWindow(sysmaxdlg[0]);
				fw.target = target;
			}
		});
	}
}

// EasyUI的渲染完全加载后才显示body
$.parser.onComplete  = function () {
	$('body').css('visibility', 'visible');
}
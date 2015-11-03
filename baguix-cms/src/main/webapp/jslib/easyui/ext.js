/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 指定$.messager.show的显示位置
 * @param left
 * @param top
 * @param bottom
 */
 /*
 【示例】
 <a href="#" class="easyui-linkbutton" onclick="showBySite(event)">help</a>
 function showBySite(event){
	var element = document.elementFromPoint(event.x,event.y);//获取点击对象
	$.messager.showBySite({
		title:'My Title',
		msg:'Message.',
		showType:'show'
	},{
		top : $(element).position().top+$(element).height(),//将$.messager.show的top设置为点击对象之下
		left : $(element).position().left,//将$.messager.show的left设置为与点击对象对齐
		bottom : ""
	});
}
*/
$.extend($.messager, {
	showBySite : function(options,param) {
		var site = $.extend( {
			left : "",
			top : "",
			right : 0,
			bottom : -document.body.scrollTop
					- document.documentElement.scrollTop
		}, param || {});
		var win = $("body > div .messager-body");
		if(win.length<=0)
			$.messager.show(options);
		win = $("body > div .messager-body");
		win.window("window").css( {
			left : site.left,
			top : site.top,
			right : site.right,
			zIndex : $.fn.window.defaults.zIndex++,
			bottom : site.bottom
		});
	}
});


/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
	var l = left;
	var t = top;
	if (l < 1) {
		l = 1;
	}
	if (t < 1) {
		t = 1;
	}
	var width = parseInt($(this).parent().css('width')) + 14;
	var height = parseInt($(this).parent().css('height')) + 14;
	var right = l + width;
	var buttom = t + height;
	var browserWidth = $(window).width();
	var browserHeight = $(window).height();
	if (right > browserWidth) {
		l = browserWidth - width;
	}
	if (buttom > browserHeight) {
		t = browserHeight - height;
	}
	$(this).parent().css({/* 修正面板位置 */
		left : l,
		top : t
	});
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;
/**
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};
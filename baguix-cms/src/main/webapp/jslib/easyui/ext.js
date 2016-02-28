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

/**
 * 扩展easyui datagrid的两个方法.动态添加和删除toolbar的项(适用于1.3.0之后的版本)
 * 作者：夏悸
 * 用法：
 * $('#tt').datagrid("addToolbarItem",[{"text":"xxx"},"-",{"text":"xxxsss","iconCls":"icon-ok"}])
 * $('#tt').datagrid("removeToolbarItem","GetChanges")//根据btn的text删除
 * $('#tt').datagrid("removeToolbarItem",0)//根据下标删除
 */
$.extend($.fn.datagrid.methods, {
	addToolbarItem : function (jq, items) {
		return jq.each(function () {
			var dpanel = $(this).datagrid('getPanel');
			var toolbar = dpanel.children("div.datagrid-toolbar");
			if (!toolbar.length) {
				toolbar = $("<div class=\"datagrid-toolbar\"><table cellspacing=\"0\" cellpadding=\"0\"><tr></tr></table></div>").prependTo(dpanel);
				$(this).datagrid('resize');
			}
			var tr = toolbar.find("tr");
			for (var i = 0; i < items.length; i++) {
				var btn = items[i];
				if (btn == "-") {
					$("<td><div class=\"datagrid-btn-separator\"></div></td>").appendTo(tr);
				} else {
					var td = $("<td></td>").appendTo(tr);
					var b = $("<a href=\"javascript:void(0)\"></a>").appendTo(td);
					b[0].onclick = eval(btn.handler || function () {});
					b.linkbutton($.extend({}, btn, {
						plain : true
					}));
				}
			}
		});
	},
	removeToolbarItem : function (jq, param) {
		return jq.each(function () {
			var dpanel = $(this).datagrid('getPanel');
			var toolbar = dpanel.children("div.datagrid-toolbar");
			var cbtn = null;
			if (typeof param == "number") {
				cbtn = toolbar.find("td").eq(param).find('span.l-btn-text');
			} else if (typeof param == "string") {
				cbtn = toolbar.find("span.l-btn-text:contains('" + param + "')");
			}
			if (cbtn && cbtn.length > 0) {
				cbtn.closest('td').remove();
				cbtn = null;
			}
		});
	}
});


/**
 * 扩展panel，增加边框设置
 * 作者：Scott
 * 用法：
 * <div id="pp" class="easyui-panel" title="aaaa">aa</div>
 * <script type="text/javascript">
 *     $('#pp').panel('setBorder',{top:0,bottom:0});
 * </script>
 */
$.extend($.fn.panel.methods, {
	setBorder: function(jq, option) {
		jq.css("border-right-width", option.right + "px");
		jq.css("border-bottom-width", option.bottom + "px");
		jq.css("border-left-width", option.left + "px");
		jq.css("border-top-width", option.bodytop + "px");
		jq.panel({
			onOpen: function() {
				jq.prev(".panel-header").css("border-top-width", option.top + "px");
			}
		});
	}
})

/**
 * 扩展layout，可以设置每个region的边框
 * 作者：Scott
 * 用法：
 * <script type="text/javascript">
 *     $("#rights").layout('setBorder',{center:{bodytop:0,right:0},south:{right:0}});
 * </script>
 */
$.extend($.fn.layout.methods, {
	setBorder: function(jq, option){
		if(option.center){
			jq.layout().layout('panel','center').panel('setBorder',option.center);
		}
		if(option.east){
			jq.layout().layout('panel','east').panel('setBorder',option.east);
		}
		if(option.west){
			jq.layout().layout('panel','west').panel('setBorder',option.west);
		}
		if(option.north){
			jq.layout().layout('panel','north').panel('setBorder',option.north);
		}
		if(option.south){
			jq.layout().layout('panel','south').panel('setBorder',option.south);
		}
	}
})

//获取树形节点层级
$.extend($.fn.tree.methods, {
	getLevel:function(jq,target){
		var l = $(target).parentsUntil("ul.tree","ul");
		return l.length+1;
	}
})

//更多的扩展可以参考这里http://jqext.sinaapp.com/#docs/doc.html?jdirk.html
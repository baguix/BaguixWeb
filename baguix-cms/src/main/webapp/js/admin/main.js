/**
 * 在main_tabs上增加一个Tab
 */
function SS_main_addMainTab(opts) {
    if (main_tabs.tabs('exists', opts.title)) {
        main_tabs.tabs('select', opts.title);
    } else {
        main_tabs.tabs('add', opts);
    }
}

/**
 * 截取一段字符串
 * 例如：SS_main_cutString("abcdefg","abc","fg")="de"
 */
function SS_main_cutString(str, begin, end) {
    var value = str.substring(begin.length, str.lastIndexOf(end));
    return value;
}

/**
 * 单击NavMenu导航菜单项时调用此函数。
 * 左边的树将显示相应的树形列表。
 * 根据树形节点的数据URL，根据URL的格式，判断进行以下操作：
 * 1.addTabHref(###地址###);                            1.以href形式在main_tabs上增加一个tab
 * 2.addTabIframe(###地址###);                        2.以iFrame形式在main_tabs上增加一个tab
 * 3.doAction(###地址`~参数###);                        3.执行一个Struts2的action，结果以JSON形式返回到一个EasyUI的Messager上。
 * 4.doJSCode(###代码###);                            4.执行一段JavaScript代码。
 * 5.goURLSelf(###地址###);                            5.在自身窗体打开一个URL链接
 * 6.goURLBlank(###地址###);                            6.在新窗口打开一个URL链接
 */
function SS_main_menuTree(pid) {
    main_tree.tree({
        url: ss_basepath+'/manctrl/lefttree?pid=' + pid,
        onClick: function (node) {
            var action = $.trim(String(node.attributes.url));
            //根据URL数据判断操作

            //1.以href形式在main_tabs上增加一个tab
            if (action.indexOf("addTabHref(###") == 0 && action.lastIndexOf("###)") > 12) {//addTabHref(###有12个字符
                var addr = $.trim(SS_main_cutString(action, "addTabHref(###", "###)"));
                if (addr == "") {
                    $.messager.alert('错误码(JSmain00002)', '指向的地址为空！', 'error');
                } else {
                    var opts = {
                        title: node.text,
                        closable: true,
                        href: ss_basepath+"/"+addr,
                        border: false,
                        fit: true
                    };
                    SS_main_addMainTab(opts);
                }
            } else
            //2.以iFrame形式在main_tabs上增加一个tab
            if (action.indexOf("addTabIframe(###") == 0 && action.indexOf("###)") > 14) {
                var addr = $.trim(SS_main_cutString(action, "addTabIframe(###", "###)"));
                if (addr == "") {
                    $.messager.alert('错误码(JSmain00003)', '指向的地址为空！', 'error');
                } else {
                    var iframe = '<iframe id="SS_mainFrame_' + node.id + '" src="' + ss_basepath+"/"+addr + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>';
                    var opts = {
                        title: node.text,
                        closable: true,
                        content: iframe,
                        border: false,
                        fit: true
                    };
                    SS_main_addMainTab(opts);
                }
            } else
            //3.执行一个Struts2的action，结果以JSON形式返回到一个EasyUI的Messager上。
            if (action.indexOf("doAction(###") == 0 && action.indexOf("###)") > 10) {
                var urlNparams = SS_main_cutString(action, "doAction(###", "###)");
                var addr = "";
                var params = "";
                if (urlNparams.indexOf("`~") > 0) {   //用键盘左上角的两个`~符号分割地址和参数。
                    addr = $.trim(urlNparams.split("`~")[0]);
                    params = $.trim(urlNparams.split("`~")[1]);
                }
                else {
                    addr = $.trim(urlNparams);
                }
                if (addr == "") {
                    $.messager.alert('错误码(JSmain00004)', '无法访问空地址！', 'error');
                }
                if (params == "") {
                    params = '({})';
                }
                var d = eval(params);
                $.ajax({
                    url: ss_basepath+"/"+addr,
                    // data : {'id': id},     数据 的格式
                    data: d,
                    dataType: 'json',
                    success: function (r) {
                        //结果返回
                        $.messager.show({title: '提示', msg: r.msg});
                    }
                });
            } else
            //4.执行一段JavaScript代码。
            if (action.indexOf("doJSCode(###") == 0 && action.indexOf("###)") > 9) {
                var code = $.trim(SS_main_cutString(action, "doJSCode(###", "###)"));
                if (code == "") {
                    $.messager.alert('错误码(JSmain00005)', '无法执行空代码！', 'error');
                } else {
                    eval(code);
                }
            } else
            //5.在自身窗体打开一个URL链接
            if (action.indexOf("goURLSelf(###") == 0 && action.indexOf("###)") > 11) {
                var addr = $.trim(SS_main_cutString(action, "goURLSelf(###", "###)"));
                if (addr == "") {
                    $.messager.alert('错误码(JSmain00006)', '无法转向空地址！', 'error');
                } else {
                    window.location.href = addr;
                }
            } else
            //6.在新窗口打开一个URL链接
            if (action.indexOf("goURLBlank(###") == 0 && action.indexOf("###)") > 11) {
                var addr = $.trim(SS_main_cutString(action, "goURLBlank(###", "###)"));
                if (addr == "") {
                    $.messager.alert('错误码(JSmain00007)', '无法打开空地址！', 'error');
                } else {
                    window.open(addr, "_blank");
                }
            }
            else {
                $.messager.alert('错误码(JSmain00001)', '没有[' + action + ']操作！', 'error');
            }

        },// onClick
        onLoadError: function (err) {
            $.messager.alert('错误码(JSmain0008)', '数据加载错误！', 'error');
            //window.location.href = logout_page;
        }// onLoadError
    });// tree
}

//导航菜单直接执行JS
function SS_main_doJSCode(code) {
    if (code != null && code != "") {
        eval(code);
    }
}
//导航菜单直接跳转URL
function SS_main_goURLSelf(addr) {
    if (addr != null && addr != "") {
        window.open(addr, "_blank");
    }
}

//导航菜单直接打开URL
function SS_main_goURLBlank(addr) {
    if (addr != null && addr != "") {
        window.location.href = addr;
    }
}


//选择不同的TAB，触发载入相应的左树，用于main_tabs的onSelect事件。
function tabmenu(m) {
    $.ajax({
        url: 'tree_ptext.do',
        data: {"ptext": m},
        type: "post",
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: 'json',
        success: function (r) {
            SS_main_menuTree(r);
        }
    });
}

var info_Dialog;
var info_dialog_iframe;
//SiteInfo修改对话框
function SS_siteinfo_Dialog(caption, url, w, h) {
    if (url != null) {
        info_Dialog = $('<div style="overflow: hidden;"/>');
        info_dialog_iframe = $('<iframe/>');
        info_dialog_iframe.attr({
            'src': url,
            width: '100%',
            height: '100%',
            frameborder: '0'
        });
        info_Dialog.dialog({
            title: caption,
            height: h,
            width: w,
            modal: true,
            collapsible: false,
            maximizable: false,
            resizable: false,
            content: info_dialog_iframe,
            buttons: [{
                text: '确定',
                iconCls: 'icon-confirm',
                handler: function () {
                    var fw = GetFrameWindow(info_dialog_iframe[0]);
                    fw.okEvent();
                    info_Dialog.dialog('close');
                }
            },
                {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        info_Dialog.dialog('close');
                    }
                }],
            onClose: function () {
                var fw = GetFrameWindow(info_dialog_iframe[0]);
                fw.closeEvent();
            },
            onOpen: function () {
                var target = $(this);
                setTimeout(function () {
                    var fw = GetFrameWindow(info_dialog_iframe[0]);
                    fw.target = target;
                }, 100);
            }
        });
    }
}

var img_Dialog;
var img_dialog_iframe;
//ImgInfo修改对话框
function SS_img_Dialog(caption, url, w, h) {
    if (url != null) {
        img_Dialog = $('<div style="overflow: hidden;"/>');
        img_Dialog_iframe = $('<iframe/>');
        img_Dialog_iframe.attr({
            'src': url,
            width: '100%',
            height: '100%',
            frameborder: '0'
        });
        img_Dialog.dialog({
            title: caption,
            height: h,
            width: w,
            modal: true,
            collapsible: false,
            maximizable: false,
            resizable: false,
            content: img_Dialog_iframe,
            buttons: [{
                text: '确定',
                iconCls: 'icon-confirm',
                handler: function () {
                    var fw = GetFrameWindow(img_Dialog_iframe[0]);
                    fw.okEvent();
                    img_Dialog.dialog('close');
                }
            },
                {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        img_Dialog.dialog('close');
                    }
                }],
            onClose: function () {
                var fw = GetFrameWindow(img_Dialog_iframe[0]);
                fw.closeEvent();
            },
            onOpen: function () {
                var target = $(this);
                setTimeout(function () {
                    var fw = GetFrameWindow(img_Dialog_iframe[0]);
                    fw.target = target;
                }, 100);
            }
        });
    }
}

var sys_Dialog;
var sys_dialog_iframe;
//ImgInfo修改对话框
function SS_sys_Dialog(caption, url, w, h) {
    if (url != null) {
        sys_Dialog = $('<div style="overflow: hidden;"/>');
        sys_Dialog_iframe = $('<iframe/>');
        sys_Dialog_iframe.attr({
            'src': url,
            width: '100%',
            height: '100%',
            frameborder: '0'
        });
        sys_Dialog.dialog({
            title: caption,
            height: h,
            width: w,
            modal: true,
            collapsible: false,
            maximizable: false,
            resizable: false,
            content: sys_Dialog_iframe,
            buttons: [{
                text: '确定',
                iconCls: 'icon-confirm',
                handler: function () {
                    var fw = GetFrameWindow(sys_Dialog_iframe[0]);
                    fw.okEvent();
                    sys_Dialog.dialog('close');
                }
            },
                {
                    text: '取消',
                    iconCls: 'icon-cancel',
                    handler: function () {
                        sys_Dialog.dialog('close');
                    }
                }],
            onClose: function () {
                var fw = GetFrameWindow(sys_Dialog_iframe[0]);
                fw.closeEvent();
            },
            onOpen: function () {
                var target = $(this);
                setTimeout(function () {
                    var fw = GetFrameWindow(sys_Dialog_iframe[0]);
                    fw.target = target;
                }, 100);
            }
        });
    }
}

<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>${applicationScope.sitename}</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <jsp:include page="../maininc.jsp"></jsp:include>
    <!-- 拾色器 -->
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/jslib/color/scp/jquery.colorPicker.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/jslib/color/scp/colorPicker.css" type="text/css"/>
</head>
<body>
<div>
    <form id="manctrl_imgtool_form" method="post">
        <script type="text/javascript">
            var cutp = ${cutPic};
            var isthum = ${thumbPic};
            var wmktype = ${watermark};
            var wmkpos = '${watermarkPosition}';
            var bord = ${border};
        </script>

        <fieldset>
            <legend>缩/裁原图功能</legend>
            <table>
                <tr>
                    <td class="formlabel">是否开启：</td>
                    <td><ss:SysDict dictname="yesno" name="cutPic" id="cutPic" init="cutp"></ss:SysDict></td>
                </tr>
                <tr>
                    <td class="formlabel">图片尺寸(px)：</td>
                    <td>
                        <input id="picWidth" name="picWidth" class="easyui-numberspinner" style="width:100px;"
                               required="required" data-options="value:${picWidth},min:1,max:1600,editable:true"> X
                        <input id="picHeight" name="picHeight" class="easyui-numberspinner" style="width:100px;"
                               required="required" data-options="value:${picHeight},min:1,max:1600,editable:true">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>缩略图功能</legend>
            <table>
                <tr>
                    <td class="formlabel">是否开启：</td>
                    <td><ss:SysDict dictname="yesno" name="isThumb" id="isThumb" init="isthum"></ss:SysDict></td>
                </tr>
                <tr>
                    <td class="formlabel">缩略图尺寸(px)：</td>
                    <td>
                        <input id="thumbWidth" name="thumbWidth" class="easyui-numberspinner" style="width:100px;"
                               required="required" data-options="value:${thumbWidth},min:1,max:1600,editable:true"> X
                        <input id="thumbHeight" name="thumbHeight" class="easyui-numberspinner" style="width:100px;"
                               required="required" data-options="value:${thumbHeight},min:1,max:1600,editable:true">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>图片边框功能</legend>
            <table>
                <tr>
                    <td class="formlabel">是否开启：</td>
                    <td>
                        <ss:SysDict dictname="yesno" name="border" id="border" init="bord"></ss:SysDict>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">边框颜色：</td>
                    <td>
                        <input type="text" id="borderColor" name="borderColor" style="width:100px;color:${borderColor};"
                               title="边框颜色" value="${borderColor}"/>
                        <input type="text" id="wmbcp" name="wmbcp" style="width:100px;" size="100" title="边框颜色"
                               value=""/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">边框大小：</td>
                    <td>
                        <input id="borderSize" name="borderSize" class="easyui-numberspinner" style="width:80px;"
                               required="required" data-options="value:${borderSize},min:1,max:30,editable:true">
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>水印</legend>
            <table>
                <tr>
                    <td class="formlabel">类型选择：</td>
                    <td>
                        <ss:SysDict dictname="watermark" name="watermark" id="watermark"
                                    init="wmktype"></ss:SysDict>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印的位置：</td>
                    <td>
                        <ss:SysDict dictname="watermarkpos" name="watermarkpos" id="watermarkpos"
                                    init="wmkpos"></ss:SysDict>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印文字内容：</td>
                    <td>
                        <input type="text" id="markText" name="markText" style="width:300px;color:${markTextColor};"
                               title="缩略图宽度" value="${markText}"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印文字颜色：</td>
                    <td>
                        <input type="text" id="markTextColor" name="markTextColor"
                               style="width:100px;color:${markTextColor};" title="水印颜色" value="${markTextColor}"/>
                        <input type="text" id="wmcp" name="wmcp" style="width:100px;" size="100" title="水印颜色"
                               value=""/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印文字字号：</td>
                    <td>
                        <input id="markTextSize" name="markTextSize" class="easyui-numberspinner"
                               style="width:80px;"
                               required="required" data-options="value:${markTextSize},min:1,max:100,editable:true">
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印图片：</td>
                    <td>
                        <input type="text" id="markImg" name="markImg" style="width:250px;" title="图片水印"
                               value="${markImg}"/>
                        <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-upload"
                           onclick="uploadThumb()">上传</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-image"
                           onclick="thumbSelector()">选择</a>
                        <input type="hidden" id="watermarkimgstr" name="watermarkimgstr" value="${markImgStr}">
                        <img id="watermarkimgshow" style="display:none;position:absolute;z-index:999"/>
                        <script type="text/javascript">
                            $('#watermarkimg').hover(
                                    function (e) {
                                        if ($('#watermarkimg').val() != "") {
                                            $('#watermarkimgshow').attr('src', $('#watermarkimg').val());
                                            $('#watermarkimgshow').css('left', e.pageX);
                                            $('#watermarkimgshow').css('top', e.pageY);
                                            $('#watermarkimgshow').show();
                                        }
                                    },
                                    function () {
                                        $('#watermarkimgshow').hide();
                                    }
                            );
                        </script>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印图片透明度：</td>
                    <td>
                        <input id="markImgAlpha" name="markImgAlpha" class="easyui-numberspinner"
                               style="width:80px;"
                               required="required" data-options="value:${markImgAlpha},min:5,max:80,editable:true">
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<script type="text/javascript">
    <!--
    function submitForm() {
        $('#manctrl_imgtool_form').form('submit', {
            url: '${pageContext.request.contextPath}/manctrl/imageinfo-save.do',
            success: function (r) {
                var o = jQuery.parseJSON(r);
                if (o.success) {
                    parent.$.messager.show({
                        title: '提示',
                        msg: o.msg
                    });
                }
            }
        });
    }
    var initColors=[
        "EEEEEE", "DDDDDD", "CCCCCC", "BBBBBB", "999999", "000000",
        "FF0000", "00FF00", "0000FF", "FFFF00", "00FFFF", "FF00FF",
        "FFCC00", "FF9900", "FF3300", "FF0000", "CC0000", "CC6600",
        "99CC00", "99CC99", "00CC00", "009900", "999900", "339999",
        "00CCFF", "0099FF", "0033FF", "0000CC", "3399CC", "663399",
        "FF99FF", "CC00FF", "CC6600", "FF3399", "CC3399", "990099"
    ];
    //水印颜色
    $('#wmcp').colorPicker({
        pickerDefault: '${markTextColor}',
        colors: initColors,
        showHexField: false
    });
    $('#wmcp').change(function(){
        $('#markTextColor').css('color',$('#wmcp').val()).focus();
        $('#markTextColor').val($('#wmcp').val()).css('color',$('#wmcp').val());
    });

    //边框颜色
    $('#wmbcp').colorPicker({
        pickerDefault: '${borderColor}',
        colors: initColors,
        showHexField: false
    });
    $('#wmbcp').change(function(){
        $('#borderColor').css('color',$('#wmbcp').val());
        $('#borderColor').val($('#wmbcp').val()).css('color',$('#wmbcp').val());
    });


    //上传缩略图弹框
    function uploadThumb() {
        parent.SysUploadDialog("缩略图上传", "manctrl/imageinfo/thumbup.do", "", "add");
    }

    function thumbSelector() {
        parent.SysImageDialog("缩略图选择器", "manctrl/imageinfo/thumbslector.do", "", 590, 400, "add");
    }

    //缩略图回设值
    function setThumb(v) {
        $('#watermarkimg').val(v);
        is = $('#watermarkimgstr').val() + "," + v;
        var arr = new Array();
        arr = is.split(",");
        arr.unique();
        is = arr.join(",");
        $('#watermarkimgstr').val(is);
    }

    //缩略图选择器需要的函数（一）
    function getUploadImgTitle() {
        name = $('#watermarkimgstr').val();
        return name;
    }
    //缩略图选择器需要的函数（二）
    function getUploadImgSrc() {
        name = $('#watermarkimgstr').val();
        return name;
    }
    //缩略图选择器需要的函数（三）
    function setUploadImgTitle(name) {
        $('#watermarkimgstr').val(name);
    }
    //缩略图选择器需要的函数（四）
    function setUploadImgSrc(name) {
        $('#watermarkimgstr').val(name);
    }
    //======================================系统弹窗事件Begin======================================
    //关闭函数
    function closeEvent() {
    }

    //按"确定"按钮函数
    function okEvent() {
        submitForm();
    }
    var opened, backdata, target;
    if (opened) {
        opened = 0;
    }
    //======================================系统弹窗事件End======================================

    $(function () {
        $('body').css('visibility', 'visible');
    });
    //-->
</script>
</body>
</html>
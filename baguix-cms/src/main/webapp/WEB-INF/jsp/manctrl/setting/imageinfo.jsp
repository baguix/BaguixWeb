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
            // 字典初值
            var cutp = ${cutPic};
            var isthum = ${thumbPic};
            var bord = ${border};
            var wmktype = ${watermark};
            var wmkpos = '${watermarkPosition}';
        </script>
        <fieldset>
            <legend>缩/裁原图功能</legend>
            <table>
                <tr>
                    <td class="formlabel">是否开启：</td>
                    <td><ss:SysDict dictname="yesno" name="cutPic" id="cutPic" init="cutp" /></td>
                </tr>
                <tr>
                    <td class="formlabel">图片尺寸(px)：</td>
                    <td>
                        <ss:euNumber id="picWidth" css="width:100px;" val="${picWidth}" dataoption="min:1,max:1600,editable:true,required:'required'"/>
                        ×
                        <ss:euNumber id="picHeight" css="width:100px;" val="${picHeight}" dataoption="min:1,max:1600,editable:true,required:'required'"/>
                    </td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <legend>缩略图功能</legend>
            <table>
                <tr>
                    <td class="formlabel">是否开启：</td>
                    <td><ss:SysDict dictname="yesno" name="thumbPic" id="thumbPic" init="isthum"/></td>
                </tr>
                <tr>
                    <td class="formlabel">缩略图尺寸(px)：</td>
                    <td>
                        <ss:euNumber id="thumbWidth" css="width:100px;" val="${thumbWidth}" dataoption="min:1,max:1600,editable:true,required:'required'"/>
                        ×
                        <ss:euNumber id="thumbHeight" css="width:100px;" val="${thumbHeight}" dataoption="min:1,max:1600,editable:true,required:'required'"/>
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
                        <ss:SysDict dictname="yesno" name="border" id="border" init="bord"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">边框颜色：</td>
                    <td>
                        <ss:euColorBox id="borderColor" val="${borderColor}"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">边框大小：</td>
                    <td>
                        <ss:euNumber id="borderSize" val="${borderSize}" dataoption="min:1,max:30,editable:true,required:'required'" css="width:80px;"/>
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
                                    init="wmktype"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印的位置：</td>
                    <td>
                        <ss:SysDict dictname="watermarkpos" name="watermarkPosition" id="watermarkpos"
                                    init="wmkpos"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印文字内容：</td>
                    <td>
                        <ss:euTextBox id="markText" val="${markText}" />
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印文字颜色：</td>
                    <td>
                        <ss:euColorBox id="markTextColor" val="${markTextColor}"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印文字字号：</td>
                    <td>
                        <ss:euNumber id="markTextSize" val="${markTextSize}" dataoption="min:1,max:100,editable:true,required:'required'" css="width:80px;"/>
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印图片：</td>
                    <td>
                        <img  id="imgshow" style="border:1px solid #ccc;display:block;height:120px;" />
                        <div style="height:5px;"> </div>
                        <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-upload"
                           onclick="uploadThumb()">上传</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" icon="icon-image"
                           onclick="thumbSelector()">选择</a>
                        <input id="markImg" name="markImg" type="hidden" value="${markImg}"/>
                        <input id="markImgStr" name="markImgStr" type="hidden" value="${markImgStr}" />
                    </td>
                </tr>
                <tr>
                    <td class="formlabel">水印图片透明度：</td>
                    <td>
                        <ss:euNumber id="markImgAlpha" val="${markImgAlpha}" dataoption="min:5,max:80,editable:true,required:'required'" css="width:80px;"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </form>
</div>
<script type="text/javascript">
    <!--
    if($('#markImg').val() != ""){
        var img = "${pageContext.request.contextPath}"+ $('#markImg').val();
    }else{
        var img = "${pageContext.request.contextPath}/images/nopic120.gif";
    }
    $('#imgshow').attr('src', img );

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

    //上传缩略图弹框
    function uploadThumb() {
        parent.SysUploadDialog("水印Logo上传", "manctrl/imageinfo/logoup.do", "", "add");
    }

    function thumbSelector() {
        parent.SysImageDialog("水印Logo选择器", "manctrl/imageinfo/logoslector.do", "", 590, 400, "add");
    }

    //缩略图回设值
    function setThumb(v) {
        $('#markImg').val(v);
        is = $('#markImgStr').val() + "," + v;
        var arr = new Array();
        arr = is.split(",");
        arr.unique();
        is = arr.join(",");
        $('#markImgStr').val(is);
        if($('#markImg').val() != ""){
            var img = "${pageContext.request.contextPath}"+ $('#markImg').val();
        }else{
            var img = "${pageContext.request.contextPath}/images/nopic120.gif";
        }
        $('#imgshow').attr('src', img );
    }

    //缩略图选择器需要的函数（一）
    function getUploadImgTitle() {
        name = $('#markImgStr').val();
        return name;
    }
    //缩略图选择器需要的函数（二）
    function getUploadImgSrc() {
        name = $('#markImgStr').val();
        return name;
    }
    //缩略图选择器需要的函数（三）
    function setUploadImgTitle(name) {
        $('#markImg').val(name);
    }
    //缩略图选择器需要的函数（四）
    function setUploadImgSrc(name) {
        $('#markImgStr').val(name);
        if($('#markImg').val() != ""){
            var img = "${pageContext.request.contextPath}"+ $('#markImg').val();
        }else{
            var img = "${pageContext.request.contextPath}/images/nopic120.gif";
        }
        $('#imgshow').attr('src', img );
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
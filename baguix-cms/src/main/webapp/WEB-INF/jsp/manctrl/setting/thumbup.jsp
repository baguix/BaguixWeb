<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
    <title>图片上传</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/jquery-ui/jquery-ui.min.css" >
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/jslib/plupload/jquery.ui.plupload/css/jquery.ui.plupload.css" >
</head>
<body style="padding: 0;margin: 0;">
<div id="uploader"></div>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/jquery-ui/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/plupload.full.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/jquery.ui.plupload/jquery.ui.plupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jslib/plupload/i18n/zh_CN.js"></script>
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

    // 指定缩略图的上传路径(相对于上传文件夹根目录)
    var uploaddir = "thumb";
    // 最大支持文件大小（支持b, kb, mb, gb, tb后缀）
    var max_file_size = '20mb';
    var filters = {title: "图片文件(jpg,gif,png)", extensions: "jpg,gif,png"};
    $("#uploader").plupload({
        runtimes : 'html5,flash,silverlight,html4',
        url : '${pageContext.request.contextPath}/manctrl/uploadImg.do',
        // 缩略图上传不裁切不缩小，完全按原图上传。所以uptype=0
        multipart_params: {'uptype': 0, 'path': uploaddir, 'picwidth': ${thumbWidth}, 'picheight': ${thumbHeight}, 'thumbw': 0, 'thumbh': 0},
        // 限制只能选择一个文件
        max_file_count:1,
        max_file_size : max_file_size,
        chunk_size: '1mb',
        // 缩略图配置
        resize : {
            width : 200,
            height : 200,
            quality : 90,
            crop: true
        },
        // Specify what files to browse for
        filters : filters,
        // Rename files by clicking on their titles
        rename: true,
        // Sort files
        sortable: true,
        // Enable ability to drag'n'drop files onto the widget (currently only HTML5 supports that)
        dragdrop: true,
        // Views to activate
        views: {
            list: true,
            thumbs: true, // Show thumbs
            active: 'thumbs'
        },
        // Flash settings
        flash_swf_url : '${pageContext.request.contextPath}/jslib/plupload/Moxie.swf',
        // Silverlight settings
        silverlight_xap_url : '${pageContext.request.contextPath}/jslib/plupload/Moxie.xap',
        uploaded:function(uploader,file,response){
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
        }
    });

    //关闭窗口时的回调函数

    function closeEvent(method) {
        parent.main_dialog_iframe[0].contentWindow.setThumb(urls);
    }

</script>
</body>
</html>

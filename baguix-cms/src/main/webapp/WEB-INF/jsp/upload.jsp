<%--
  Created by IntelliJ IDEA.
  User: Scott
  Date: 2015/7/8
  Time: 7:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <title>您好</title>
  <link href="/jslib/stream/stream-v1.css" rel="stylesheet" type="text/css">
</head>
<body>
<form method="post" action="/upfile.do" enctype="multipart/form-data">
  描述：<input type="text" name="desc" />
  文件：<input type="file" name="file" />
  <input type="submit" value="上传" />
</form>

<input type="text" id="picture" name="cover" /><a href="javascript:void(0);" onclick="upImage();">上传图片</a>

<input type="text" id="file" /><a href="javascript:void(0);" onclick="upFiles();">上传文件</a>
<br>
<img id="preview">
<script type="text/plain" id="upload_ue"></script>

<script src="http://cdn.bootcss.com/jquery/1.10.2/jquery.min.js"></script>
<script type="text/javascript" src="/jslib/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="/jslib/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
  var _editor;
  $(function() {
    //重新实例化一个编辑器，防止在上面的editor编辑器中显示上传的图片或者文件
    _editor = UE.getEditor('upload_ue');
    _editor.ready(function () {
      //设置编辑器不可用
      //_editor.setDisabled();
      //隐藏编辑器，因为不会用到这个编辑器实例，所以要隐藏
      _editor.hide();
      //侦听图片上传
      _editor.addListener('beforeInsertImage', function (t, arg) {
        //将地址赋值给相应的input,只去第一张图片的路径
        $("#picture").attr("value", arg[0].src);
        //图片预览
        $("#preview").attr("src", arg[0].src);
      })
      //侦听文件上传，取上传文件列表中第一个上传的文件的路径,
       /* 文件上传监听
        * 需要在ueditor.all.min.js文件中找到
        * plugins/insertfile.js中的：
        * me.execCommand('insertHtml', html);
        * 之后插入me.fireEvent('afterUpfile', filelist);
        */
      _editor.addListener('afterUpfile', function (t, arg) {
        $("#file").val(arg[0].url);
      })
    });
  });
  //弹出图片上传的对话框
  function upImage() {
    var myImage = _editor.getDialog("insertimage");
    myImage.open();
  }
  //弹出文件上传的对话框
  function upFiles() {
    var myFiles = _editor.getDialog("attachment");
    myFiles.open();
  }
</script>
</body>
</html>

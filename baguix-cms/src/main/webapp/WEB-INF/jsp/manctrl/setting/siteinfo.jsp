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
</head>
<body class="easyui-layout" style="visibility:hidden">
<script type="text/javascript">
    <!--
    function submitForm() {
        $('#manctrl_siteinfo_form').form('submit', {
            url: '${pageContext.request.contextPath}/manctrl/siteinfo-save.do',
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
    //-->
</script>

<div class="easyui-panel" style="padding:10px;width:90%;margin:0 auto;" data-options="fit:true,border:false">

    <form id="manctrl_siteinfo_form" method="post" action="siteinfo-deal.do">
        <table style="width:650px;margin:0 auto;">
            <tr>
                <td class="ct" align="right">网站名称：</td>
                <td class="cc">
                    <input type="text" id="title" name="title" value="${title}" style="width:500px;" size="100"/>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站标题连接符：</td>
                <td class="cc">
                    <input type="text" id="titleSeparator" name="titleSeparator" value="${titleSeparator}"
                           style="width:50px;" size="5"/>

                    <div class="tips">
                            <span class="tips">
                                如：连接符是“<span class="redfont">-</span>”。列表页：网站名 <span class="redfont">-</span> 栏目名；内容页：网站名 <span class="redfont">-</span> 栏目名 <span class="redfont">-</span> 文章标题
                            </span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站副标题：</td>
                <td class="cc">
                    <input type="text" id="subTitle" name="subTitle" value="${subTitle}" style="width:500px;"
                           size="100"/>

                    <div class="tips">副标题与网站名称之间不会自动添加连接符。</div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站网址：</td>
                <td class="cc">
                    <input type="text" id="siteUrl" name="siteUrl" value="${siteUrl}" style="width:500px;"
                           size="100"/>

                    <div class="tips">网站网址如有填写，前台文章路径将加入该网址做绝对路径引用。</div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站关键词：</td>
                <td class="cc">
                        <textarea id="keywords" name="keywords" style="width:500px; height:60px;" cols="40"
                                  rows="5">${keywords}</textarea>

                    <div class="tips">全站默认关键词，有利于搜索引擎收录，注意：多个关键词请用英文逗号“,”隔开</div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站描述：</td>
                <td class="cc">
                        <textarea id="description" name="description" style="width:500px; height:60px;" cols="40"
                                  rows="5">${description}</textarea>

                    <div class="tips">全站默认关键词，有利于搜索引擎收录</div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">TCP/IP备案号：</td>
                <td class="cc">
                    <input type="text" id="icp" name="icp" value="${icp}" style="width:200px;" size="100"/>

                    <div class="c">
                        <span class="tips">工信部网站备案号</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">公安局备案号：</td>
                <td class="cc">
                    <input type="text" id="police" name="police" value="${police}" style="width:200px;" size="100"/>

                    <div class="tips">
                        <span class="tips">当地公安局备案号，例如广西网警为：http://www.gx.cyberpolice.cn</span>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">公安局备案代码：</td>
                <td class="cc">
                        <textarea id="policeCode" name="policeCode" style="width:500px; height:60px;" cols="40"
                                  rows="5">${policeCode}</textarea>

                    <div class="tips">全站默认关键词，有利于搜索引擎收录</div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站版权：</td>
                <td class="cc">
                        <textarea id="copyright" name="copyright" style="width:500px; height:60px;" cols="40"
                                  rows="5">${copyright}</textarea>

                    <div class="tips">留作版权信息使用</div>
                </td>
            </tr>
            <tr>
                <td class="ct" align="right">网站授权许可号：</td>
                <td class="cc">
                    <input type="text" id="license" name="license" value="${license}" style="width:500px;"
                           size="100"/>

                    <div class="tips">正版用户享受售后服务的凭证</div>
                </td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">
    <!--
    $(function () {
        $('body').css('visibility', 'visible');
    });
    //-->
</script>
</body>
</html>
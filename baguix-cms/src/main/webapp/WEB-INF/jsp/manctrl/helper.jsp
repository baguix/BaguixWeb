<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="easyui-accordion" data-options="fit:true,border:false">
	<div title="网站信息" data-options="iconCls:'icon-works'"
		style="overflow:auto;padding:5px;">
		<div class="easyui-calendar" style="width:180px;height:180px;"></div>
		<div>
		<ul>
			<li>服务器已经正常运行:2400(小时)</li>
			<li>访问总量:${applicationScope.AccessTimes}(个)</li>
			<li>访问总IP数:${applicationScope.IpCount}(IP)</li>
			<li>独立访客:${applicationScope.UV}(UV)</li>
			<li>浏览次数:${applicationScope.PV}(PV)</li>
		</ul>
		</div>
	</div>
	<div title="友情链接" data-options="iconCls:'icon-ok'"
		style="overflow:auto;padding:5px;">
		<ul>
			<li><a href="http://">百度推广</a></li>
			<li><a href="http://">百度站长平台</a></li>
			<li><a href="http://">搜狗推广</a></li>
			<li><a href="http://">百度商道</a></li>
			<li><a href="http://">新浪洽谈通</a></li>
			<li><a href="http://">阿里学院</a></li>
			<li><a href="http://">阿里巴巴供销平台</a></li>
		</ul>
	</div>
</div>
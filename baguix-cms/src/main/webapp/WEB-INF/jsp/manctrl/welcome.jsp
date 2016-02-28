<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ss" uri="/WEB-INF/sys.tld" %>
<div title="首页">
    <div style="padding:20px;line-height:30px;">
        <div style="padding:5px 15px;text-align:center;background:#8AD1E6;width:200px;font-size:20px;font-weight:bold;color:#fff">
            产 品 特 性
        </div>
        <div style="padding:10px;">
            <div style="float:left; margin-left:50px;width:200px;">
                <img src="${pageContext.request.contextPath}/images/manctrl/welcome/safe.png"/>
                <h4>完善的安全保护</h4>

                <p>多级安全保护措施，免除您的网站受到入侵者篡改和挂马的困扰。</p>
            </div>
            <div style="float:left; margin-left:50px;width:200px;">
                <img src="${pageContext.request.contextPath}/images/manctrl/welcome/setting.png"/>
                <h4>灵活的内容设置</h4>

                <p>一目了然的网站内容管理和栏目展现设置，方便您更好地的呈现信息。</p>
            </div>
            <div style="float:left; margin-left:50px;width:200px;">
                <img src="${pageContext.request.contextPath}/images/manctrl/welcome/feel.png"/>
                <h4>优秀的用户体验</h4>

                <p>美观的网站布局，简洁的操作界面，丰富的动态效果，灵敏的操作响应。</p>
            </div>
            <div style="float:left; margin-left:50px;width:200px;">
                <img src="${pageContext.request.contextPath}/images/manctrl/welcome/service.png"/>
                <h4>高效的售后服务</h4>

                <p>售后服务秉承快速响应和竭诚为您的理念，极大地降低您的运维成本。</p>
            </div>
        </div>
        <div class="cls"></div>
        <hr>
        <div id="container" style="margin:0px auto;width:1000px;height:300px"></div>
        <script language="JavaScript">
            $(function () {
                $('#container').highcharts({
                    title: {
                        text: '网站平均访问量'
                    },
                    subtitle: {
                        text: '${applicationScope.siteUrl}'
                    },
                    credits: {
                        enabled:false
                    },
                    xAxis: {
                        title: {
                            text: '日期'
                        },
                        categories: ['7天前', '6天前', '5天前', '4天前', '3天前', '2天前', '1天前', '今天']
                    },
                    yAxis: {
                        title: {
                            text: '数值',
                            rotation:0,
                            align:'high'
                        },
                        plotLines: [{
                            value: 0,
                            width: 1,
                            color: '#808080'
                        }]
                    },
                    legend: {
                        layout: 'vertical',
                        align: 'right',
                        verticalAlign: 'middle',
                        borderWidth: 0
                    },
                    series: [{
                        name: 'PV',
                        color:'#ff4d4d',
                        data: [70, 69, 95, 145, 182, 215, 252, 265]
                    },{
                        name: 'UV',
                        color:'#2693ff',
                        data: [45, 65, 75, 55, 35, 120, 110, 89]
                    }
                    ]
                });
            });
        </script>
        <div class="cls"></div>
        <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    </div>
</div>
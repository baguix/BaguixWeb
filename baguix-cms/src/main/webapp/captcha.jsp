<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<html>
<body>

<h2>您好！</h2>

<form action="captchatest.do" method="post">
    <div class="chknumber" style="line-height: 32px;height: 30px;">
        <label>验证码：
            <input name="kaptcha" type="text" id="kaptcha" maxlength="4" class="chknumber_input"/>
        </label>
        <input type="submit" value="提交"/>
        <img src="captcha.do" id="kaptchaImage" style="margin-bottom: -3px"/>
        <script src="http://cdn.bootcss.com/jquery/1.11.2/jquery.min.js"></script>
        <script type="text/javascript">
            $(function () {
                $('#kaptchaImage').click(function () {//生成验证码
                    $(this).hide().attr('src', 'captcha.do?' + Math.floor(Math.random() * 100)).fadeIn();
                })
            });
        </script>
    </div>
</form>
</body>
</html>

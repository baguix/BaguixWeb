$(function() {
	// 提示
	$('#username').placeholder({
		word: '用户名/邮箱/手机号'
	})
	$('#password').placeholder({
		word: '密码'
	})
	$('#authcode').placeholder({
		word: '验证码'
	})

	// 生成验证码
	$('#kaptchaImage').click(function () {
		$(this).hide().attr('src', 'captcha.do?' + Math.floor(Math.random() * 100)).fadeIn();
	})

})
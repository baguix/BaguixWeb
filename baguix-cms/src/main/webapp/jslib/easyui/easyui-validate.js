$.extend($.fn.validatebox.defaults.rules, {
	//验证中文  
	chinese:{
		validator:function(value){
			return /^[\u0391-\uFFE5]+$/.test(value);
		},
		message:"只能输入汉字."
	},
	
	//验证中文、英文字母、数字和下划线
	strline:{
		validator:function(value){
			return /^[\u0391-\uFFE5\w]+$/.test(value);
		},
		message:"只能包括中文字、英文字母、数字和下划线."
	},
	
	//验证中文,英文,数字
	str:{
		validator:function(value){
			return /^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(value);
		},
		message:"只能包括中文字、英文字母、数字."
	},
	
	//验证英文,数字
	english:{
		validator:function(value){
			return /^[a-zA-Z0-9]+$/.test(value);
		},
		message:"只能包括英文字母、数字."
	},
	
	//整数数字
	number:{
		validator:function(value){
			return /^[0-9]+$/.test(value);
		},
		message:"只能输入整数."
	},
	
	//手机号码验证
	mobile:{
		validator:function(value){
			var reg = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			return value.length == 11 && reg.test(value);
		},
		message:"请正确填写手机号码."
	},
	
	//固定电话号码验证
	telephone:{
		validator:function(value){
			//电话号码格式010-12345678
			var reg = /^\d{3,4}?\d{7,8}$/;
			var tel = /^\d{7,8}$/;
			return reg.test(value) || tel.test(value);
		},
		message:"请正确填写固定电话号码."
	},
	
	//联系电话(手机/电话皆可)验证 
	phone:{
		validator:function(value){
			var cmccMobile = /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
			var tel = /^\d{3,4}-\d{7,8}$/;
			var tel1 = /^\d{7,8}$/;
			return tel.test(value) || tel1.test(value) || (value.length == 11 && cmccMobile.test(value));
		},
		message:"请正确填写联系电话."
	},
	
	//验证国内邮编验证
	zipcode:{
		validator:function(value){
			var reg = /^[1-9]\d{5}$/;
			return reg.test(value);
		},
		message:"邮编必须长短0开端的6位数字."
	},
	
	//身份证号码验证 
	idCardNo:{
		validator:function(value){
			return isIdCardNo(value); 
		},
		message:"请正确输入您的身份证号码."
	}
});
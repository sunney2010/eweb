/** 用户登录 */
var login = {
	/**
	 * 登录
	 * 
	 * @returns {Boolean}
	 */
	goToLogin : function() {
		var userId = $("#userId").val();
		var password = $("#password").val();
		var url = '/checkLogin.html';
		var data = {
			"userId" : userId,
			"password" : password
		};
		sys.sendAjax(url, data, login.loginSuccess, login.error, 'POST', null,
				null, false);
	},
	error : function() {
		alert("error");
		window.location.href = "/login";
	},
	loginSuccess : function(data) {
		if (data.success == true) {// 登录成功
			window.location.href =  "/index.html";
		} else {
			alert("login error");
			// 其他异常 登录失败
			return;

		}
	}
};

/** 登录信息提示 */
var loginmsg = {
	uname_null : '请输入用户名!',
	uname_char_illegal : '用户名不存在,请重输!',
	uname_len_illegal : '用户名不存在,请重输!',
	upassword_null : '请输入密码!',
	upassword_len_illegal : '密码错误,请重输!',
	ucode_null : '请输入验证码!',
	ucode_len_illegal : '验证码错误,请重输!',
	login_illegal : '账号或密码错误',
	login_locked : '该账户已被锁定,请联系管理员处理',
	login_stoped : '该账户已被停用,请联系管理员处理',
	login_valication_time : '用户名或密码错误次数过多,该账户将锁定10分钟',
	login_exception : '登录异常'
};

// 回车提交
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		//$("#login").click();
		login.goToLogin();
	}
});

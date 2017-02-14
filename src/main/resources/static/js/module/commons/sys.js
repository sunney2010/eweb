var sys = {
   // path:_global.data.getGlobal("CONTEXT_PATH"),
	/**
	 * 跳转到登录
	 */
	relogin : function() {
		window.location.href = sys.path + "/login.do";
	},
	/**
	 * 权限判断
	 * @param url 数据库对应url
	 * @returns {Boolean} true有；false无
	 */
	checkIsLimit : function(url){
        var returnBoolean = false;
        var param = {"url" : url};
        sys.queryLimit(param,function(data){
        	returnBoolean=data;
        });
        return returnBoolean;
    },
	queryLimit : function(param,successFunction){
		var url=sys.path+"/menu/checkIsLimit.do";
		utils.ajax(url, param, successFunction, null, "POST", "json", null, null, false);
	},
	/**
	 * 获取cookie的值
	 */
	getCookieValue : function(cookieName) {
		var cookieArray = document.cookie.split("; ");
		for ( var i = 0; i < cookieArray.length; i++) {
			var arr = cookieArray[i].split("=");
			if (arr[0] == cookieName) {
				return unescape(arr[1]);
			}
		}
		return "";
	},
	/**
	 * 设置cookie中的信息
	 * @param name cookie的name
	 * @param name cookie的value
	 */ 
	setCookie : function(name, value, expires, path, domain, secure) 
	{     
		var today = new Date();     
		today.setTime( today.getTime() );     
		if ( expires ) 
		{         
			expires = expires * 1000 * 60 * 60 * 24;     
		}    
		var expires_date = new Date( today.getTime() + (expires) );     
		document.cookie = name+'='+escape( value ) +         
		( ( expires ) ? ';expires='+expires_date.toGMTString() : '' ) + //expires.toGMTString()         
		( ( path ) ? ';path=' + path : '' ) +         
		( ( domain ) ? ';domain=' + domain : '' ) +         
		( ( secure ) ? ';secure' : '' ); 
	},
	/**
	 * 删除cookie
	 * @param name cookie的name
	 */
	deleteCookie : function(name, path, domain) 
	{     
		if (sys.getCookieValue(name)) 
		{
		 document.cookie = name + '=' +             
		( ( path ) ? ';path=' + path : '') +             
		( ( domain ) ? ';domain=' + domain : '' ) +             
		';expires=Thu, 01-Jan-1970 00:00:01 GMT'; 
		}
	},
	/**
	 * 随机图片
	 */
	refreshCaptchaImg : function(imageId) {
		$('#' + imageId).attr("src", sys.path + "/images/loading.gif");
		$('#' + imageId).attr("src",
				sys.path + "/validateImage.jpg?a=" + store.getRandomStr());
	},
	/**
	 * 随机数字
	 */
	getRandomStr : function() {
		return parseInt(Math.random() * 1000);
	},
	/**
	 * 截取字符串
	 */
	interceptString : function(str, length) {
		if (str.length > length) {
			str = str.substr(0, length) + "...";
		}
		return str;
	},
	// 去左右空格;
	LRtrim : function(s) {
		function ltrim(s) {
			return s.replace(/^\s*/, "");
		}
		// 去右空格;
		function rtrim(s) {
			return s.replace(/\s*$/, "");
		}
		return rtrim(ltrim(s));
	},
	/**
	 * 当前时间
	 * 
	 * @return 'yyyy-mm-dd'
	 */
	getCurrentDate : function() {
		var curDate = new Date();
		var month = curDate.getMonth() + 1;
		var day = curDate.getDate();
		if (month < 10)
			month = "0" + month;
		if (day < 10)
			day = "0" + day;
		return curDate.getFullYear() + "-" + month + "-" + day;
	},
	/**
	 * 去字符串空格
	 */
	trim : function(temp) {
		return temp.replace(/(^\s*)|(\s*$)/g, "");
	},

	Jqtrim : function(str) {
		var result;
		var is_global = "g";
		result = str.replace(/(^\s+)|(\s+$)/g, "");
		if (is_global.toLowerCase() == "g")
			result = result.replace(/\s/g, "");
		return result;
	},
	/**
	 * 只能输入中文，英文和数字
	 */
	checkStr : function(str) {
		var pattern = /^[0-9a-zA-Z\u4e00-\u9fa5\s]+$/i;
		if (pattern.test(str)) {
			return true;
		} else {
			return false;
		}
	},

	/**
	 * 只能输入中文，英文
	 */
	checkECStr : function(str) {
		var pattern = /^[a-zA-Z\u4e00-\u9fa5\s]+$/i;
		if (pattern.test(str)) {
			return true;
		} else {
			return false;
		}
	},

	/**
	 * 只能输入数字
	 */
	checkNum : function(str) {
		var pattern = /^\d+$/i;
		if (pattern.test(str)) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 字符串为空
	 */
	checkNull : function(str) {
		str = store.trim(str);
		if (str.length == 0) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 验证金额(浮点型、整型)
	 */
	checkMoney : function(str) {
		var pattern = /^[1-9]\d*\.\d*$|^0\.\d*[1-9]\d*$|^0?\.0+|0]$|^\d+$/;
		if (pattern.test(str)) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 检查数字或字母
	 */
	checkNumOrChar : function(str) {
		var pattern = /^[0-9a-zA-Z]+$/i;
		if (pattern.test(str)) {
			return true;
		} else {
			return false;
		}
	},
	/**
	 * 比较2个时间天数间隔,格式:2010-06-20 12:12
	 */
	compareDate : function(date1, date2) {
		var time = new Date(date2.replace(/-/g, "\/")).getTime()
				- new Date(date1.replace(/-/g, "\/")).getTime();
		// 开始时间晚于结束时间
		if (time < 0)
			return time;
		// 计算天数
		return time / (86400 * 1000);
	},
	/**
	 * 去最接近的数字
	 */
	proximalNum : function(num, array) {
		var dis = Math.abs(array[0] - num);
		var result = num;
		for ( var i = 0; i < array.length; i++) {
			var temp = Math.abs(array[i] - num);
			if (dis > temp) {
				dis = temp;
				result = array[i];
			}
		}
		if (result < array[0]) {
			result = array[0];
		} else if (result > array[array.length - 1]) {
			result = array[array.length - 1];
		}
		return result;
	},
	/**
	 * 判断是否是允许上传的图片类型
	 */
	uploadImageEnabled : function(imageName) {
		var point = imageName.lastIndexOf(".");
		var type = imageName.substr(point);
		if (type != ".jpg" && type != ".JPG" && type != ".gif"
				&& type != ".GIF" && type != ".png" && type != ".PNG"
				&& type != ".bmp" && type != ".BMP" && type != ".jpeg"
				&& type != ".JPEG") {
			return false;
		}
		return true;
	},
	/**
	 * 获取文件大小
	 */
	getFileSize : function(obj) {
		var filesize = 0;
		if (Sys.firefox) {
			filesize = obj.files[0].fileSize;
		} else if (Sys.ie) {
			var filePath = obj.value;
			var image = new Image();
			image.dynsrc = filePath;
			filesize = image.fileSize;
		}
		return filesize;
	},

	/**
	 * HTMLEncode方法编码
	 */
	HTMLEnCode : function(str) {
		var s = "";
		if (str.length == 0)
			return "";
		s = str.replace(/&/g, "&amp;");
		s = s.replace(/</g, "&lt;");
		s = s.replace(/>/g, "&gt;");
		s = s.replace(/  /g, "&nbsp;");
		s = s.replace(/\'/g, "&apos;");
		s = s.replace(/\"/g, "&quot;");
		// s = s.replace(/\n/g, "<br>");
		return s;
	},

	/**
	 * HTMLDeCode方法解码
	 */
	HTMLDeCode : function(str) {
		var s = "";
		if (str.length == 0)
			return "";
		s = str.replace(/&amp;/g, "&");
		s = s.replace(/&lt;/g, "<");
		s = s.replace(/&gt;/g, ">");
		s = s.replace(/&nbsp;/g, "  ");
		s = s.replace(/&apos;/g, "\'");
		s = s.replace(/&quot;/g, "\"");
		// s = s.replace(/<br>/g, "\n");
		return s;
	},

	/**
	 * 按长度截取字符串
	 */
	subStr : function(str, Len) {
		return (str.length > Len) ? (str.substring(0, Len - 3) + "...") : str;
	},
	/**
	 * 计算字符串长度,中文按2计算
	 */
	getBytesLength : function(str) {
		return str.replace(/[^\x00-\xff]/g, 'xx').length;
	},
	/**
	 * 获得字符串实际长度，中文2，英文1 要获得长度的字符串
	 */
	getLength : function(str) {
		var realLength = 0, len = str.length, charCode = -1;
		for ( var i = 0; i < len; i++) {
			charCode = str.charCodeAt(i);
			if (charCode >= 0 && charCode <= 128)
				realLength += 1;
			else
				realLength += 2;
		}
		return realLength;
	},
	/**
	 * 取文件扩展名,不包括"."
	 */
	getExtension : function(filename) {
		var defExt = "";
		if ((filename != null) && (filename.length > 0)) {
			var i = filename.lastIndexOf('.');

			if ((i > -1) && (i < (filename.length - 1))) {
				return filename.substring(i + 1);
			}
		}
		return defExt;
	},

	/**
	 * 格式化容量
	 * 
	 * @size 单位Byte
	 */
	formatSpace : function(size) {
		var str = "";
		if (size > 0 && size < 1024) {
			str = size + " Byte";
		} else if (size > 1024 && size < Math.pow(1024, 2)) {
			str = (size / 1024).toFixed(2) + " KB";
		} else if (size > Math.pow(1024, 2) && size < Math.pow(1024, 3)) {
			str = (size / Math.pow(1024, 2)).toFixed(2) + " MB";
		} else if (size > Math.pow(1024, 3) && size < Math.pow(1024, 4)) {
			str = (size / Math.pow(1024, 3)).toFixed(2) + " GB";
		} else if (size > Math.pow(1024, 4) && size < Math.pow(1024, 5)) {
			str = (size / Math.pow(1024, 4)).toFixed(2) + " TB";
		}
		return str;
	},

	/**
	 * 通用Tab切换 tanhuihuang 20100719 |-tabId 标签ID 必须是ul tabContentId 切换内容ID
	 * childElement 切换内容列表元素 highlightClass 高亮class
	 */
	switchTab : function(tabId, tabContentId, childElement, highlightClass) {
		$('#' + tabId + ' li').click(
				function() {
					$(this).addClass(highlightClass);
					$(this).siblings().removeClass(highlightClass);
					$('#' + tabContentId + '>' + childElement).hide();
					$(
							$('#' + tabContentId + '>' + childElement).get(
									$(this).index())).show();

				});
	},
	/**
	 * 转义html特殊字符
	 */
	escapeHtmlSpecialChar : function(str) {
		return str.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g,
				'&gt;').replace(/"/g, '&quot;');
	},
	/**
	 * 求两个时间的天数差 日期格式为 YYYY-MM-dd
	 */
	daysBetween : function(DateOne, DateTwo) {
		var OneMonth = DateOne.substring(5, DateOne.lastIndexOf('-'));
		var OneDay = DateOne.substring(DateOne.length,
				DateOne.lastIndexOf('-') + 1);
		var OneYear = DateOne.substring(0, DateOne.indexOf('-'));

		var TwoMonth = DateTwo.substring(5, DateTwo.lastIndexOf('-'));
		var TwoDay = DateTwo.substring(DateTwo.length,
				DateTwo.lastIndexOf('-') + 1);
		var TwoYear = DateTwo.substring(0, DateTwo.indexOf('-'));

		var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date
				.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
		return Math.abs(cha);
	},
	compareDate : function(date1, date2) {
		var time = new Date(date2.replace(/-/g, "\/")).getTime()
				- new Date(date1.replace(/-/g, "\/")).getTime();
		// 开始时间晚于结束时间
		if (time < 0)
			return time;
		// 计算天数
		return time / (86400 * 1000);

	},
	/**
	 * 格式化日期
	 */
	dateFormat : function(date) {
		var dateStr = (date.year + 1900) + '/' + (date.month + 1) + '/'
				+ date.date;
		var timeStr = date.hours + ':'
				+ (date.minutes < 10 ? '0' + date.minutes : date.minutes);
		var today = new Date();
		var todayStr = today.getFullYear() + '/' + (today.getMonth() + 1) + '/'
				+ today.getDate();
		var d = new Date(dateStr);
		var todayDate = new Date(todayStr);
		var newdate;
		if (todayDate - d == 0) {
			newdate = '今天   ' + timeStr;
		} else if (todayDate - d == 86400000) {
			newdate = '昨天   ' + timeStr;
		} else {
			newdate = (date.month + 1) + '月' + date.date + '日' + '    '
					+ timeStr;
		}
		return newdate;
	},
	/**
	 * 验证 两个时间
	 */
	validTime :function(startTime,endTime,splitStr){
//		   var arr1 = startTime.split(splitStr);
//		   var arr2 = endTime.split(splitStr);
//		   var date1=new Date(parseInt(arr1[0]),parseInt(arr1[1])-1,parseInt(arr1[2]),0,0,0);
//		   var date2=new Date(parseInt(arr2[0]),parseInt(arr2[1])-1,parseInt(arr2[2]),0,0,0);
		var time = new Date(endTime.replace(/-/g, "\/")).getTime()
					- new Date(startTime.replace(/-/g, "\/")).getTime();
			// 开始时间晚于结束时间
			if (time < 0){
//		     if(date1.getTime()>date2.getTime()) {   
		    	 var msgBox = art.dialog({
		    		 	id:"validateTime",
			 			title : '温馨提示',
			 			content :'结束日期不能小于开始日期',
			 			okVal : '确定',
			 			icon: 'warning',
			 			lock: true,
						opacity: 0.10,
			 			ok : function() {
			 				msgBox.close();
			 			}
			 		});
	               return false;
	         }else{
	        	 return true;
	         }
	         return false;
		},
	/**
	 * ajax请求 Parm格式:data = {'page.currentPage':5};
	 */
	sendAjax : function(URL, Parm, SuccFun, ErrorFun, PostTYPe, dataType,cacheType, asyncType) {
		PostTYPe = (PostTYPe == null) ? "GET" : "POST";
		cacheType = (cacheType == null) ? false : true;
		dataType = (dataType == null) ? "json" : dataType;
		ErrorFun = (ErrorFun == null) ? sys.ajaxError : ErrorFun;
		asyncType = (asyncType == null) ? true : false;
		$.ajax({
			url : URL,
			data : Parm,
			cache : cacheType,
			type : PostTYPe,
			dataType : dataType,
			success : SuccFun,
			async : asyncType,
			error : ErrorFun,
			complete : function(XMLHttpRequest, textStatus){
				var s=XMLHttpRequest.status;
					if(s==202){
						sys.relogin();
					}
			}
		});
	},
	/**
	 * 错误
	 * 
	 * @param data
	 * @param textStatus
	 */
	ajaxError : function(data, textStatus) {
		var msg = '';
		if (textStatus == 'timeout') {
			msg = '数据请求超时，请稍候再试。(timeout)';
		} else if (textStatus == 'error') {
			msg = '服务器异常，请稍候再试。(error)';
		} else if (textStatus == 'parsererror') {
			msg = '响应数据格式错误。(parsererror)';
		} else {
			msg = '请求数据失败，请稍候再试。(' + textStatus + ')';
		}
		alert(msg);
//		$.msgBox.window({
//			title : "温馨提示",
//			html : '<div class="warningTip lh50">' + msg + '</div>',
//			height : 100,
//			showMask : true,
//			cls : "shortBox"
//		});
		/*
		 * $.msgBox.alert({ title : "温馨提示", msg : "请求数据失败，请稍候再试。(" + textStatus +
		 * ")", icon : "error", textYes : "关 闭", width : 350 });
		 */
	},
	loadError : function(info) {
		var msg = '<div class="warningTip lh50">' + info + '</div>';
		$.msgBox.window({
			title : "温馨提示",
			html : msg,
			height : 100,
			showMask : true,
			cls : "shortBox"
		});
		/*
		 * $.msgBox.alert({ title : "温馨提示", msg : info, icon : "error", textYes :
		 * "关 闭", width : 350 });
		 */
	},
	tip : function(info) {
		// var msg ='<div class="warningTip lh50">'+info+'</div>';
		// $.msgBox.window({
		// title:" ",
		// html : msg,
		// height:100,
		// showMask : true,
		// cls : "shortBox"
		// });
		/*$.msgBox.alert({
			title : '温馨提示',
			cls : 'shortBox popTips',
			msg : info,
			icon : 'warning',
			showMash : false
		});*/
		art.dialog({
			lock: true,
			opacity: 0.10,	// 透明度
		    icon: 'warning',
			//time: 1,
			title: '温馨提示',
			content: info
		});
	},

	/**
	 * 填充分页层, obj:JQUERY对象，分页层 callbackFun : 回调方法 page :
	 * JS对象,格式{'counts':20,'currentPage':0,'perPageSize':10}
	 */
	fillPaginations : function(obj, callbackFun, page) {
		obj.pagination(page.counts, {
			first_text : "首页",
			prev_text : "上一页",
			next_text : "下一页",
			last_text : "尾页",
			ellipse_text : "...",
			current_page : page.currentPage - 1,
			num_edge_entries : 1,
			num_display_entries : 7,
			prev_show_always : false,
			next_show_always : false,
			items_per_page : page.perPageSize,
			callback : callbackFun
		});
	},
	/**
	 * 只能输入数字 事件绑定到属性num="yes"的元素 numzero="yes" 能以0开头
	 */
	onlyInputNum : function() {
//		var regex = /\D|^0/g;
		var regex =/^[1-9]\d*$/;
		var zeroregex = /\D/g;
		$('input[num=yes]').each(function() {
			$(this).keyup(function() {// 输入
				var tmptxt = $(this).val();
				if(!regex.test(tmptxt)){
					tmptxt ='';
				}
				$(this).val(tmptxt);
			}).bind("paste", function() {// 粘贴
				var tmptxt = $(this).val();
				if(!regex.test(tmptxt)){
					tmptxt ='';
				}
				$(this).val(tmptxt);
			}).css("ime-mode", "disabled");// 禁用输入法
		});
		$('input[numzero=yes]').each(function() {
			$(this).keyup(function() {// 输入
				var tmptxt = $(this).val();
				tmptxt = tmptxt.replace(zeroregex, '');
				$(this).val(tmptxt);
			}).bind("paste", function() {// 粘贴
				var tmptxt = $(this).val();
				$(this).val(tmptxt.replace(zeroregex, ''));
			}).css("ime-mode", "disabled");// 禁用输入法
		});

	},
	/**
	 * 输入框显示默认值 元素加上默认属性 default="默认值"
	 * 
	 * @param id
	 */
	defaultValue : function() {
		// .c_999{color:#999!important;}
		var defaultCss = "c_999";
		// 如果文本框为只读，不做任务处理
		$("input[default]").each(function() {
			var obj = $(this);
			if (!obj.attr('readonly')) {
				if (!obj.val()) {
					obj.val(obj.attr('default'));
					obj.addClass(defaultCss);
				}
			}
			obj.focusin(function() {// 获得焦点清除默认提示
				if (!obj.attr('readonly')) {
					if (obj.val() == obj.attr('default')) {
						obj.removeClass(defaultCss);
						obj.val("");
					}
				}
			}).focusout(function() {// 失去焦点显示默认提示
				if (!obj.attr('readonly')) {
					if (!obj.val()) {
						obj.val(obj.attr('default'));
						obj.addClass(defaultCss);
					}
				}
			});
		});

		$("textarea[default]").each(function() {
			var obj = $(this);
			if (!obj.attr('readonly')) {
				if (!obj.val()) {
					obj.val(obj.attr('default'));
					obj.addClass(defaultCss);
				}
			}
			obj.focus(function() {// 获得焦点清除默认提示
				if (!obj.attr('readonly')) {
					if (obj.val() == obj.attr('default')) {
						obj.val("");
						obj.removeClass(defaultCss);
					}
				}
			}).blur(function() {// 失去焦点显示默认提示
				if (!obj.attr('readonly')) {
					if (!obj.val()) {
						obj.val(obj.attr('default'));
						obj.addClass(defaultCss);
					}
				}
			});
		});
	},
	/**
	 * 输入提示信息样式 例： 输入标签:<input id="example"/> 提示框:<label id="exampleTip"></label>
	 * 默认提示信息:<span id="exampleDefaultTip">请输入。。。</span> 只用于显示 参照添加角色
	 * addRole.jsp
	 */
	inputTipCss : function() {
		$("input,select,textarea").each(function() {
			var obj = $(this);
			var type = obj.attr("type"); 
			if(type != "checkbox" && type != 'radio' && type != 'file'){
				$("#" + obj.attr('id') + "Tip").html('').hide();
				obj.bind('focusin', function() {
					obj.removeClass('ipt-error').addClass('ipt-active');
					$("#" + obj.attr('id') + "Tip").html('').hide();
					$("#" + obj.attr('id') + "DefaultTip").show();
				}).bind('focusout', function() {
					obj.removeClass('ipt-active');
				});
			}
		});

	},
	/**
	 * @param elename
	 *            提示信息的元素
	 * @param tip
	 *            提示信息内容
	 * @param twidth
	 *            提示框宽度) 默认150
	 * @param tpadding
	 *            提示框距左边宽度 默认10
	 */
	showTip : function(elename, tip, twidth, tpadding) {
		if (!twidth) {
			twidth = 150;
		}
		if (!tpadding) {
			tpadding = 10;
		}

		$("#" + elename).addClass("ipt-error");
		$("#" + elename + "DefaultTip").hide();
		$("#" + elename + "Tip").addClass('tipbox').attr(
				"style",
				'width: ' + twidth + 'px; text-align: left;padding-left: '
						+ tpadding + 'px').html(tip).show();
	},
	/**
	 * 过滤所输入框前后空格
	 */
	filterRLspace : function() {
		var regex = /^\s*|\s*$/g;
		// 文本框
		$('input,textarea').each(function() {
			if ($(this).attr('type') == 'file') {
				return false;
			}
			$(this).blur(function() {// 失去焦点显示去除空格
				var tmptxt = $(this).val();
				$(this).val(tmptxt.replace(regex, ''));
			});
		});
	},
	/**
	 * 检查字符串是否被指定分隔符分隔
	 */
	checkSplitStr : function(str, split) {
		var reg = "(^[0-9a-zA-Z\u4E00-\u9FA5][0-9a-zA-Z\u4E00-\u9FA5" + split
				+ "]*[0-9a-zA-Z\u4E00-\u9FA5]$)|^[0-9a-zA-Z\u4E00-\u9FA5]$"
		var regObj = new RegExp(reg, "g");
		return regObj.test(str);
	},
	/**
	 * 默认tip友好提示,可自己扩充
	 * 
	 * @param id
	 *            名称
	 * @param info
	 *            提示信息,默认取标签title的值
	 */
	defaultTip : function(id, info) {
		var obj = $('#' + id);
		info = (info == null) ? obj.attr("title") : info;
		$('#' + id).poshytip({
			className : 'tip-store', // 提示信息样式
			content : info, // 提示信息内容，默认取得标签title
			showOn : 'focus', // 触发显示提示信息，可选值:'hover', 'focus',
								// 'none'，注：none主要是用于手动控制显示与show结合使用
			alignTo : 'target', // 显示位置 ，可选值：'cursor', 'target'
			alignX : 'right', // X轴位置
			alignY : 'center', // Y轴位置
			offsetX : 5, // X轴偏移
			allowTipHover : false, // 允许悬停显示
			fade : true, // 逐渐消失
			slide : true
		// 慢慢滑动
		});
	},
	/**
	 * 默认tip警告提示
	 * 
	 * @param id
	 *            名称
	 * @param info
	 *            提示信息
	 */
	warningTip : function(id, info) {
		$('#' + id).poshytip({
			className : 'tip-store-error', // 提示信息样式
			content : info, // 提示信息内容，默认取得标签title
			showOn : 'focus', // 触发显示提示信息，可选值:'hover', 'focus',
								// 'none'，注：none主要是用于手动控制显示与show结合使用
			alignTo : 'target', // 显示位置 ，可选值：'cursor', 'target'
			alignX : 'right', // X轴位置
			alignY : 'center', // Y轴位置
			offsetX : 5, // X轴偏移
			allowTipHover : false, // 允许悬停显示
			fade : true, // 逐渐消失
			slide : true
		// 慢慢滑动
		});
	},
	/***************************************************************************
	 * 格式化显示金额数字 id 临时存放的控件ID pfix 前缀 不需要前缀 ' ', len 保留小数几位 values 需要格式的数字
	 */
	formatMoney : function(id, pfix, len, values) {
		values = Math.round(parseFloat(values)) / 100;
		var xsd = values.toString().split(".");
		if (xsd.length == 1) {
			values = values.toString() + ".00";
			return values;
		}
		if (xsd.length > 1) {
			if (xsd[1].length < 2) {
				values = values.toString() + "0";
			}
			return values;
		}
		// if (values.toString().indexOf(".") < 0) {
		// values = values.toString() + ".00";
		// }
		// return values;
		// }
		// $('#'+id).val(values);
		// var money = $('#'+id).priceFormat({prefix: pfix,centsLimit:2}).val();
		// return money;
	},
	// 截取字符串（中文占两个字符）
	cutString : function(str,len) {
		var str_length = 0;
		   var str_len = 0;
		      str_cut = new String();
		      str_len = str.length;
		      for(var i = 0;i<str_len;i++)
		     {
		        a = str.charAt(i);
		        str_length++;
		        if(escape(a).length > 4)
		        {
		         //中文字符的长度经编码之后大于4
		         str_length++;
		         }
		         str_cut = str_cut.concat(a);
		         if(str_length>=len)
		         {
		         str_cut = str_cut.concat("...");
		         return str_cut;
		         }
		    }
		    //如果给定字符串小于指定长度，则返回源字符串；
		    if(str_length<len){
		     return  str;
		    }
	},
	/**
     * 日期转换
     * yyyy splitStr mm splitStr dd hh:mm:ss
     * @param {} date  splitStr 分隔字符
     * @return {21:23 2012-01-02}
     */
    dateFormatSecNew : function(date,splitStr){
        if (date) {
            if(!splitStr){
                splitStr = "/";
            }
            var month = parseInt(date.month)<9?"0"+(date.month+1):date.month+1;
            var day = parseInt(date.date)<10?"0"+date.date:date.date;
            var year = 1900 + date.year;
            var hours = parseInt(date.hours)<10?"0"+date.hours:date.hours;
            var minutes = parseInt(date.minutes)<10?"0"+date.minutes:date.minutes;
            var seconds = parseInt(date.seconds)<10?"0"+date.seconds:date.seconds;
            return year + splitStr + month + splitStr + day+' '+hours + ":" + minutes ;
        } else {
            return "未知";
        }
    },
    /**
     * 日期转换
     * yyyy splitStr mm splitStr dd hh:mm:ss
     * @param {} date  splitStr 分隔字符
     * @return {21:23 2012-01-02}
     */
    dateFormatDate : function(date,splitStr){
        if (date) {
            if(!splitStr){
                splitStr = "/";
            }
            var month = parseInt(date.month)<9?"0"+(date.month+1):date.month+1;
            var day = parseInt(date.date)<10?"0"+date.date:date.date;
            var year = 1900 + date.year;
            return year + splitStr + month + splitStr + day ;
        } else {
            return "未知";
        }
    },
	
	//去左右'-'; 
	LRgang: function(s){ 
        function ltrim(s){ 
            return s.replace( /^\-*/, ""); 
            } 
            //去右空格; 
            function rtrim(s){ 
                return s.replace( /\-*$/, ""); 
            } 
            return rtrim(ltrim(s)); 
    },
    /**
	 * 将Java Date转为js Date  日期类型转换
	 * @param java.util.Date data
	 * return yyyy-MM-dd hh:mm:ss
	 */
	formatDate : function(data){
		var date = new Date();
	    date.setTime(data);
	    var now = date.getFullYear()+"-"; 
		now = now + (date.getMonth()+1)+"-";
		now = now + date.getDate()+" ";
		now = now + date.getHours()+":";
		now = now + date.getMinutes()+":";
		now = now + date.getSeconds()+"";
	    return now;
	}
};
var brower = {
	getBrower : function() {
		var userAgent = window.navigator.userAgent;
		if (userAgent.indexOf("MSIE 6.0") > 1) {
			return "ie6";
		} else if (userAgent.indexOf("MSIE 7.0") > 1) {
			return "ie7";
		} else if (userAgent.indexOf("MSIE 8.0") > 1) {
			return "ie8";
		} else if (userAgent.indexOf("MSIE 9.0") > 1) {
			return "ie9";
		} else if (userAgent.indexOf("Chrome") > 1) {
			return "chrome";
		} else if (userAgent.indexOf("Firefox") > 1) {
			return "firefox";
		} else if (userAgent.indexOf("Opera") > 1) {
			return "opera";
		} else {
			return "";
		}
	}
};

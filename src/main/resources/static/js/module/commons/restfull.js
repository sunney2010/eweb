/**
 * api for RESTful operation
 */
/**
 * use case: <a href="/user/12" onclick="restfull.restDelete(this,'confirm delete?');return false;">delete</a>
 */
var restfull = {
		//delete 
		restDelete:function(anchor,confirmMsg){
			confirmMsg = (confirmMsg == null) ?restfullMsg.DELETE_SURE : confirmMsg;
	        art.dialog({
	            title: '温馨提示！',
	            icon: 'question',
	            content: confirmMsg,
	            lock: true,
	            opacity: 0.10,
	            okVal:'确定',
	            cancel:'取消',
	            ok: function () {
	            	var f = document.createElement("form");
					f.style.display = "none";
					anchor.parentNode.appendChild(f);
					f.method = "POST";
					f.action = anchor.href;
					var m = document.createElement("input");
					m.setAttribute("type", "hidden");
					m.setAttribute("name", "_method");
					m.setAttribute("value", "delete");
					f.appendChild(m);
					f.submit();
				}
			});
		},
		//asyn delete
		asynRestDelete:function(url,msg,SuccFun){
			msg=(msg == null)? restfullMsg.DELETE_SURE : msg;
			SuccFun=(SuccFun == null)?restfull.callback_asynRestDelete:SuccFun;
			layer.confirm(msg, {
        	    btn: ['确定','取消'], //按钮
        	    shade: false //不显示遮罩
        	}, function(){
        		var  data= {_method:'DELETE'};
            	sys.sendAjax(url,data,SuccFun,null,'POST','json');
            }, function(){
        	   return;
        	});
		},
		callback_asynRestDelete:function(data){
			var result=data.result.success;
			var url=data.result.info;
			var msg=restfullMsg.DELETE_FAIL;
			if(result==true){
				msg=restfullMsg.DELETE_SUCCEED;
				layer.msg(msg);
				console.log("url:"+url);
				window.location.href = url;
			}else{
				layer.msg(msg);
			}
		},
		//batchDelete
		restBatchDelete:function(action,checkboxName,form) {
			if (!hasOneChecked(checkboxName)) {
				alert("请选择你要删除的对象!");
				return;
			}
			if (confirm("你确认要删除?")) {
				if(!form){
					form = document.forms['queryForm'];
				}
				form.action = action;
				form.method = 'POST';
				var m = document.createElement("input");
				m.setAttribute("type", "hidden");
				m.setAttribute("name", "_method");
				m.setAttribute("value", "delete");
				form.appendChild(m);
				
				form.submit();
			}
		}
};
var restfullMsg={
		DELETE_SURE:"您确定要删除该信息吗？",
		DELETE_SUCCEED:"删除信息成功！",
	    DELETE_FAIL:"删除信息失败！"
};
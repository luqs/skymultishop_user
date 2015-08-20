var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = com.sirius.skymall.user.contextPath + '/base/feedback!update.sy';
			} else {
				url = com.sirius.skymall.user.contextPath + '/base/feedback!saveFeedback.sy';
			}
			var checkRes=true;
			var telNum=$(':input[name="data.tel"]').val();//手机号码
			var content=$(':input[name="data.msgContent"]').val();//留言内容 
			var email=$(':input[name="data.email"]').val();//邮箱
			var qqNum=$(':input[name="data.qq"]').val();//qq号
			var nichen=$(':input[name="data.nichen"]').val();//昵称
			if(telNum.length>0){
				var reShouji = /^1\d{10}$/;
				if(!reShouji.test(telNum)){
					var reGuhua=/^0\d{2,3}-?\d{7,8}$/;
					if(!reGuhua.test(telNum)){
						$pjq.messager.alert(const_message_role_warning, const_message_feedback_alert_phone, 'error');
						checkRes=false;						
					}
				}
			}
			if(content.length<=0){
				$pjq.messager.alert(const_message_role_warning, const_message_feedback_alert_content, 'error');
				checkRes=false;	
			}
			if(email.length>0){
				var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
				if(!re.test(email)){
					$pjq.messager.alert(const_message_role_warning, const_message_feedback_alert_email, 'error');
					checkRes=false;	
				}

			}
			if(nichen.length>0){
				//昵称要以字母开头长度4到16位
				var re = /^[a-zA-z]\w{3,15}$/;
				if(!re.test(nichen)){
					$pjq.messager.alert(const_message_role_warning, const_message_feedback_alert_nick, 'error');
					checkRes=false;	
				}
			}
			if(qqNum.length>0){
				var re = /^[1-9]\d{4,8}$/;
				if(!re.test(qqNum)){
					$pjq.messager.alert(const_message_role_warning, const_message_feedback_alert_qq, 'error');
					checkRes=false;	
				}
			}
			

			if(checkRes){
				$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
					if (result.success) {
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert(const_message_role_warning, result.msg, 'error');
					}
				}, 'json');
			}
		}
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : const_message_role_loading
			});
			$.post(com.sirius.skymall.user.contextPath + '/base/feedback!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.name' : result.name,
						'data.nichen' : result.nichen,
						'data.tel' : result.tel,
						'data.email' : result.email,
						'data.qq' : result.qq,
						'data.msgTitle' : result.msgTitle,
						'data.msgContent' : result.msgContent
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
$(function() {
		var regFun = function() {
			var loginname = $.trim($("#loginname").val());
			if(loginname!=null && loginname.length>=4 && loginname.length<=20){
				$(".tipinfo").hide();
				if ($('form').form('validate')) {
					$('#regBtn').linkbutton('disable');
					$.post(com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_reg.sy', $('form').serialize(), function(result) {
						if (result.success) {
							location.replace(com.sirius.skymall.user.contextPath + '/index.jsp');
						} else {
							$.messager.alert(const_message_login_info, result.msg, 'error', function() {
								$('form :input:eq(1)').focus();
							});
							$('#regBtn').linkbutton('enable');
						}
					}, 'json');
				}
			}else{
				//"The length of loginname must between 4 and 20!"
				$(".tipinfo").html("The length of loginname must between 4 and 20!");
				$(".tipinfo").show();
				return;
			}
			
		};

		$('#regDialog').show().dialog({
			modal : true,
			closable : false,
			iconCls : 'ext-icon-user_add',
			buttons : [ {
				text : const_message_login_title,
				handler : function() {
					location.replace(com.sirius.skymall.user.contextPath + '/login.jsp');
				}
			}, {
				id : 'regBtn',
				text : const_message_reg_title,
				handler : function() {
					regFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						regFun();
					}
				});
			}
		});
	});
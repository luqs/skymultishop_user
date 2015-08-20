$(function() {

		var loginFun = function() {
			var loginTabs = $('#loginTabs').tabs('getSelected');//当前选中的tab
			var $form = loginTabs.find('form');//选中的tab里面的form
			if ($form.length == 1 && $form.form('validate')) {
				$('#loginBtn').linkbutton('disable');
				$.post(com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy', $form.serialize(), function(result) {
					if (result.success) {
						location.replace(com.sirius.skymall.user.contextPath + '/index.jsp');
					} else {
						$.messager.alert(const_message_login_info, result.msg, 'error', function() {
							$('#loginBtn').linkbutton('enable');
						});
						$("#valdatecode").attr("src","home!doNotNeedSessionAndSecurity_validateCode.sy?time="+ new Date())
					}
				}, 'json');
			}
		};

		$('#loginDialog').show().dialog({
			modal : false,
			closable : false,
			iconCls : 'ext-icon-lock_open',
			buttons : [ {
				text : const_message_reg_title,
				handler : function() {
					location.replace(com.sirius.skymall.user.contextPath + '/reg.jsp');
				}
			}, {
				id : 'loginBtn',
				text : const_message_login_title,
				handler : function() {
					loginFun();
				}
			} ],
			onOpen : function() {
				$('form :input:first').focus();
				$('form :input').keyup(function(event) {
					if (event.keyCode == 13) {
						loginFun();
					}
				});
			}
		});

		$('#userLoginCombobox').combobox({
			url : com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_loginNameComboBox.sy',
			valueField : 'loginname',
			textField : 'loginname',
			required : true,
			panelHeight : 'auto',
			mode : 'remote',
			delay : 500
		});

		$('#userLoginCombogrid').combogrid({
			url : com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_loginNameComboGrid.sy',
			panelWidth : 500,
			panelHeight : 200,
			idField : 'loginname',
			textField : 'loginname',
			pagination : true,
			fitColumns : true,
			required : true,
			rownumbers : true,
			mode : 'remote',
			delay : 500,
			sortName : 'loginname',
			sortOrder : 'asc',
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'loginname',
				title : const_message_login_field_loginname,
				width : 100,
				sortable : true
			}, {
				field : 'name',
				title : const_message_login_field_name,
				width : 100,
				sortable : true
			}, {
				field : 'createdatetime',
				title : const_message_login_field_createdatetime,
				width : 150,
				sortable : true
			}, {
				field : 'modifydatetime',
				title : const_message_login_field_modifydatetime,
				width : 150,
				sortable : true
			} ] ]
		});

	});
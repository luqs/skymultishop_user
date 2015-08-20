var grid;
	var editFunBak = function(id) {
		parent.$.messager.confirm(const_message_role_warning, const_message_publicno_confirmappoint, function(r) {
			if (r) {
				$.post(com.sirius.skymall.user.contextPath + '/base/appointpublicno!update.sy', {
					id : id
				}, function(str) {
					alert(str.msg);
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	var editFun = function(id){
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : const_message_publicno_editpublicinfo,
			url : com.sirius.skymall.user.contextPath + '/admin/base/publicNoForm.jsp?id=' + id,
			buttons : [ {
				text : const_message_tool_edit,
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	//指定成普通用户
	var updateFun = function(id){
		parent.$.messager.confirm(const_message_role_warning, const_message_publicno_revert, function(r) {
			if (r) {
				$.post(com.sirius.skymall.user.contextPath + '/base/appointpublicno!update.sy', {
					id : id
				}, function(str) {
					alert(str.msg);
					grid.datagrid('reload');
				}, 'json');
			}
		});
	}
	//指定成服务号
	var modifyFun = function(id){
		parent.$.messager.confirm(const_message_role_warning, const_message_publicno_confirmappoint, function(r) {
			if (r) {
				$.post(com.sirius.skymall.user.contextPath + '/base/appointpublicno!update1.sy', {
					id : id
				}, function(str) {
					alert(str.msg);
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : com.sirius.skymall.user.contextPath + '/base/appointpublicno!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
// 			sortName : 'seq',
// 			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title :  const_message_role_form_id,
				field : 'id'
			} ] ],
			columns : [ [{
				width : '100',
				title : const_message_login_field_loginname,
				field : 'username'
			},{
				width : '100',
				title : const_message_login_field_name,
				field : 'name'
			},{
				width : '100',
				title : const_message_publicno_phone,
				field : 'phone'
			},{
				width : '100',
				title : const_message_publicno_email,
				field : 'email'
			},{
				width : '100',
				title : const_message_publicno_boatcard,
				field : 'boatcard'
			},{
				width : '150',
				title : const_message_publicno_signature,
				field : 'signature'
			},  
			{
				width : '100',
				title : const_message_publicno_usertype,
				field : 'usertype',
				sortable : true,
				formatter:function(val,row){  
					if(row.usertype==1){
						return const_message_publicno_commonuser;
					}else if(row.usertype==2){
						return const_message_publicno_businessuser;
					}else if(row.usertype==3){
						return const_message_publicno_shopwaiter;
					}else if(row.usertype==4){
						return const_message_publicno_deliver;
					}else if(row.usertype==5){
						return const_message_publicno_serviceaccount;
					}else if(row.usertype==6){
						return const_message_publicno_publicaccount;
					}
//					switch (value) {
//					case '1':
//						return "普通用户";
//					case '2':
//						return "商家";
//					case '3':
//						return "店小二";
//					case '4':
//						return "配送员";
//					case '5':
//						return "服务账号";
//					case '6':
//						return "公众账号";
//					}
				}
			},
			{
				title : const_message_role_operation,
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
					$("#appointpublicnoAction >img").each(function(){
						  str += com.sirius.skymall.user.formatString($(this)[0].outerHTML, row.id);
					});
					return str;
				}
			} ] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : const_message_role_loading
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', com.sirius.skymall.user.pixel_0);
				parent.$.messager.progress('close');
			}
		});
	});
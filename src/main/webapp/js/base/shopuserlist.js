var grid;
	
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : com.sirius.skymall.user.contextPath + '/base/syshopuser!grid.sy',
			queryParams: {usertype:"1"},
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
				field : 'loginname'
			}, {
				width : '100',
				title : const_message_userinfo_age,
				field : 'age'
			}, {
				width : '100',
				title : const_message_userinfo_sex,
				field : 'sex'
			},{
				width : '100',
				title : const_message_user_createdate,
				field : 'createDate'
			}, {
				title : const_message_role_operation,
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
					$("#blacklistAction >img").each(function(){
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
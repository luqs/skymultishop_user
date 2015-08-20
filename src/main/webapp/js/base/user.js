var grid;
var addFun = function() {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_user_adduserinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyuserForm.jsp',
		buttons : [ {
			text : const_message_tool_add,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};
var showFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_user_viewuserinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyuserForm.jsp?id=' + id
	});
};
var editFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_user_edituserinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyuserForm.jsp?id=' + id,
		buttons : [ {
			text : const_message_tool_edit,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};
var removeFun = function(id) {
	parent.$.messager.confirm(const_message_common_info, const_message_role_confirmtodelete, function(r) {
		if (r) {
			$.post(com.sirius.skymall.user.contextPath + '/base/syuser!delete.sy', {
				id : id
			}, function() {
				grid.datagrid('reload');
			}, 'json');
		}
	});
};
var grantRoleFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_role_rolegrant,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyuserRoleGrant.jsp?id=' + id,
		buttons : [ {
			text : const_message_tool_edit,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};
$(function() {
	grid = $('#grid').datagrid({
		title : '',
		url : com.sirius.skymall.user.contextPath + '/base/syuser!grid.sy',
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
		sortName : 'createdatetime',
		sortOrder : 'desc',
		pageSize : 50,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
		frozenColumns : [ [ {
			width : '100',
			title : const_message_login_field_loginname,
			field : 'loginname',
			sortable : true
		}, {
			width : '80',
			title : const_message_login_field_name,
			field : 'name',
			sortable : true
		} ] ],
		columns : [ [ {
			width : '150',
			title : const_message_login_field_createdatetime,
			field : 'createdatetime',
			sortable : true
		}, {
			width : '150',
			title : const_message_login_field_modifydatetime,
			field : 'updatedatetime',
			sortable : true
		}, {
			width : '50',
			title : const_message_userinfo_sex,
			field : 'sex',
			sortable : true,
			formatter : function(value, row, index) {
				//return "男";
				switch (value) {
				case 0:
					return const_message_userinfo_female;
				case 1:
					return const_message_userinfo_male;
				}
			}
		}, {
			width : '50',
			title : const_message_userinfo_age,
			field : 'age',
			hidden : true
		}, {
			width : '250',
			title : const_message_user_photo,
			field : 'photo',
			formatter : function(value, row) {
				if(value){
					return com.sirius.skymall.user.formatString('<span title="{0}">{1}</span>', value, value);
				}
			}
		}, {
			title : const_message_role_operation,
			field : 'action',
			width : '90',
			formatter : function(value, row) {
				var str = '';
				$("#userAction >img").each(function(){
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
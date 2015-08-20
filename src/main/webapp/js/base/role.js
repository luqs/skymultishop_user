var grid;
var addFun = function() {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_role_addroleinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyroleForm.jsp',
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
		title : const_message_role_viewroleinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyroleForm.jsp?id=' + id
	});
};
var editFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_role_editroleinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyroleForm.jsp?id=' + id,
		buttons : [ {
			text : const_message_tool_edit,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};
var removeFun = function(id) {
	parent.$.messager.confirm(const_message_role_warning, const_message_role_confirmtodelete, function(r) {
		if (r) {
			$.post(com.sirius.skymall.user.contextPath + '/base/syrole!delete.sy', {
				id : id
			}, function() {
				grid.datagrid('reload');
			}, 'json');
		}
	});
};
var grantFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_role_rolegrant,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyroleGrant.jsp?id=' + id,
		buttons : [ {
			text : const_message_tool_grant,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
			}
		} ]
	});
};
$(function() {
	grid = $('#grid').datagrid({
		title : '',
		url : com.sirius.skymall.user.contextPath + '/base/syrole!grid.sy',
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
		sortName : 'seq',
		sortOrder : 'asc',
		frozenColumns : [ [ {
			width : '100',
			title : const_message_role_form_rolename,
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
			width : '300',
			title : const_message_role_form_description,
			field : 'description'
		}, {
			width : '60',
			title : const_message_role_form_order,
			field : 'seq',
			hidden : true,
			sortable : true
		}, {
			title : const_message_role_operation,
			field : 'action',
			width : '80',
			formatter : function(value, row) {
				var str = '';
				$("#roleAction >img").each(function(){
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
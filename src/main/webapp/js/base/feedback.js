var grid;
	var addFun = function() {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : const_message_feedback_add_feedback,
			url : com.sirius.skymall.user.contextPath + '/admin/base/feedbackForm.jsp',
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
			title : const_message_feedback_view_feedback,
			url : com.sirius.skymall.user.contextPath + '/admin/base/feedbackForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : const_message_feedback_edit_feedback,
			url : com.sirius.skymall.user.contextPath + '/admin/base/feedbackForm.jsp?id=' + id,
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
				$.post(com.sirius.skymall.user.contextPath + '/base/feedback!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : com.sirius.skymall.user.contextPath + '/base/feedback!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
// 			sortName : 'createdatetime',
// 			sortOrder : 'desc',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			frozenColumns : [ [ {
				width : '100',
				title : const_message_login_field_name,
				field : 'name'
			} ] ],
			columns : [ [ {
				width : '100',
				title : const_message_feedback_field_nickname,
				field : 'nichen'
			}, {
				width : '100',
				title : const_message_feedback_field_phone,
				field : 'tel'
			}, {
				width : '100',
				title : const_message_feedback_field_email,
				field : 'email'
			}, {
				width : '100',
				title : const_message_feedback_field_qq,
				field : 'qq'
			},{
				width : '100',
				title : const_message_feedback_field_title,
				field : 'msgTitle'
			},{
				width : '100',
				title : const_message_feedback_field_content,
				field : 'msgContent'
			}
			, {
				title : const_message_role_operation,
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					$("#fbackAction >img").each(function(){
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
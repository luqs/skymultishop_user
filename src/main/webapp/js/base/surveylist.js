var grid;
var addFun = function() {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_user_adduserinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SurveyForm.jsp',
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
		url : com.sirius.skymall.user.contextPath + '/admin/base/SurveyForm.jsp?id=' + id
	});
};
var editFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_user_edituserinfo,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SurveyForm.jsp?id=' + id,
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
			$.post(com.sirius.skymall.user.contextPath + '/base/survey!delete.sy', {
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
			url : com.sirius.skymall.user.contextPath + '/base/survey!grid.sy',
			queryParams: {},
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			frozenColumns : [ [ {
				width : '100',
				title :  const_message_survey_form_id,
				field : 'id'
			} ] ],
			columns : [ [{
				width : '100',
				title : const_message_survey_form_title,
				field : 'title'
			}, {
				width : '100',
				title : const_message_survey_form_description,
				field : 'description'
			}, {
				width : '100',
				title : const_message_survey_form_createdate,
				field : 'createDate'
			}, {
				title : const_message_role_operation,
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
					$("#surveyAction >img").each(function(){
						  str += com.sirius.skymall.user.formatString($(this)[0].outerHTML, row.id);
					});
					return str;
				}
			}] ],
			toolbar : '#toolbar',
			onBeforeLoad : function(param) {
				parent.$.messager.progress({
					text : const_message_role_loading
				});
			},
			onLoadSuccess : function(data) {
				$('.iconImg').attr('src', com.sirius.skymall.user.pixel_0);
				parent.$.messager.progress('close');
			},
			onDblClickRow: function(rowNumber, data){
				window.location.href= com.sirius.skymall.user.contextPath + '/base/question!home.sy?surveyId='+data.id;
    		}
		});
	});
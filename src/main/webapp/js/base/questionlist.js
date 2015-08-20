var grid;
var addSingleFun = function(sid) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_survey_addquestion,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SingleChoiceForm.jsp?sid='+sid,
		buttons : [ {
			text : const_message_tool_add,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,window);
			}
		} ]
	});
};
var addMultiFun = function(sid) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_survey_addquestion,
		url : com.sirius.skymall.user.contextPath + '/admin/base/MultiChoiceForm.jsp?sid='+sid,
		buttons : [ {
			text : const_message_tool_add,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,window);
			}
		} ]
	});
};
var addAskFun = function(sid) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_survey_addquestion,
		url : com.sirius.skymall.user.contextPath + '/admin/base/SurveyAskForm.jsp?sid='+sid,
		buttons : [ {
			text : const_message_tool_add,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,window);
			}
		} ]
	});
};

var addOneFun = function(sid) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : const_message_survey_addquestion,
		url : com.sirius.skymall.user.contextPath + '/admin/base/oneChoiceForm.jsp?sid='+sid,
		buttons : [ {
			text : const_message_tool_add,
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$,window);
			}
		} ]
	});
};

var removeFun = function(id) {
	parent.$.messager.confirm(const_message_common_info, const_message_role_confirmtodelete, function(r) {
		if (r) {
			$.post(com.sirius.skymall.user.contextPath + '/base/question!delete.sy', {
				id : id
			}, function() {
				//grid.datagrid('reload');
				window.location.reload(true);
			}, 'json');
		}
	});
};
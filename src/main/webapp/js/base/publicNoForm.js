var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = com.sirius.skymall.user.contextPath + '/base/appointpublicno!editUser.sy';
		} else {
			url = com.sirius.skymall.user.contextPath + '/base/appointpublicno!save.sy';
		}
		var name=$(':input[name="data.name"]').val();//名称
		//var signature=$(':input[name="data.signature"]').val();//签名
		$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
			if (result.success) {
				alert(result.msg);
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert(const_message_role_warning, result.msg, 'error');
			}
		}, 'json');
	}
};

$(function() {
	if ($(':input[name="data.id"]').val().length > 0) {
		parent.$.messager.progress({
			text : const_message_role_loading
		});
		$.post(com.sirius.skymall.user.contextPath + '/base/appointpublicno!getById.sy', {///base/appointpublicno!update.sy
			id : $(':input[name="data.id"]').val()
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'data.name' : result.name,
					'data.loginname' : result.loginname,
					'data.age' : result.age,
					'data.sex' : result.sex,
					'data.createDate' : result.createDate,
					'data.userId' : result.userId,
					'data.userIntablename' : result.userIntablename,
					'data.signature' : result.signature
				});
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
});
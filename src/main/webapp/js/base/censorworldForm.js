var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = com.sirius.skymall.user.contextPath + '/base/sycensorwords!update.sy';
		} else {
			url = com.sirius.skymall.user.contextPath + '/base/sycensorwords!save.sy';
		}
		$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
			if (result.success) {
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert(const_message_common_info, result.msg, 'error');
			}
		}, 'json');
	}
};
$(function() {
	if ($(':input[name="data.id"]').val().length > 0) {
		parent.$.messager.progress({
			text : const_message_role_loading
		});
		$.post(com.sirius.skymall.user.contextPath + '/base/sycensorwords!getById.sy', {
			id : $(':input[name="data.id"]').val()
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'data.word' : result.word,
					'data.content' : result.content
				});
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
});
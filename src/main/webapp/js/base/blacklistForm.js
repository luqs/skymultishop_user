var submitForm = function($dialog, $grid, $pjq) {alert("XX");
	if ($('form').form('validate')) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = com.sirius.skymall.user.contextPath + '/base/syblacklist!update.sy';
		} else {
			url = com.sirius.skymall.user.contextPath + '/base/syblacklist!save.sy';
		}
		$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
			if (result.success) {
				$grid.datagrid('load');
				$dialog.dialog('destroy');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	}
};
$(function() {
	if ($(':input[name="data.id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(com.sirius.skymall.user.contextPath + '/base/syblacklist!getById.sy', {
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
					'data.userIntablename' : result.userIntablename
				});
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
});
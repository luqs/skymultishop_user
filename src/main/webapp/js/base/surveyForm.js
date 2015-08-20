var uploader;//上传对象
var submitNow = function($dialog, $grid, $pjq) {
	var url;
	if ($(':input[name="data.id"]').val().length > 0) {
		url = com.sirius.skymall.user.contextPath + '/base/survey!update.sy';
	} else {
		url = com.sirius.skymall.user.contextPath + '/base/survey!save.sy';
	}
	$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
		parent.com.sirius.skymall.user.progressBar('close');//关闭上传进度条

		if (result.success) {
			$pjq.messager.alert('提示', result.msg, 'info');
			$grid.datagrid('load');
			$dialog.dialog('destroy');
		} else {
			$pjq.messager.alert('提示', result.msg, 'error');
		}
	}, 'json');
};
var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {
		submitNow($dialog, $grid, $pjq);
	}
};
$(function() {

	if ($(':input[name="data.id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(com.sirius.skymall.user.contextPath + '/base/survey!getById.sy', {
			id : $(':input[name="data.id"]').val()
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'data.title' : result.title,
					'data.description' : result.description
					
				});
				
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
});
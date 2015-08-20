var uploader;//上传对象
var submitNow = function($dialog, $grid, $pjq,$window) {
	var url;
	if ($(':input[name="data.id"]').val().length > 0) {
		url = com.sirius.skymall.user.contextPath + '/base/question!update.sy?type=1&surveyId='+const_sid;
	} else {
		url = com.sirius.skymall.user.contextPath + '/base/question!save.sy?type=1&surveyId='+const_sid;
	}
	$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
		parent.com.sirius.skymall.user.progressBar('close');//关闭上传进度条

		if (result.success) {
			$pjq.messager.alert('提示', result.msg, 'info');
			//$grid.datagrid('load');
			$dialog.dialog('destroy');
			$window.location.reload(true);
			
		} else {
			$pjq.messager.alert('提示', result.msg, 'error');
		}
	}, 'json');
};
var submitForm = function($dialog, $grid, $pjq,$window) {
	if ($('form').form('validate')) {
		submitNow($dialog, $grid, $pjq,$window);
	}
};
var appendOpts = function(){
	$(".unstyled").append('<li style=""><input  type="radio" name="radio"><input type="text" name="answers" style="width:200px;"></input></li>');
};
var appendQuestionItems = function(){
	$(".question-items").append('<li style=""><input type="text" name="items" style="width:200px;"></input></li>');
};

//$(function() {
//
//	if ($(':input[name="data.id"]').val().length > 0) {
//		parent.$.messager.progress({
//			text : '数据加载中....'
//		});
//		$.post(com.sirius.skymall.user.contextPath + '/base/question!getById.sy', {
//			id : $(':input[name="data.id"]').val()
//		}, function(result) {
//			if (result.id != undefined) {
//				$('form').form('load', {
//					'data.id' : result.id,
//					'data.question' : result.question
//					
//					
//				});
//				
//			}
//			parent.$.messager.progress('close');
//		}, 'json');
//	}
//});
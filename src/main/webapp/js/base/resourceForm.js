var submitForm = function($dialog, $grid, $pjq, $mainMenu) {
	if ($('form').form('validate')) {
		var url;
		if ($(':input[name="data.id"]').val().length > 0) {
			url = com.sirius.skymall.user.contextPath + '/base/syresource!update.sy';
		} else {
			url = com.sirius.skymall.user.contextPath + '/base/syresource!save.sy';
		}
		$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
			if (result.success) {
				$grid.treegrid('reload');
				$dialog.dialog('destroy');
				$mainMenu.tree('reload');
			} else {
				$pjq.messager.alert('提示', result.msg, 'error');
			}
		}, 'json');
	}
};
var showIcons = function() {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : '浏览小图标',
		url : com.sirius.skymall.user.contextPath + '/style/icons.jsp',
		buttons : [ {
			text : '确定',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.selectIcon(dialog, $('#iconCls'));
			}
		} ]
	});
};
$(function() {
	if ($(':input[name="data.id"]').val().length > 0) {
		parent.$.messager.progress({
			text : '数据加载中....'
		});
		$.post(com.sirius.skymall.user.contextPath + '/base/syresource!getById.sy', {
			id : $(':input[name="data.id"]').val(),
		}, function(result) {
			if (result.id != undefined) {
				$('form').form('load', {
					'data.id' : result.id,
					'data.name' : result.name,
					'data.url' : result.url,
					'data.syresourcetype.id' : result.syresourcetype.id,
					'data.description' : result.description,
					'data.syresource.id' : result.syresource ? result.syresource.id : '',
					'data.iconCls' : result.iconCls,
					'data.seq' : result.seq,
					'data.target' : result.target,
					'data.bindinfo':result.bindinfo
				});
				$('#iconCls').attr('class', result.iconCls);//设置背景图标
			}
			parent.$.messager.progress('close');
		}, 'json');
	}
});
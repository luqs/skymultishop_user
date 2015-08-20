var submitForm = function($dialog, $grid, $pjq) {
	var nodes = $('#tree').tree('getChecked', [ 'checked', 'indeterminate' ]);
	var ids = [];
	for (var i = 0; i < nodes.length; i++) {
		ids.push(nodes[i].id);
	}
	$.post(com.sirius.skymall.user.contextPath + '/base/syrole!grant.sy', {
		id : $(':input[name="data.id"]').val(),
		ids : ids.join(',')
	}, function(result) {
		if (result.success) {
			$dialog.dialog('destroy');
		} else {
			$pjq.messager.alert('提示', result.msg, 'error');
		}
		$pjq.messager.alert('提示', '授权成功！', 'info');
	}, 'json');
};
$(function() {
	parent.$.messager.progress({
		text : '数据加载中....'
	});
	$('#tree').tree({
		url : com.sirius.skymall.user.contextPath + '/base/syresource!doNotNeedSecurity_getResourcesTree.sy',
		parentField : 'pid',
		checkbox : true,
		formatter : function(node) {
			return node.name;
		},
		onLoadSuccess : function(node, data) {
			$.post(com.sirius.skymall.user.contextPath + '/base/syresource!doNotNeedSecurity_getRoleResources.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result) {
					for (var i = 0; i < result.length; i++) {
						var node = $('#tree').tree('find', result[i].id);
						if (node) {
							var isLeaf = $('#tree').tree('isLeaf', node.target);
							if (isLeaf) {
								$('#tree').tree('check', node.target);
							}
						}
					}
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
	});
});
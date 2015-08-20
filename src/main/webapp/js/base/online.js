var grid;
$(function() {
	grid = $('#grid').datagrid({
		title : '',
		url : com.sirius.skymall.user.contextPath + '/base/syonline!grid.sy',
		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
		sortName : 'createdatetime',
		sortOrder : 'desc',
		pageSize : 100,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		frozenColumns : [ [ {
			width : '100',
			title : '登录名',
			field : 'loginname',
			sortable : true
		}, {
			width : '300',
			title : 'IP地址',
			field : 'ip',
			sortable : true
		} ] ],
		columns : [ [ {
			width : '150',
			title : '创建时间',
			field : 'createdatetime',
			sortable : true
		}, {
			width : '100',
			title : '类别',
			field : 'type',
			sortable : true,
			formatter : function(value, row, index) {
				switch (value) {
				case '0':
					return '注销系统';
				case '1':
					return '登录系统';
				}
			}
		} ] ],
		toolbar : '#toolbar',
		onBeforeLoad : function(param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
		},
		onLoadSuccess : function(data) {
			$('.iconImg').attr('src', com.sirius.skymall.user.pixel_0);
			parent.$.messager.progress('close');
		}
	});
});
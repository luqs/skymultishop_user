var grid;
var add2blackList = function(id) {
	parent.$.messager.confirm('询问', '您确定要将此用户加入到黑名单？', function(r) {
		if (r) {
			$.post(com.sirius.skymall.user.contextPath + '/base/addblacklist!black.sy', {
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
		url : com.sirius.skymall.user.contextPath + '/base/addblacklist!grid.sy',
//		striped : true,
		rownumbers : true,
		pagination : true,
		singleSelect : true,
		idField : 'id',
//		sortName : 'createdatetime',
//		sortOrder : 'desc',
		pageSize : 100,
		pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500, 1000 ],
		columns : [ [{
			width : '75',
			title : '名称',
			field : 'name'
		},{
			width : '75',
			title : '登录名',
			field : 'loginname'
		}, 
//		{
//			width : '70',
//			title : '性别',
//			field : 'xb',
//			formatter : function(value, row, index) {
//				switch (value) {
//				case '0':
//					return '女';
//				case '1':
//					return '男';
//				}
//			}
//		},
//		{
//			width : '70',
//			title : '性别',
//			field : 'xb'
//		},
		{
			width : '70',
			title : '年龄',
			field : 'age'
		},{
			width : '130',
			title : '注册时间',
			field : 'createdatetime'
		},{
			width : '75',
			title : 'id',
			field : 'id'
		},{
			title : '操作',
			field : 'action',
			width : '80',
			formatter : function(value, row) {
				var str = '';
				var parms=row.id+"%"+row.tableName;
//				str += com.sirius.skymall.user.formatString('<a href="javascript:void(0);" onclick="add2blackList(\'{0}\');">加入黑名单</a>',row.id);
				str += com.sirius.skymall.user.formatString('<a href="javascript:void(0);" onclick="add2blackList(\'{0}\');">加入黑名单</a>',parms);
				return str;
			}
		}] ],
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
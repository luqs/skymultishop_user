var grid;
var addFun = function() {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : '添加资源信息',
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyresourceForm.jsp',
		buttons : [ {
			text : '添加',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
			}
		} ]
	});
};
var showFun = function(id) {alert("test");
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : '查看资源信息',
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyresourceForm.jsp?id=' + id
	});
};
var editFun = function(id) {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : '编辑资源信息',
		url : com.sirius.skymall.user.contextPath + '/admin/base/SyresourceForm.jsp?id=' + id,
		buttons : [ {
			text : '编辑',
			handler : function() {
				dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$, parent.mainMenu);
			}
		} ]
	});
};
var removeFun = function(id) {
	parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
		if (r) {
			$.post(com.sirius.skymall.user.contextPath + '/base/syresource!delete.sy', {
				id : id
			}, function() {
				grid.treegrid('reload');
				parent.mainMenu.tree('reload');
			}, 'json');
		}
	});
};
var redoFun = function() {
	var node = grid.treegrid('getSelected');
	if (node) {
		grid.treegrid('expandAll', node.id);
	} else {
		grid.treegrid('expandAll');
	}
};
var undoFun = function() {
	var node = grid.treegrid('getSelected');
	if (node) {
		grid.treegrid('collapseAll', node.id);
	} else {
		grid.treegrid('collapseAll');
	}
};
$(function() {
	grid = $('#grid').treegrid({
		title : '',
		url : com.sirius.skymall.user.contextPath + '/base/syresource!treeGrid.sy',
		idField : 'id',
		treeField : 'name',
		parentField : 'pid',
		rownumbers : true,
		pagination : false,
		sortName : 'seq',
		sortOrder : 'asc',
		frozenColumns : [ [ {
			width : '200',
			title : '资源名称',
			field : 'name'
		} ] ],
		columns : [ [ {
			width : '200',
			title : '图标名称',
			field : 'iconCls'
		}, {
			width : '200',
			title : '资源路径',
			field : 'url',
			formatter : function(value, row) {
				if(value){
					return com.sirius.skymall.user.formatString('<span title="{0}">{1}</span>', value, value);
				}
			}
		}, {
			width : '60',
			title : '资源类型',
			field : 'syresourcetype',
			formatter : function(value, row) {
				return value.name;
			}
		}, {
			width : '150',
			title : '创建时间',
			field : 'createdatetime',
			hidden : true
		}, {
			width : '150',
			title : '修改时间',
			field : 'updatedatetime',
			hidden : true
		}, {
			width : '200',
			title : '资源描述',
			field : 'description',
			formatter : function(value, row) {
				if(value){
					return com.sirius.skymall.user.formatString('<span title="{0}">{1}</span>', value, value);
				}
			}
		}, {
			width : '80',
			title : '排序',
			field : 'seq',
			hidden : true
		}, {
			width : '80',
			title : '目标',
			field : 'target'
		}, {
			title : '操作',
			field : 'action',
			width : '60',
			formatter : function(value, row) {
				var str = '';
				$("#resourceAction >img").each(function(){
					  str += com.sirius.skymall.user.formatString($(this)[0].outerHTML, row.id);
				});
				return str;
			}
		} ] ],
		toolbar : '#toolbar',
		onBeforeLoad : function(row, param) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
		},
		onLoadSuccess : function(row, data) {
			$('.iconImg').attr('src', com.sirius.skymall.user.pixel_0);
			parent.$.messager.progress('close');
		}
	});
});
var grid;
	var addFun = function() {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : '添加白名单',
			url : com.sirius.skymall.user.contextPath + '/admin/base/whiteList_add.jsp',
			buttons : [ {
				text : '添加',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var showFun = function(id) {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : '查看白名单信息',
// 			url : com.sirius.skymall.user.contextPath + '/admin/base/SyroleForm.jsp?id=' + id
			url : com.sirius.skymall.user.contextPath + '/admin/base/whitelistForm.jsp?id=' + id
		});
	};
	var editFun = function(id) {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : '编辑白名单信息',
			url : com.sirius.skymall.user.contextPath + '/admin/base/whitelistForm.jsp?id=' + id,
			buttons : [ {
				text : '编辑',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(com.sirius.skymall.user.contextPath + '/base/sywhitelist!delete.sy', {
					id : id
				}, function() {
					grid.datagrid('reload');
				}, 'json');
			}
		});
	};
	var grantFun = function(id) {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : '角色授权',
			url : com.sirius.skymall.user.contextPath + '/admin/base/SyroleGrant.jsp?id=' + id,
			buttons : [ {
				text : '授权',
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : com.sirius.skymall.user.contextPath + '/base/sywhitelist!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
// 			sortName : 'seq',
// 			sortOrder : 'asc',
			frozenColumns : [ [ {
				width : '100',
				title : '管理员名称',
				field : 'adminName'
			} ] ],
			columns : [ [ {
				width : '100',
				title : '管理员登陆名',
				field : 'adminLoginName'
			}, {
				width : '100',
				title : '管理员IP',
				field : 'adminIp'
			}, {
				title : '操作',
				field : 'action',
				width : '80',
				formatter : function(value, row) {
					var str = '';
					$("#whitelistAction >img").each(function(){
						  str += com.sirius.skymall.user.formatString($(this)[0].outerHTML, row.id);
					});
					return str;
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
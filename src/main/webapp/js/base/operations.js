$(function() {
		grid = $('#grid').datagrid({
			title : '',
			url : com.sirius.skymall.user.contextPath + '/base/operationhistory!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			frozenColumns : [ [ {
				width : '100',
				title :  '登录名',
				field : 'loginName'
			} ] ],
			columns : [ [{
				width : '100',
				title : '登录IP',
				field : 'loginIp'
			}, {
				width : '200',
				title : '操作路径',
				field : 'operationUrl'
			}, {
				width : '170',
				title : '所做操作',
				field : 'operationName'
			},{
				width : '120',
				title : '操作日期',
				field : 'operationTime'
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
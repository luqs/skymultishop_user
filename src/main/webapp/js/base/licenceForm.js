var content='';
var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = com.sirius.skymall.user.contextPath + '/base/licence!update.sy';
			} else {
				url = com.sirius.skymall.user.contextPath + '/base/licence!save.sy';
			}
			var checkRes=true;
			var content=$(':input[name="data.licence"]').val();//条款 
			if(content.length<=0){
				$pjq.messager.alert('提示', '条款内容不能为空', 'error');
				checkRes=false;	
			}
			if(checkRes){
				$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
					if (result.success) {
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert('提示', result.msg, 'error');
					}
				}, 'json');
			}
		}
	};
$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : '数据加载中....'
			});
			$.post(com.sirius.skymall.user.contextPath + '/base/licence!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id
						
					});
				}
				parent.$.messager.progress('close');
			}, 'json');
		}
		$("#savef").click(function(){
			if(confirm("确定修改?")){
			    $("#form1").submit();
			}
		});
});

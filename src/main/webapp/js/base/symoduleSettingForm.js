var submitForm = function($dialog, $grid, $pjq) {
		if ($('form').form('validate')) {
			var url;
			if ($(':input[name="data.id"]').val().length > 0) {
				url = com.sirius.skymall.user.contextPath + '/base/symodulesetting!updatemodule.sy';
			} else {
				url = com.sirius.skymall.user.contextPath + '/base/symodulesetting!save.sy';
			}
			var checkRes=true;
//			var telNum=$(':input[name="data.tel"]').val();//手机号码
//			var content=$(':input[name="data.msgContent"]').val();//留言内容 
//			var email=$(':input[name="data.email"]').val();//邮箱
//			var qqNum=$(':input[name="data.qq"]').val();//qq号
//			var nichen=$(':input[name="data.nichen"]').val();//昵称
			if(checkRes){
				$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
					if (result.success) {
						$grid.datagrid('load');
						$dialog.dialog('destroy');
					} else {
						$pjq.messager.alert(const_message_role_warning, result.msg, 'error');
					}
				}, 'json');
			}
		}
	};
	$(function() {
		if ($(':input[name="data.id"]').val().length > 0) {
			parent.$.messager.progress({
				text : const_message_role_loading
			});///base/symodulesetting!edit.sy?id=' + id+'&type=view
			$.post(com.sirius.skymall.user.contextPath + '/base/symodulesetting!getById.sy', {
				id : $(':input[name="data.id"]').val()
			}, function(result) {
				if (result.id != undefined) {
					$('form').form('load', {
						'data.id' : result.id,
						'data.title' : result.title,
						'data.link' : result.link,
						'data.icon' : result.icon
					});
					var vis=[];
					vis.push("<select class='visible' name='data.visible'>");
					if(result.visible==1){
						vis.push("<option selected='selected' value='1'>"+const_message_survey_visible+"</option>");
						vis.push("<option value='2'>"+const_message_survey_invisible+"</option>");
					}else{
						vis.push("<option value='1'>"+const_message_survey_visible+"</option>");
						vis.push("<option selected='selected' value='2'>"+const_message_survey_invisible+"</option>");
					}
					$(".visible").html(vis.join(""));
					
					
					
					var vis=[];
					vis.push("<select class='showindex' name='data.showindex'>");
					for(var i=1;i<12;i++){
						var select="";
						if(i==result.showindex){
							select='selected="selected"';
						}
						vis.push("<option "+select+" value='"+i+"'>"+i+"</option>");
					}
					vis.push("");
					$(".showindex").html(vis.join(""));
					
					
					
					
				}
				parent.$.messager.progress('close');
			}, 'json');
		}else{
			
			var vis=[];
			vis.push("<select class='visible' name='data.visible'>");
//			if(result.visible==1){
				vis.push("<option selected='selected' value='1'>"+const_message_survey_visible+"</option>");
				vis.push("<option value='2'>"+const_message_survey_invisible+"</option>");
			$(".visible").html(vis.join(""));
			
			
			
			var vis=[];
			vis.push("<select class='showindex' name='data.showindex'>");
			for(var i=1;i<12;i++){
				var select="";
				
				vis.push("<option value='"+i+"'>"+i+"</option>");
			}
			vis.push("");
			$(".showindex").html(vis.join(""));
			
		}
	});
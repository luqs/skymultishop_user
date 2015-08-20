var grid;
	var addFun = function() {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : '添加注册条款信息',
			url : com.sirius.skymall.user.contextPath + '/admin/base/symoduleSettingForm.jsp',
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
			title : const_message_feedback_view_feedback,
			url : com.sirius.skymall.user.contextPath + '/admin/base/symoduleSettingForm.jsp?id=' + id
		});
		
		//window.location.href=com.sirius.skymall.user.contextPath + '/base/symodulesetting!edit.sy?id=' + id+'&type=view';
	};
	var editFun = function(id) {
		var dialog = parent.com.sirius.skymall.user.modalDialog({
			title : const_message_feedback_view_feedback,
			url : com.sirius.skymall.user.contextPath + '/admin/base/symoduleSettingForm.jsp?id=' + id,
			buttons : [ {
				text : const_message_tool_edit,
				handler : function() {
					dialog.find('iframe').get(0).contentWindow.submitForm(dialog, grid, parent.$);
				}
			} ]
		});
	};
	var removeFun = function(id) {
		parent.$.messager.confirm('询问', '您确定要删除此记录？', function(r) {
			if (r) {
				$.post(com.sirius.skymall.user.contextPath + '/base/symodulesetting!delete.sy', {
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
			url : com.sirius.skymall.user.contextPath + '/base/symodulesetting!grid.sy',
			striped : true,
			rownumbers : true,
			pagination : true,
			singleSelect : true,
			idField : 'id',
			pageSize : 50,
			pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
			columns : [ [ {
				width : '260',
				title : const_message_survey_form_title,
				field : 'title'
			},
			{
				width : '260',
				title : const_message_survey_link,
				field : 'link'
			},
			{
				width : '260',
				title : const_message_survey_icon,
				field : 'icon'
			},
			{
				width : '260',
				title : const_message_survey_showindex,
				field : 'showindex',
				formatter : function(value, row) {
					var vis=[];
					vis.push("<select class='showindex'>");
					for(var i=1;i<12;i++){
						var select="";
						if(i==row.showindex){
							select='selected="selected"';
						}
						vis.push("<option "+select+" value='"+i+"'>"+i+"</option>");
					}
					vis.push("</select><input type='hidden' value='"+row.id+"'/>");
					return vis.join("");
				}
			},
			{
				width : '260',
				title : const_message_survey_visible,
				field : 'visible',
				formatter : function(value, row) {
					var vis=[];
					vis.push("<select class='visible'>");
					if(row.visible==1){
						vis.push("<option selected='selected' value='1'>"+const_message_survey_visible+"</option>");
						vis.push("<option value='2'>"+const_message_survey_invisible+"</option>");
					}else{
						vis.push("<option value='1'>"+const_message_survey_visible+"</option>");
						vis.push("<option selected='selected' value='2'>"+const_message_survey_invisible+"</option>");
					}
					vis.push("</select><input type='hidden' value='"+row.id+"'/>");
					return vis.join("");
				}
			}, {
				title : '操作',
				field : 'action',
				width : '90',
				formatter : function(value, row) {
					var str = '';
					$("#licenceAction >img").each(function(){
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
				
				$(".visible").change(function(){
					var val=$(this).val();
					var id=$(this).next().val();
					$.ajax({
						type:"post",
						url:"symodulesetting!update.sy",
						data:"id="+id+"&visible="+val,
						success:function(data){
							if(data=="1"){
								alert("修改成功")
							}else{
								alert("修改失败");
								window.location.href=window.location.href;
							}
						}	
					})
				});
//				/symoduleSetting
				
				$(".showindex").change(function(){
					var val=$(this).val();
					var id=$(this).next().val();
					$.ajax({
						type:"post",
						url:"symodulesetting!update.sy",
						data:"id="+id+"&showindex="+val,
						success:function(data){
							if(data=="1"){
								alert("修改成功")
							}else{
								alert("修改失败");
								window.location.href=window.location.href;
							}
						}	
					})
				});
				
				
			}
		});
		
		
		
		
		
		
		
		
	});
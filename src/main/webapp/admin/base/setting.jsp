<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String id = request.getParameter("id");
	if (id == null) {
		id = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/roleForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
var uploader;

$(function(){
	$("#submit").click(function(){
		
		if ($('.form').form('validate')) {
			if (uploader.files.length > 0) {
				uploader.start();
				uploader.bind('StateChanged', function(uploader) {// 在所有的文件上传完毕时，提交表单
					if (uploader.files.length === (uploader.total.uploaded + uploader.total.failed)) {
						parent.com.sirius.skymall.user.progressBar('close');//关闭上传进度条
						$(".form").submit();
						//submitNow($dialog, $grid, $pjq);
					}
				});
			} else {
				//parent.com.sirius.skymall.user.progressBar('close');//关闭上传进度条
				$(".form").submit();
			}
		}
		
		
	})	;
	
	
	
	uploader = new plupload.Uploader({//上传插件定义
		browse_button : 'pickfiles',//选择文件的按钮
		container : 'container',//文件上传容器
		runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
		//flash_swf_url : com.sirius.skymall.user.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
		url : com.sirius.skymall.user.contextPath + '/plupload?fileFolder=/userPhoto',//上传文件路径
		max_file_size : '5mb',//100b, 10kb, 10mb, 1gb
		chunk_size : '10mb',//分块大小，小于这个大小的不分块
		unique_names : true,//生成唯一文件名
		// 如果可能的话，压缩图片大小
		/*resize : {
			width : 320,
			height : 240,
			quality : 90
		},*/
		// 指定要浏览的文件类型
		filters : [ {
			title : '图片文件',
			extensions : 'jpg,gif,png'
		} ]
	});
	uploader.bind('Init', function(up, params) {//初始化时
		//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
		$('#filelist').html("");
	});
	uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
		if (uploader.files.length > 1) {
			parent.$.messager.alert('提示', '只允许选择一张照片！', 'error');
			uploader.stop();
			return;
		}
		$('.ext-icon-cross').hide();
	});
	uploader.bind('FilesAdded', function(up, files) {//选择文件后
		$.each(files, function(i, file) {
			$('#filelist').append('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');
		});
		up.refresh();
	});
	uploader.bind('UploadProgress', function(up, file) {//上传进度改变
		var msg;
		if (file.percent == 100) {
			msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
		} else {
			msg = file.percent;
		}
		$('#' + file.id + '>strong').html(msg + '%');

		parent.com.sirius.skymall.user.progressBar({//显示文件上传滚动条
			title : '文件上传中...',
			value : msg
		});
	});
	uploader.bind('Error', function(up, err) {//出现错误
		$('#filelist').append("<div>错误代码: " + err.code + ", 描述信息: " + err.message + (err.file ? ", 文件名称: " + err.file.name : "") + "</div>");
		up.refresh();
	});
	uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
		var response = $.parseJSON(info.response);
		if (response.status) {
			$('#' + file.id + '>strong').html("100%");
			//console.info(response.fileUrl);
			//console.info(file.name);
			//$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
			//$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
			$(':input[name="data.adminsystemlogo"]').val(response.fileUrl);
			$("#data.pic").attr("src","${pageContext.request.contextPath}/"+response.fileUrl)
		}
	});
	uploader.init();
	
	
	
	
	
	
});

</script>


</head>
<body>
	<form method="post" class="form" action="${pageContext.request.contextPath}/base/sysitesetting!saveOrUpdate.sy">
		<fieldset>
			<legend>配置信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>网站名称</th>
					<td colspan="2"><input name="data.siteName" value="${siteSetting.siteName}"  /> <input type="hidden" name="data.id" value="${siteSetting.id}" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>后台管理logo</th>
					<td id="container">
						<a id="pickfiles" class="easyui-linkbutton">上传</a>
						<div id="filelist">您的浏览器没有安装Flash插件，或不支持HTML5！</div>
					</td>
					<td>
						<img alt="" src="${pageContext.request.contextPath}/${siteSetting.adminsystemlogo}" style='width:150px;height:150px' id="data.pic">
						<input name="data.adminsystemlogo" type='hidden'  value="${siteSetting.adminsystemlogo}" class="easyui-validatebox" data-options="required:true" />
					
					</td>
				</tr>
				<tr>
					<th>商城版权信息</th>
					<td colspan="2"><input name="data.copyRightInfo"  value="${siteSetting.copyRightInfo}" class="easyui-validatebox" data-options="required:true" style="width: 155px;" value="100" /></td>
				</tr>
				
				<tr>
					
					<td colspan="3"><a class="easyui-linkbutton" id="submit">保存</a></td>
				</tr>
				
				
			</table>
		</fieldset>
	</form>
</body>
</html>
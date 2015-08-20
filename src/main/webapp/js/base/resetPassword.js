var uploader;//上传对象
var submitNow = function($dialog, $grid, $pjq) {
	var url;
	//if ($(':input[name="data.id"]').val().length > 0) {
		url = com.sirius.skymall.user.contextPath + '/base/sycommonuser!resetpwd.sy';
	//} else {
	//	url = com.sirius.skymall.user.contextPath + '/base/sycommonuser!save.sy';
	//}
	$.post(url, com.sirius.skymall.user.serializeObject($('form')), function(result) {
		parent.com.sirius.skymall.user.progressBar('close');//关闭上传进度条

		if (result.success) {
			$.messager.alert('提示', result.msg, 'info');
//			$.datagrid('load');
//			$.dialog('destroy');
			$("form")[0].reset();
		} else {
			$.messager.alert('提示', result.msg, 'error');
		}
	}, 'json');
};
var submitForm = function($dialog, $grid, $pjq) {
	if ($('form').form('validate')) {
			if(!$("#username").val()){
				alert("Please fill in the Login name");
				return;
			}else if($.trim($("#username").val()).length!=8){
				alert("Please input eight  digit");
				return;
			}else if(!$("#password").val()){
				alert("Please fill in the Login password");
				return;
			}
			submitNow($dialog, $grid, $pjq);
	}
};
$(function() {

//	if ($(':input[name="data.id"]').val().length > 0) {
//		parent.$.messager.progress({
//			text : '数据加载中....'
//		});
//		$.post(com.sirius.skymall.user.contextPath + '/base/sycommonuser!getById.sy', {
//			id : $(':input[name="data.id"]').val()
//		}, function(result) {
//			if (result.id != undefined) {
//				$('form').form('load', {
//					'data.id' : result.id,
//					'data.name' : result.name,
//					'data.loginname' : result.loginname,
//					'sex' : result.sex,
//					'data.realname' : result.realname,
//					'photo' : result.photo
//				});
//				if (result.photo) {
//					$('#photo').attr('src', com.sirius.skymall.user.contextPath + result.photo);
//				}
//			}
//			parent.$.messager.progress('close');
//		}, 'json');
//	}
//
//	uploader = new plupload.Uploader({//上传插件定义
//		browse_button : 'pickfiles',//选择文件的按钮
//		container : 'container',//文件上传容器
//		runtimes : 'html5,flash',//设置运行环境，会按设置的顺序，可以选择的值有html5,gears,flash,silverlight,browserplus,html4
//		//flash_swf_url : com.sirius.skymall.user.contextPath + '/jslib/plupload_1_5_7/plupload/js/plupload.flash.swf',// Flash环境路径设置
//		url : com.sirius.skymall.user.contextPath + '/plupload?fileFolder=/userPhoto',//上传文件路径
//		max_file_size : '5mb',//100b, 10kb, 10mb, 1gb
//		chunk_size : '10mb',//分块大小，小于这个大小的不分块
//		unique_names : true,//生成唯一文件名
//		// 如果可能的话，压缩图片大小
//		/*resize : {
//			width : 320,
//			height : 240,
//			quality : 90
//		},*/
//		// 指定要浏览的文件类型
//		filters : [ {
//			title : '图片文件',
//			extensions : 'jpg,gif,png'
//		} ]
//	});
//	uploader.bind('Init', function(up, params) {//初始化时
//		//$('#filelist').html("<div>当前运行环境: " + params.runtime + "</div>");
//		$('#filelist').html("");
//	});
//	uploader.bind('BeforeUpload', function(uploader, file) {//上传之前
//		if (uploader.files.length > 1) {
//			parent.$.messager.alert('提示', '只允许选择一张照片！', 'error');
//			uploader.stop();
//			return;
//		}
//		$('.ext-icon-cross').hide();
//	});
//	uploader.bind('FilesAdded', function(up, files) {//选择文件后
//		$.each(files, function(i, file) {
//			$('#filelist').append('<div id="' + file.id + '">' + file.name + '(' + plupload.formatSize(file.size) + ')<strong></strong>' + '<span onclick="uploader.removeFile(uploader.getFile($(this).parent().attr(\'id\')));$(this).parent().remove();" style="cursor:pointer;" class="ext-icon-cross" title="删除">&nbsp;&nbsp;&nbsp;&nbsp;</span></div>');
//		});
//		up.refresh();
//	});
//	uploader.bind('UploadProgress', function(up, file) {//上传进度改变
//		var msg;
//		if (file.percent == 100) {
//			msg = '99';//因为某些大文件上传到服务器需要合并的过程，所以强制客户看到99%，等后台合并完成...
//		} else {
//			msg = file.percent;
//		}
//		$('#' + file.id + '>strong').html(msg + '%');
//
//		parent.com.sirius.skymall.user.progressBar({//显示文件上传滚动条
//			title : '文件上传中...',
//			value : msg
//		});
//	});
//	uploader.bind('Error', function(up, err) {//出现错误
//		$('#filelist').append("<div>错误代码: " + err.code + ", 描述信息: " + err.message + (err.file ? ", 文件名称: " + err.file.name : "") + "</div>");
//		up.refresh();
//	});
//	uploader.bind('FileUploaded', function(up, file, info) {//上传完毕
//		var response = $.parseJSON(info.response);
//		if (response.status) {
//			$('#' + file.id + '>strong').html("100%");
//			//console.info(response.fileUrl);
//			//console.info(file.name);
//			//$('#f1').append('<input type="hidden" name="fileUrl" value="'+response.fileUrl+'"/>');
//			//$('#f1').append('<input type="hidden" name="fileName" value="'+file.name+'"/><br/>');
//			$(':input[name="photo"]').val(response.fileUrl);
//		}
//	});
//	uploader.init();

});
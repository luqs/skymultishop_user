var mainMenu;
var mainTabs;

$(function() {

	var loginFun = function() {
		if ($('#loginDialog form').form('validate')) {
			$('#loginBtn').linkbutton('disable');
			$.post(com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_login.sy', $('#loginDialog form').serialize(), function(result) {
				if (result.success) {
					$('#loginDialog').dialog('close');
				} else {
					$.messager.alert('提示', result.msg, 'error', function() {
						$('#loginDialog form :input:eq(1)').focus();
					});
				}
				$('#loginBtn').linkbutton('enable');
			}, 'json');
		}
	};
	$('#loginDialog').show().dialog({
		modal : true,
		closable : false,
		iconCls : 'ext-icon-lock_open',
		buttons : [ {
			id : 'loginBtn',
			text : '登录',
			handler : function() {
				loginFun();
			}
		} ],
		onOpen : function() {
			$('#loginDialog form :input[name="data.pwd"]').val('');
			$('form :input').keyup(function(event) {
				if (event.keyCode == 13) {
					loginFun();
				}
			});
		}
	}).dialog('close');

	$('#passwordDialog').show().dialog({
		modal : true,
		closable : true,
		iconCls : 'ext-icon-lock_edit',
		buttons : [ {
			text : '修改',
			handler : function() {
				if ($('#passwordDialog form').form('validate')) {
					$.post(com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSecurity_updateCurrentPwd.sy', {
						'data.pwd' : $('#pwd').val()
					}, function(result) {
						if (result.success) {
							$.messager.alert('提示', '密码修改成功！', 'info');
							$('#passwordDialog').dialog('close');
						}
					}, 'json');
				}
			}
		} ],
		onOpen : function() {
			$('#passwordDialog form :input').val('');
		}
	}).dialog('close');

	mainMenu = $('#mainMenu').tree({
		url : com.sirius.skymall.user.contextPath + '/base/syresource!doNotNeedSecurity_getMainMenu.sy',
		parentField : 'pid',
		onClick : function(node) {
			if (node.attributes.url) {
				var src = com.sirius.skymall.user.contextPath + node.attributes.url;
				if (!com.sirius.skymall.user.startWith(node.attributes.url, '/')) {
					src = node.attributes.url;
				}
				if (node.attributes.target && node.attributes.target.length > 0) {
					window.open(src, node.attributes.target);
				} else {
					var tabs = $('#mainTabs');
					var opts = {
						title : node.text,
						closable : true,
						iconCls : node.iconCls,
						content : com.sirius.skymall.user.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', src),
						border : false,
						fit : true
					};
					if (tabs.tabs('exists', opts.title)) {
						tabs.tabs('select', opts.title);
					} else {
						tabs.tabs('add', opts);
					}
				}
			}
		}
	});

	$('#mainLayout').layout('panel', 'center').panel({
		onResize : function(width, height) {
			com.sirius.skymall.user.setIframeHeight('centerIframe', $('#mainLayout').layout('panel', 'center').panel('options').height - 5);
		}
	});

	mainTabs = $('#mainTabs').tabs({
		fit : true,
		border : false,
		tools : [ {
			iconCls : 'ext-icon-arrow_up',
			handler : function() {
				mainTabs.tabs({
					tabPosition : 'top'
				});
			}
		}, {
			iconCls : 'ext-icon-arrow_left',
			handler : function() {
				mainTabs.tabs({
					tabPosition : 'left'
				});
			}
		}, {
			iconCls : 'ext-icon-arrow_down',
			handler : function() {
				mainTabs.tabs({
					tabPosition : 'bottom'
				});
			}
		}, {
			iconCls : 'ext-icon-arrow_right',
			handler : function() {
				mainTabs.tabs({
					tabPosition : 'right'
				});
			}
		}, {
			text : const_message_tool_refresh,
			iconCls : 'ext-icon-arrow_refresh',
			handler : function() {
				var panel = mainTabs.tabs('getSelected').panel('panel');
				var frame = panel.find('iframe');
				try {
					if (frame.length > 0) {
						for (var i = 0; i < frame.length; i++) {
							frame[i].contentWindow.document.write('');
							frame[i].contentWindow.close();
							frame[i].src = frame[i].src;
						}
						if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
							try {
								CollectGarbage();
							} catch (e) {
							}
						}
					}
				} catch (e) {
				}
			}
		}, {
			text : const_message_tool_close,
			iconCls : 'ext-icon-cross',
			handler : function() {
				var index = mainTabs.tabs('getTabIndex', mainTabs.tabs('getSelected'));
				var tab = mainTabs.tabs('getTab', index);
				if (tab.panel('options').closable) {
					mainTabs.tabs('close', index);
				} else {
					$.messager.alert('提示', '[' + tab.panel('options').title + ']不可以被关闭！', 'error');
				}
			}
		} ]
	});

	$.ajax({
		type:'get',
		url:com.sirius.skymall.user.contextPath+'/service/rest/site/getinfo.json',
		success:function(data){
			if(data.Result && data.Result.settingEntity){
				var logo=com.sirius.skymall.user.contextPath+data.Result.settingEntity.adminsystemlogo;
				$(".logo").css("background",'url("'+logo+'") no-repeat scroll 0 0 rgba(0, 0, 0, 0)');
			}
		
			
		},
		error:function(){
		}
	});
	
	
	
	
	
});
	
var lockWindowFun = function() {
	$.post(com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_logout.sy', function(result) {
		$('#loginDialog').dialog('open');
	}, 'json');
};
var logoutFun = function() {
	$.post(com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_logout.sy', function(result) {
		location.replace(com.sirius.skymall.user.contextPath + '/index.jsp');
	}, 'json');
};
var showMyInfoFun = function() {
	var dialog = parent.com.sirius.skymall.user.modalDialog({
		title : '我的信息',
		url : com.sirius.skymall.user.contextPath + '/base/syuser!doNotNeedSessionAndSecurity_getUserInfo.sy'
	});
};


function GetQueryString(hash, name) {
	   var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");
	   var r = hash.match(reg);
	   if (r!=null) return (unescape(r[2])); return null;
}
function addtabs(hash){
	var content = com.sirius.skymall.user.formatString('<iframe src="{0}" allowTransparency="true" style="border:0;width:100%;height:99%;" frameBorder="0"></iframe>', GetQueryString(hash, "href"));
	$("#mainTabs").tabs("add", {
        title: GetQueryString(hash, "title"), content: content, iniframe: true, closable: true
    })
}


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="message.app.title" /></title>
<jsp:include page="../inc.jsp"></jsp:include>
<script src="${ctx}/js/pageControl.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body id="mainLayout" class="easyui-layout">
	<div data-options="region:'north',href:''" style="height: 70px; overflow: hidden;" class="logo">
	<%@ include file="header.jsp"%>
	</div>
	<div data-options="region:'west',href:'',split:true" title='<s:text name="message.main.navigation" />' style="width: 200px; padding: 10px;">
		<ul id="mainMenu"></ul>
	</div>
	<iframe style="display:none;" src="${productserver}/proxy.jsp?token=${token}"></iframe>
	<iframe style="display:none;" src="${orderserver}/proxy.jsp?token=${token}"></iframe>
	<iframe style="display:none;" src="${activityserver}/proxy.jsp?token=${token}"></iframe>
	<div data-options="region:'center'" style="overflow: hidden;">
		<div id="mainTabs">
			<div title='<s:text name="message.main.about" />' data-options="iconCls:'ext-icon-heart'">
				<iframe src="${ctx}/welcome.jsp" allowTransparency="true" style="border: 0; width: 100%; height: 99%;" frameBorder="0"></iframe>
			</div>
		</div>
	</div>
	<div data-options="region:'south',href:'${ctx}/admin/footer.jsp',border:false" style="height: 30px; overflow: hidden;"></div>

	<div id="loginDialog" title='<s:text name="message.main.unlock" />' style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th width="100"><s:text name="message.login.name" /></th>
					<td>${loginName}<input name="data.loginname" readonly="readonly" type="hidden" value="${loginName}" /></td>
				</tr>
				<tr>
					<th><s:text name="message.login.pwd" /></th>
					<td><input name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="passwordDialog" title='<s:text name="message.main.modifypwd" />' style="display: none;">
		<form method="post" class="form" onsubmit="return false;">
			<table class="table">
				<tr>
					<th><s:text name="message.login.newpwd" /></th>
					<td><input id="pwd" name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.login.repwd" /></th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<title><s:text name="message.login.title" /></title>

</head>
<body>
	<div id="loginDialog" title="<s:text name="message.login.title" />" style="display: none; width: 360px; height: 210px; overflow: hidden;">
		<div id="loginTabs" class="easyui-tabs" data-options="fit:true,border:false">
			<div title="<s:text name="message.login.user.input_mode" />" style="overflow: hidden; padding: 10px;">
				<form method="post" class="form">
					<table class="table" style="width: 100%; height: 100%;">
						<tr>
							<th width="100"><s:text name="message.login.name" /></th>
							<td><input name="data.loginname" class="easyui-validatebox" data-options="required:true" value="" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th><s:text name="message.login.pwd" /></th>
							<td><input name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" value="" style="width: 210px;" /></td>
						</tr>
						<tr>
							<th><s:text name="message.login.identifying" /></th>
							<td style="vertical-align:middle">  <input name="data.code"  class="easyui-validatebox"  value="" style="width: 110px;" />
								<img alt="" id="valdatecode" style="vertical-align:middle" src="home!doNotNeedSessionAndSecurity_validateCode.sy" onclick="this.src='home!doNotNeedSessionAndSecurity_validateCode.sy?time=' + new Date()"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<script src="${ctx}/js/login.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</body>
</html>
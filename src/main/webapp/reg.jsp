<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<title><s:text name="message.reg.title" /></title>

</head>
<style type="text/css">
	.tipinfo{
		color:red;
		display:none;
	}
	

</style>
<body>
	<div id="regDialog" title="<s:text name="message.reg.title" />" style="display: none;">
		<div class="tipinfo"></div>
		<form method="post" class="form">
			<table class="table">
				<tr>
					<th width="100"><s:text name="message.login.name" /></th>
					<td><input id="loginname" name="data.loginname" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.login.pwd" /></th>
					<td><input id="pwd" name="data.pwd" type="password" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.login.repwd" /></th>
					<td><input type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#pwd\']'" /></td>
				</tr>
			</table>
		</form>
	</div>
	<script src="${ctx}/js/reg.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</body>
</html>
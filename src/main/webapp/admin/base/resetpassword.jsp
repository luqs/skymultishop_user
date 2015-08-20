<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="message.user.usermanagement" /></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/resetPassword.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	
	<div id="blacklistAction">
		<form action="">
			<table style="margin-top: 200px;margin-left:200px; height: 150px;">
				<caption>Reset password</caption>
				<tbody>
				<tr>
					<td>loginName</td>
					<td><input type="input" name="data.loginname" id="username"></td>
				</tr>
				<tr>
					<td>password</td>
					<td><input type="password" name="data.pwd" id="password" value="123456"><span style="margin-left:10px ;font-size:12px;">密码默认为123456</span></td>
				</tr>
				<tr>
				    <td></td>
					<td><input type="button" value="submit" onclick="submitForm()"></td>
				</tr>
				</tbody>
			</table>
	</form>
	
	
	</div>
</body>
</html>
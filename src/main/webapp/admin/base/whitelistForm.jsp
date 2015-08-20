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
<script src="${ctx}/js/base/whitelistForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>白名单基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th>管理员名称</th>
					<td><input name="data.adminName" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>管理员登陆名</th>
					<td><input name="data.adminLoginName" class="easyui-numberspinner" /></td>
					<th>管理员IP地址</th>
					<td><textarea name="data.adminIp"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
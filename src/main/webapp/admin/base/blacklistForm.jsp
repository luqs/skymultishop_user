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
<script src="${ctx}/js/base/blacklistForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend>黑名单基本信息</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th>名称</th>
					<td><input name="data.name" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>登录名</th>
					<td><input name="data.loginname" class="easyui-validatebox" /></td>
					<th>年龄</th>
					<td><input name="data.age"  class="easyui-validatebox"></td>
				</tr>
				<tr>
					<th>性别</th>
					<td><input name="data.sex" class="easyui-validatebox"  /></td>
					<th>日期</th>
					<td><textarea name="data.createDate"></textarea></td>
				</tr>
				<tr>
				    <td colspan="4">
				       <input type="hidden"  name="data.userIntablename">
				    </td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
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
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend><s:text name="message.role.form.title" /></legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th><s:text name="message.role.form.id" /></th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th><s:text name="message.role.form.rolename" /></th>
					<td><input name="data.name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.role.form.order" /></th>
					<td><input name="data.seq" class="easyui-numberspinner" data-options="required:true,min:0,max:100000,editable:false" style="width: 155px;" value="100" /></td>
					<th><s:text name="message.role.form.description" /></th>
					<td><textarea name="data.description"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
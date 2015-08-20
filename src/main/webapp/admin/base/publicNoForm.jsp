<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<script src="${ctx}/js/base/publicNoForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend><s:text name="message.publicno.editpublicinfo" /></legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th><s:text name="message.role.form.id" /></th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th><s:text name="message.login.field.name" /></th>
					<td><input name="data.name" class="easyui-validatebox"  /></td>
				</tr>
				<tr>
					<th><s:text name="message.login.field.loginname" /></th>
					<td><input name="data.loginname" class="easyui-validatebox" readonly="readonly" /></td>
					<th><s:text name="message.publicno.signature" /></th>
					<td>
						<textarea name="data.signature">
						</textarea>
					</td>
				</tr>
				<tr>
					<th></th>
					<td></td>
					<th></th>
					<td></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
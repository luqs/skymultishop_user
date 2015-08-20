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
<script src="${ctx}/js/base/symoduleSettingForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend><s:text name="message.role.form.title" /></legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th><s:text name="message.survey.form.title" /></th>
					<td><input name="data.title" value="" data-options="required:true"/><input name="data.id" value="<%=id%>" type="hidden" /></td>
					<th><s:text name="message.common.link" /></th>
					<td><input name="data.link" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.common.icon" /></th>
					<td><input name="data.icon" class="easyui-validatebox" data-options="required:true" style="width: 155px;" value="100" /></td>
					<th><s:text name="message.common.showindex" /></th>
					<td class="showindex"></td>
				</tr>
				<tr>
					<th><s:text name="message.common.visible" /></th>
					<td class="visible"></td>
					<th></th>
					<td></td>
				</tr>
				
			</table>
		</fieldset>
	</form>
</body>
</html>
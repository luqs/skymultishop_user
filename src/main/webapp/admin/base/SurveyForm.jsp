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
<script src="${ctx}/js/base/surveyForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend><s:text name="message.survey.title" /></legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th><s:text name="message.survey.form.id" /></th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
				</tr>
				<tr>
				<th><s:text name="message.survey.form.title" /></th>
					<td><input style="width: 257px;" name="data.title" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.survey.form.description" /></th>
					<td><textarea style="width: 257px; height: 220px;" name="data.description" ></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
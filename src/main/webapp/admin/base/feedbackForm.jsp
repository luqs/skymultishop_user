<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String contextPath = request.getContextPath();
%>
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
<script src="${ctx}/js/base/feedbackForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend><s:text name="message.feedback.form.basicinfo" /></legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th><s:text name="message.feedback.field.id" /></th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th><s:text name="message.feedback.field.name" /></th>
					<td><input name="data.name" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.feedback.field.nickname" /></th>
					<td><input name="data.nichen" class="easyui-validatebox" data-options="required:true" /></td>
					<th><s:text name="message.feedback.field.phone" /></th>
					<td><input name="data.tel" class="easyui-validatebox" data-options="required:true" ></td>
				</tr>
				<tr>
					<th><s:text name="message.feedback.field.email" /></th>
					<td><input name="data.email" class="easyui-validatebox"  data-options="required:true"/></td>
					<th><s:text name="message.feedback.field.qq" /></th>
					<td><input name="data.qq" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.feedback.field.title" /></th>
					<td><input name="data.msgTitle" class="easyui-validatebox" data-options="required:true" /></td>
					<th><s:text name="message.feedback.field.content" /></th>
					<td><textarea name="data.msgContent" required="required"></textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
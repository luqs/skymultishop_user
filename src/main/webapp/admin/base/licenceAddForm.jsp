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
<script src="${ctx}/js/base/licenceForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post"  class="form">
		<fieldset>
			<legend>注册条款</legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th>编号</th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
				</tr>
				<tr>
					<th>条款内容</th>
					
					<td><textarea  name="data.licence" class="easyui-ueditor"
						data-options="width: 450, height: 340,required: true">
						<s:property value="data.licence" />
					</textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
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
<script src="${ctx}/js/base/commonUserForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
	<form method="post" class="form">
		<fieldset>
			<legend><s:text name="message.userinfo.title" /></legend>
			<table class="table" style="width: 100%;">
				<tr>
					<th><s:text name="message.role.form.id" /></th>
					<td><input name="data.id" value="<%=id%>" readonly="readonly" /></td>
					<th><s:text name="message.login.field.loginname" /></th>
					<td><input name="data.loginname" class="easyui-validatebox" data-options="required:true" /></td>
				</tr>
				<tr>
					<th><s:text name="message.login.field.name" /></th>
					<td><input name="data.name" /></td>
					<th><s:text name="message.userinfo.sex" /></th>
					<td><select class="easyui-combobox" name="sex" data-options="panelHeight:'auto',editable:false" style="width: 155px;">
							<option value="1"><s:text name="message.userinfo.male" /></option>
							<option value="2"><s:text name="message.userinfo.female" /></option>
					</select></td>
				</tr>
				<tr>
					<th><s:text name="message.userinfo.realname" /></th>
					<td><input name="data.realname" class="easyui-validatebox" /></td>
					<th>room</th>
					<td><input name="data.roomtelephone" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<th>voyageid</th>
					<td><input id="voyageid" name="data.voyagId" class="easyui-validatebox" readonly="readonly"> </td>
					<th></th>
					<td></td>
				</tr>
				<tr>
					<th><s:text name="message.userinfo.uploadpic" /></th>
					<td><div id="container">
							<a id="pickfiles" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom'"><s:text name="message.userinfo.selectfile" /></a>
							<div id="filelist"><s:text name="message.userinfo.uploaderror" /></div>
						</div></td>
					<th></th>
					<td><input name="photo" readonly="readonly" style="display: none;" /> <img id="photo" src="" style="width: 200px; height: 200px;"></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
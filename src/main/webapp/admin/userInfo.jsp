<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../inc.jsp"></jsp:include>
<script>var resourceTreeJson = '${resInfo}';</script>
<script src="${ctx}/js/userInfo.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',fit:true,border:false">
		<table style="width: 100%;">
			<tr>
				<td><fieldset>
						<legend><s:text name="message.userinfo.title" /></legend>
						<table class="table" style="width: 100%;">
							<tr>
								<th><s:text name="message.userinfo.userid" /></th>
								<td>${userInfo.id}</td>
								<th><s:text name="message.userinfo.ip" /></th>
								<td>${userInfo.ip}</td>
							</tr>
							<tr>
								<th><s:text name="message.login.field.loginname" /></th>
								<td>${userInfo.loginname}</td>
								<th><s:text name="message.login.field.name" /></th>
								<td>${userInfo.name}</td>
							</tr>
							<tr>
								<th><s:text name="message.userinfo.sex" /></th>
								<td>
								<s:if test="userInfo.sex != null ||userInfo.sex == 1"><s:text name="message.userinfo.male" /></s:if>
								<s:else><s:text name="message.userinfo.female" /></s:else>
								</td>
								<th><s:text name="message.userinfo.age" /></th>
								<td>${userInfo.age}</td>
							</tr>
							<tr>
								<th><s:text name="message.userinfo.createdatetime" /></th>
								<td>${userInfo.createdatetime}</td>
								<th><s:text name="message.userinfo.updatedatetime" /></th>
								<td>${userInfo.updatedatetime}</td>
							</tr>
						</table>
					</fieldset></td>
			</tr>
			<tr>
				<td>
					<fieldset>
						<legend><s:text name="message.userinfo.privilegeinfo" /></legend>
						<table class="table" style="width: 100%;">
							<thead>
								<tr>
									<th><s:text name="message.userinfo.role" /></th>
									<th><s:text name="message.userinfo.privilege" /></th>
								</tr>
							</thead>
							<tr>
								<td valign="top">
								<ul>
								    <s:iterator value="rolesInfo" var="role">
									<li title='${role.description}'>${role.name}</li>
									</s:iterator>
								</ul>
								</td>
								<td valign="top"><ul id="resources"></ul></td>
							</tr>
						</table>
					</fieldset>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>
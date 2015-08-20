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
<div  id="saveLicence" class="easyui-dialog" title="Basic Dialog" closed="true" data-options="iconCls:'icon-save;'" style="width:450px;height:450px;padding:10px">
</div>
<body>
	<s:if test="type == 'edit'">
	<TABLE width="100%" class="position">
		<TR>
			<TD align="right"><a href="javascript:void(0)" id="savef">保存</a></TD>
		</TR>
	</TABLE>
	</s:if>
	<form method="post" action="licence!updateLicence.sy" class="form" id="form1">
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
						<s:property value="data.licence" escape="false"/>
					</textarea></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
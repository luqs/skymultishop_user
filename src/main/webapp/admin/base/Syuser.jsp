<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/user.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<td>
					<form id="searchForm">
						<table>
							<tr>
								<td><s:text name="message.login.field.loginname" /></td>
								<td><input name="QUERY_t#loginname_S_LK" style="width: 80px;" /></td>
<%-- 								<td><s:text name="message.login.field.name" /></td> --%>
<!-- 								<td><input name="QUERY_t#name_S_LK" style="width: 80px;" /></td> -->
<%-- 								<td><s:text name="message.userinfo.sex" /></td> --%>
<%-- 								<td><select name="QUERY_t#sex_S_EQ" class="easyui-combobox" data-options="panelHeight:'auto',editable:false"><option value=""><s:text name="message.tool.select" /></option> --%>
<%-- 										<option value="1"><s:text name="message.userinfo.male" /></option> --%>
<%-- 										<option value="0"><s:text name="message.userinfo.female" /></option></select></td> --%>
<%-- 								<td><s:text name="message.user.createdate" /></td> --%>
<!-- 								<td><input name="QUERY_t#createdatetime_D_GE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" />-<input name="QUERY_t#createdatetime_D_LE" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td> -->
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',com.sirius.skymall.user.serializeObject($('#searchForm')));"><s:text name="message.tool.filter" /></a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchForm input').val('');grid.datagrid('load',{});"><s:text name="message.tool.resetfilter" /></a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<c:if test="${userPermission.haveSavePermission}">
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();"><s:text name="message.tool.add" /></a></td>
							</c:if>
<!-- 							<td><div class="datagrid-btn-separator"></div></td> -->
<%-- 							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_add',plain:true" onclick=""><s:text name="message.tool.import" /></a></td> --%>
<%-- 							<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-table_go',plain:true" onclick=""><s:text name="message.tool.export" /></a></td> --%>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
	<div id="userAction" style="display: none;">
		<c:if test="${userPermission.haveGetPermission}">
		<img class="iconImg ext-icon-note" title='<s:text name="message.tool.view" />' onclick="showFun('{0}');"/>
		</c:if>
		<c:if test="${userPermission.haveUpdatePermission}">
		<img class="iconImg ext-icon-note_edit" title='<s:text name="message.tool.edit" />' onclick="editFun('{0}');"/>
		</c:if>
		<c:if test="${userPermission.haveGrantPermission}">
		<img class="iconImg ext-icon-user" title='<s:text name="message.userinfo.privilege" />' onclick="grantRoleFun('{0}');"/>
		</c:if>
		<c:if test="${userPermission.haveDelPermission}">
		<img class="iconImg ext-icon-note_delete" title='<s:text name="message.tool.delete" />' onclick="removeFun('{0}');"/>
		</c:if>
	</div>
</body>
</html>
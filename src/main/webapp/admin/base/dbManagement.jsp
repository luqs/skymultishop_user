<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="message.publicno.title" /></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/dbManagement.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar">
		<table>
			<tr>
			 <c:if test="${dbManagementPermission.haveCleanPermission}">
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_delete',plain:true" onclick="cleanFun();"><s:text name="message.dbmanagement.dbcleanimport" /></a></td>
			 </c:if>
<%-- 			 <c:if test="${dbManagementPermission.haveImportPermission}"> --%>
<%-- 				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="importFun();"><s:text name="message.dbmanagement.importusers" /></a></td> --%>
<%-- 			 </c:if>  --%>
			</tr>
		</table>
	</div>
</body>
</html>
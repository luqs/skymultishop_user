<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/symoduleSetting.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar">
		<table>
			<tr>
<%-- 			    <c:if test="${licencePermission.haveSavePermission}"> --%>
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
<%-- 			    </c:if> --%>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
	<div id="licenceAction">
<%-- 		<c:if test="${licencePermission.haveGetPermission}"> --%>
		<img class="iconImg ext-icon-note" title="查看" onclick="showFun('{0}');"/>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${licencePermission.haveUpdatePermission}"> --%>
		<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun('{0}');"/>
<%-- 		</c:if> --%>
<%-- 		<c:if test="${licencePermission.haveDelPermission}"> --%>
		<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun('{0}');"/>
<%-- 		</c:if> --%>
	</div>
</body>
</html>
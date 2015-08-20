<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/resource.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
				<c:if test="${resourcePermission.haveSavePermission}">
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
				</c:if>
				
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="redoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_next'">展开</a><a onclick="undoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-resultset_previous'">折叠</a></td>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><a onclick="grid.treegrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'ext-icon-arrow_refresh'">刷新</a></td>
			</tr>
		</table>
	</div>
	<div id="resourceAction" style="display: none;">
		<c:if test="${resourcePermission.haveGetPermission}">
		<img class="iconImg ext-icon-note" title="查看" onclick="showFun('{0}');"/>
		</c:if>
		<c:if test="${resourcePermission.haveUpdatePermission}">
		<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun('{0}');"/>
		</c:if>
		<c:if test="${resourcePermission.haveDelPermission}">
		<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun('{0}');"/>
		</c:if>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>
</html>
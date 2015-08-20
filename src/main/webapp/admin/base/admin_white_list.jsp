<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>管理员白名单</title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/whitelist.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
			    <c:if test="${whitelistPermission.haveSavePermission}">
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();">添加</a></td>
			    </c:if>
				<td><div class="datagrid-btn-separator"></div></td>
				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'QUERY_t#name_S_LK':value});},prompt:'搜索角色名称'"></input></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td>
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
	<div id="whitelistAction" style="display: none;">
		<c:if test="${whitelistPermission.haveGetPermission}">
		<img class="iconImg ext-icon-note" title="查看" onclick="showFun('{0}');"/>
		</c:if>
<%-- 		<c:if test="${whitelistPermission.haveUpdatePermission}"> --%>
<!-- 		<img class="iconImg ext-icon-note_edit" title="编辑" onclick="editFun('{0}');"/> -->
<%-- 		</c:if> --%>
		<c:if test="${whitelistPermission.haveDelPermission}">
		<img class="iconImg ext-icon-note_delete" title="删除" onclick="removeFun('{0}');"/>
		</c:if>
	</div>
</body>
</html>
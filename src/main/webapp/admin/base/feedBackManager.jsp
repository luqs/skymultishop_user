<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/feedback.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar" style="display: none;">
		<table>
			<tr>
			    <c:if test="${feedbackPermission.haveSavePermission}">
					<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addFun();"><s:text name="message.tool.add" /></a></td>
			    </c:if>
<!-- 				<td><div class="datagrid-btn-separator"></div></td> -->
<!-- 				<td><input id="searchBox" class="easyui-searchbox" style="width: 150px" data-options="searcher:function(value,name){grid.datagrid('load',{'QUERY_t#name_S_LK':value});},prompt:'搜索角色名称'"></input></td> -->
<!-- 				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#searchBox').searchbox('setValue','');grid.datagrid('load',{});">清空查询</a></td> -->
			</tr>
		</table>
	</div>
	<div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
	<div id="fbackAction" style="display: none;">
		<c:if test="${feedbackPermission.haveGetPermission}">
		<img class="iconImg ext-icon-note" title="<s:text name="message.tool.view" />" onclick="showFun('{0}');"/>
		</c:if>
		<c:if test="${feedbackPermission.haveUpdatePermission}">
		<img class="iconImg ext-icon-note_edit" title="<s:text name="message.tool.edit" />" onclick="editFun('{0}');"/>
		</c:if>
		<c:if test="${feedbackPermission.haveDelPermission}">
		<img class="iconImg ext-icon-note_delete" title="<s:text name="message.tool.delete" />" onclick="removeFun('{0}');"/>
		</c:if>
	</div>
</body>
</html>
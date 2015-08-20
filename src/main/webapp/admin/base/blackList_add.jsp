<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
<script src="${ctx}/js/base/blacklist_add.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
  <div id="toolbar" style="display: none;">
  		<table>
  		   <tr>
  		     <td>
  		        <form id="searchForm">
					<table>
					   <tr>
					      <td>用户名：</td>
					      <td><input type="text" name="loginName" style="width: 80px;" ></td>
					      <td>注册日期：</td>
					      <td><input type="date" name="regBeginDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;">-<input name="regEndDate" class="Wdate" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" style="width: 120px;" /></td>
					      <td><td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="grid.datagrid('load',com.sirius.skymall.user.serializeObject($('#searchForm')));">过滤</a></td>
					   </tr>
					</table>
  				</form>
  		     </td>
  		   </tr>
  		</table>
  </div>
  <div data-options="region:'center',fit:true,border:false">
		<table id="grid" data-options="fit:true,border:false"></table>
	</div>
</body>

</html>
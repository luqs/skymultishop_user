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
<script src="${ctx}/js/base/whitelist_add.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<%-- 	<input name="data.id" value="<%=id%>" readonly="readonly" type="hidden" /> --%>
	<fieldset>
		<legend>选择管理员</legend>
		<ul id="tree"></ul>
	</fieldset>
</body>
</html>
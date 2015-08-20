<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>初始化数据库</title>
<jsp:include page="inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function() {
		$.post(com.sirius.skymall.user.contextPath + '/init!doNotNeedSessionAndSecurity_initDb.sy', function(result) {
			if (result.success) {
				window.location.replace(com.sirius.skymall.user.contextPath + '/index.jsp');
			}
		}, 'json');
	});
</script>
</head>
<body>数据库初始化中，请稍候....
</body>
</html>
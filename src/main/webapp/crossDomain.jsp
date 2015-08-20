<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>cross</title>
<jsp:include page="inc.jsp"></jsp:include>
<script src="${ctx}/js/pageControl.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
  
 <body>  
    <script type="text/javascript">
        parent.parent.addtabs(window.location.search.replace(/^\?/,''));
    </script>  
 </body>  
</html> 
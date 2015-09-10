<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String id="";
	String sid = request.getParameter("sid");
	if (sid == null) {
		sid = "";
	}
%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script type="text/javascript">
var const_sid = '<%=sid%>';
</script>
<script src="${ctx}/js/base/multiChoiceForm.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<style type="text/css">	
.dragwen {
    list-style: none outside none;
    margin: 0;
    min-height: 250px;
    padding: 0;
    position: relative;
}
.dragwen > li {
    background: none repeat scroll 0 0 #fff;
    display: block;
    height: auto;
    margin-top: 17px;
    position: relative;
    width: 100%;
}
.topic_type_con {
    height: 100%;
    margin-left: 50px;
    padding: 8px;
}
.Drag_area {
    margin: -10px 0 0 -10px;
    padding: 5px 0 0 10px;
    width: 350px;
    z-index: 9;
}
.th4 {
    font-size: 16px;
    min-height: 30px;
    padding: 6px 0 0;
    width: 350px;
}
.unstyled {
    display: inline-block;
    font-size: 14px;
    width: 100%;
}
.unstyled li {
    margin-top: 5px;
    overflow: hidden;
}
ul, ol, li {
    list-style-type: none;
    padding-left: 0;
}

.unstyled input[type="radio"], .unstyled input[type="checkbox"] {
    border: medium none !important;
    margin: 0 5px;
    vertical-align: middle !important;
}
.unstyled label {
    max-width: 700px;
    vertical-align: middle;
}

.operationH {
    height: 25px;
    margin-top: 10px;
}
.operationH a {
    float: left;
    margin-right: 8px;
    padding-left: 15px;
}
</style>
<body>
	<form method="post" class="form">
	
		<div class="topic_type_con">
				<div style="display:none;">
					<s:text name="message.survey.form.id" />
					<input name="data.id" value="<%=id%>" readonly="readonly" />
				</div>
				<div class="Drag_area">
					<div class="th4 T_edit q_title" name="question">
					<label >title:</label>
					<input name="data.question" type="text" style="width:300px;"></input>
					</div>
				</div>
				<label >items:</label>
				<ul class="question-items">
					<li style="">
					<input type="text" name="items" style="width:200px;"></input>
					</li>
					<li style="">
					<input type="text" name="items" style="width:200px;"></input>
					</li>
				</ul>
				<div class="operationH">
					<a onclick="appendQuestionItems();" class="l-btn"   href="javascript:;">add items</a>
				</div>
				<p></p>
				<label >opts:</label>
				<ul class="unstyled ">
					<li style="">
					<input  type="checkbox" name="radio">
					<input type="text" name="answers" style="width:200px;"></input>
					<input type="checkbox" name="canInputs" value="0">是否可输入
					</li>
					<li style="">
					<input  type="checkbox" name="radio">
					<input type="text" name="answers" style="width:200px;"></input>
					<input type="checkbox" name="canInputs" value="0">是否可输入
					</li>
				</ul>
				<div class="operationH">
					<a onclick="appendOpts();" class="l-btn"   href="javascript:;">add opts</a>
				</div>
			</div>
	</form>
</body>
</html>
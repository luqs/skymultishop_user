<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib_include.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<title><s:text name="message.user.usermanagement" /></title>
<jsp:include page="../../inc.jsp"></jsp:include>
<script src="${ctx}/js/base/questionlist.js?version=${version}" type="text/javascript" charset="utf-8"></script>
</head>
<style type="text/css">	
.question-list{
	height:600px;
	overflow:auto;
}
.dragwen {
    list-style: none outside none;
    margin: 0;
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
    width: 768px;
    z-index: 9;
}
.th4 {
    font-size: 16px;
    min-height: 30px;
    padding: 6px 0 0;
    width: 750px;
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
<body class="easyui-layout" data-options="fit:true,border:false">
	<div id="toolbar">
		<table>
			<tr>
			    <td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addSingleFun('<s:property value="surveyId"/>');"><s:text name="message.tool.addselect" /></a></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addMultiFun('<s:property value="surveyId"/>');"><s:text name="message.tool.addmultiselect" /></a></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addAskFun('<s:property value="surveyId"/>');"><s:text name="message.tool.addask" /></a></td>
				<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-note_add',plain:true" onclick="addOneFun('<s:property value="surveyId"/>');"><s:text name="message.tool.onechoice" /></a></td>
			</tr>
		</table>
	</div>
	<div class="question-list">
	<ul id="question_box" class="dragwen ui-sortable">
	<s:iterator value="questionDtos" status="st">
	   <li class="module"  >
			<div class="topic_type_con">
				<div class="Drag_area">
					<div class="th4 T_edit q_title" ><s:property value="#st.count"/>.<s:property value="question.question"/></div>
				</div>
				<s:if test="items!=null">
					<s:iterator value="items" >
						<div class="Drag_area">
							<div class="th4 T_edit q_title" ><s:property value="title"/></div>
						</div>
						<s:if test="question.type==1">
						<ul class="unstyled ">
							<s:iterator value="answers">
								<li style="">
								<input  type="radio"  name="radio">
								<label  class="T_edit_min"  ><s:property value="answer"/></label>
								</li>
							</s:iterator>
						</ul>
						</s:if> 
						
						
						<s:if test="question.type==2">
						<ul class="unstyled ">
							<s:iterator value="answers">
							<li style="">
							<input  type="checkbox"  name="radio">
							<label  class="T_edit_min"  ><s:property value="answer"/></label>
							</li>
							</s:iterator>
						</ul>
						</s:if> 
						<s:if test="question.type==3">
							<ul class="unstyled">
								<li style="overflow:inherit">
								<div  class="option_Fill">
								<textarea class="" cols="40" rows="5" type="text"></textarea>
								</div>
								</li>
							</ul>
						</s:if> 
						
						<s:if test="question.type==4">
						<ul class="unstyled ">
							<s:iterator value="answers">
								<li style="">
								<input  type="radio"  name="radio">
								<label  class="T_edit_min"  ><s:property value="answer"/></label>
								</li>
							</s:iterator>
						</ul>
						</s:if> 
					</s:iterator>
				</s:if> 
				<s:if test="items==null">
						<s:if test="question.type==1">
						<ul class="unstyled ">
							<s:iterator value="answers">
								<li style="">
								<input  type="radio"  name="radio">
								<label  class="T_edit_min"  ><s:property value="answer"/></label>
								</li>
							</s:iterator>
						</ul>
						</s:if> 
						<s:if test="question.type==2">
						<ul class="unstyled ">
							<s:iterator value="answers">
							<li style="">
							<input  type="checkbox"  name="radio">
							<label  class="T_edit_min"  ><s:property value="answer"/></label>
							</li>
							</s:iterator>
						</ul>
						</s:if> 
						<s:if test="question.type==3">
							<ul class="unstyled">
								<li style="overflow:inherit">
								<div  class="option_Fill">
								<textarea class="" cols="40" rows="5" type="text"></textarea>
								</div>
								</li>
							</ul>
						</s:if> 
						<s:if test="question.type==4">
							<ul class="unstyled ">
								<s:iterator value="answers">
								<li style="">
								<input  type="checkbox"  name="radio">
								<label  class="T_edit_min"  ><s:property value="answer"/></label>
								</li>
								</s:iterator>
							</ul>
						</s:if> 
				</s:if> 
				<div class="operationH">
					<a onclick="removeFun('<s:property value="question.id"/>');" class="l-btn"   href="javascript:;">delete</a>
				</div>
			</div>
		</li>
		
	</s:iterator>

	</ul>
	</div>
</body>
</html>
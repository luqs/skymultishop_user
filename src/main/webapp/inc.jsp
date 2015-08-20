<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="common/taglib_include.jsp"%>
<%@ include file="const.jsp"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${ctx}/js/namespace.js" charset="utf-8"></script>
<script type="text/javascript">

com.sirius.skymall.user.contextPath = '${ctx}';
com.sirius.skymall.user.basePath = '${basePath}';
com.sirius.skymall.user.version = '${version}';
com.sirius.skymall.user.pixel_0 = '${ctx}/style/images/pixel_0.gif';//0像素的背景，一般用于占位
com.sirius.skymall.user.dbmanagementServer = '${dbmanagementserver}';
</script>

<%-- 引入my97日期时间控件 --%>
<script type="text/javascript" src="${ctx}/jslib/My97DatePicker4.8Beta3/My97DatePicker/WdatePicker.js" charset="utf-8"></script>



<%-- 引入jQuery --%>
<c:if test="${isIE}">
<script src='${ctx}/jslib/jquery-1.9.1.js' type='text/javascript' charset='utf-8'></script>
</c:if>
<c:if test="${!isIE}">
<script src='${ctx}/jslib/jquery-2.0.3.js' type='text/javascript' charset='utf-8'></script>
</c:if>
<%-- 引入jquery扩展 --%>
<script src="${ctx}/jslib/syExtJquery.js?version=${version}" type="text/javascript" charset="utf-8"></script>

<%-- 引入Highcharts --%>
<script src="${ctx}/jslib/Highcharts-3.0.6/js/highcharts.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/jslib/Highcharts-3.0.6/js/modules/exporting.js" type="text/javascript" charset="utf-8"></script>
<%-- 引入Highcharts扩展 --%>
<script src="${ctx}/jslib/syExtHighcharts.js?version=${version}" type="text/javascript" charset="utf-8"></script>

<%-- 引入plupload --%>
<script type="text/javascript" src="${ctx}/jslib/plupload-2.0.0/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/jslib/plupload-2.0.0/js/i18n/zh_CN.js"></script>

<%-- 引入EasyUI --%>
<link id="easyuiTheme" rel="stylesheet" href="${ctx}/jslib/jquery-easyui-1.3.4/themes/bootstrap/easyui.css" type="text/css">
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.4/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-1.3.4/locale/easyui-lang-en.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/jslib/jquery.jdirk.js" charset="utf-8"></script>
<%-- 引入EasyUI Portal插件 --%>
<link rel="stylesheet" href="${ctx}/jslib/jquery-easyui-portal/portal.css" type="text/css">
<script type="text/javascript" src="${ctx}/jslib/jquery-easyui-portal/jquery.portal.js" charset="utf-8"></script>
<%-- 引入easyui扩展 --%>
<script src="${ctx}/jslib/syExtEasyUI.js?version=${version}" type="text/javascript" charset="utf-8"></script>

<%-- 引入扩展图标 --%>
<link rel="stylesheet" href="${ctx}/style/syExtIcon.css?version=${version}" type="text/css">

<%-- 引入自定义样式 --%>
<link rel="stylesheet" href="${ctx}/style/syExtCss.css?version=${version}" type="text/css">

<%-- 引入javascript扩展 --%>
<script src="${ctx}/jslib/syExtJavascript.js?version=${version}" type="text/javascript" charset="utf-8"></script>

<%-- 引入ueditor控件 --%>
<script src="${ctx}/jslib/jeasyui.extensions.js" type="text/javascript" charset="utf-8"></script>
<link href="${ctx}/jslib/ueditor1_4_3-utf8-net/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8">window.UEDITOR_HOME_URL = '${ctx}/jslib/ueditor1_4_3-utf8-net/';</script>
<script src="${ctx}/jslib/ueditor1_4_3-utf8-net/ueditor.config.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/jslib/ueditor1_4_3-utf8-net/ueditor.all.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/jslib/ueditor1_4_3-utf8-net/lang/en/en.js" type="text/javascript" charset="utf-8"></script>
<script src="${ctx}/jslib/jquery.ueditor.js" type="text/javascript" charset="utf-8"></script>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="sessionInfoDiv" style="position: absolute; right: 10px; top: 5px;">
	<s:text name="message.welcome.title" />，${loginName}
	
</div>
<div style="position: absolute; right: 0px; bottom: 0px;">
	<a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_pfMenu',iconCls:'ext-icon-rainbow'"><s:text name="message.header.changeskin" /></a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_kzmbMenu',iconCls:'ext-icon-cog'"><s:text name="message.header.console" /></a> <a href="javascript:void(0);" class="easyui-menubutton" data-options="menu:'#layout_north_zxMenu',iconCls:'ext-icon-disconnect'"><s:text name="message.header.signout" /></a>
</div>
<div id="layout_north_pfMenu" style="width: 120px; display: none;">
	<div onclick="com.sirius.skymall.user.changeTheme('default');" title="default">default</div>
	<div onclick="com.sirius.skymall.user.changeTheme('gray');" title="gray">gray</div>
	<div onclick="com.sirius.skymall.user.changeTheme('metro');" title="metro">metro</div>
	<div onclick="com.sirius.skymall.user.changeTheme('bootstrap');" title="bootstrap">bootstrap</div>
	<div onclick="com.sirius.skymall.user.changeTheme('black');" title="black">black</div>
	<div class="menu-sep"></div>
	<div onclick="com.sirius.skymall.user.changeTheme('metro-blue');" title="metro-blue">metro-blue</div>
	<div onclick="com.sirius.skymall.user.changeTheme('metro-gray');" title="metro-gray">metro-gray</div>
	<div onclick="com.sirius.skymall.user.changeTheme('metro-green');" title="metro-green">metro-green</div>
	<div onclick="com.sirius.skymall.user.changeTheme('metro-orange');" title="metro-orange">metro-orange</div>
	<div onclick="com.sirius.skymall.user.changeTheme('metro-red');" title="metro-red">metro-red</div>
</div>
<div id="layout_north_kzmbMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-user_edit'" onclick="$('#passwordDialog').dialog('open');"><s:text name="message.main.modifypwd" /></div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-user'" onclick="showMyInfoFun();"><s:text name="message.header.my" /></div>
</div>
<div id="layout_north_zxMenu" style="width: 100px; display: none;">
	<div data-options="iconCls:'ext-icon-lock'" onclick="lockWindowFun();"><s:text name="message.header.lockwin" /></div>
	<div class="menu-sep"></div>
	<div data-options="iconCls:'ext-icon-door_out'" onclick="logoutFun();"><s:text name="message.header.signout" /></div>
</div>
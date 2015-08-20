<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

var const_message_reg_title='<s:text name="message.reg.title" />';
var const_message_login_title='<s:text name="message.login.title" />';
var const_message_login_info='<s:text name="message.login.info" />';
var const_message_login_field_loginname='<s:text name="message.login.field.loginname" />';
var const_message_login_field_name='<s:text name="message.login.field.name" />';
var const_message_login_field_createdatetime='<s:text name="message.login.field.createdatetime" />';
var const_message_login_field_modifydatetime='<s:text name="message.login.field.modifydatetime" />';
var const_message_role_addroleinfo='<s:text name="message.role.addroleinfo" />';
var const_message_role_viewroleinfo='<s:text name="message.role.viewroleinfo" />';
var const_message_role_editroleinfo='<s:text name="message.role.editroleinfo" />';
var const_message_tool_add='<s:text name="message.tool.add" />';
var const_message_tool_edit='<s:text name="message.tool.edit" />';
var const_message_role_rolegrant='<s:text name="message.role.rolegrant" />';
var const_message_tool_grant='<s:text name="message.tool.grant" />';
var const_message_role_warning='<s:text name="message.role.warning" />';
var const_message_role_confirmtodelete='<s:text name="message.role.confirmtodelete" />';
var const_message_role_form_description='<s:text name="message.role.form.description" />';
var const_message_role_form_order='<s:text name="message.role.form.order" />';
var const_message_role_operation='<s:text name="message.role.operation" />';
var const_message_role_loading='<s:text name="message.role.loading" />';
var const_message_role_form_rolename='<s:text name="message.role.form.rolename" />';
var const_message_publicno_confirmappoint='<s:text name="message.publicno.confirmappoint" />';
var const_message_publicno_revert='<s:text name="message.publicno.confirmrevert" />';
var const_message_publicno_editpublicinfo='<s:text name="message.publicno.editpublicinfo" />';
var const_message_role_form_id='<s:text name="message.role.form.id" />';
var const_message_publicno_email='<s:text name="message.publicno.email" />';
var const_message_publicno_phone='<s:text name="message.publicno.phone" />';
var const_message_publicno_boatcard='<s:text name="message.publicno.boatcard" />';
var const_message_publicno_signature='<s:text name="message.publicno.signature" />';
var const_message_publicno_usertype='<s:text name="message.publicno.usertype" />';
var const_message_publicno_serviceaccount='<s:text name="message.publicno.serviceaccount" />';
var const_message_publicno_publicaccount='<s:text name="message.publicno.publicaccount" />';
var const_message_publicno_commonuser='<s:text name="message.publicno.commonuser" />';
var const_message_publicno_businessuser='<s:text name="message.publicno.businessuser" />';
var const_message_publicno_shopwaiter='<s:text name="message.publicno.shopwaiter" />';
var const_message_publicno_deliver='<s:text name="message.publicno.deliver" />';
var const_message_userinfo_age='<s:text name="message.userinfo.age" />';
var const_message_userinfo_sex='<s:text name="message.userinfo.sex" />';
var const_message_user_createdate='<s:text name="message.user.createdate" />';
var const_message_sensitiveword_sensitiveword='<s:text name="message.sensitiveword.sensitiveword" />';
var const_message_common_info='<s:text name="message.common.info" />';
var const_message_tool_refresh='<s:text name="message.tool.refresh" />';
var const_message_tool_close='<s:text name="message.tool.close" />';
var const_message_user_adduserinfo='<s:text name="message.user.adduserinfo" />';
var const_message_user_viewuserinfo='<s:text name="message.user.viewuserinfo" />';
var const_message_user_edituserinfo='<s:text name="message.user.edituserinfo" />';
var const_message_userinfo_male='<s:text name="message.userinfo.male" />';
var const_message_userinfo_female='<s:text name="message.userinfo.female" />';
var const_message_user_photo='<s:text name="message.user.photo" />';
var const_message_survey_form_id = '<s:text name="message.survey.form.id" />';
var const_message_survey_form_title = '<s:text name="message.survey.form.title" />';
var const_message_survey_link = '<s:text name="message.common.link" />';
var const_message_survey_icon = '<s:text name="message.common.icon" />';
var const_message_survey_visible = '<s:text name="message.common.visible" />';
var const_message_survey_invisible = '<s:text name="message.common.invisible" />';
var const_message_survey_showindex = '<s:text name="message.common.showindex" />';
//=icon
var const_message_survey_form_description = '<s:text name="message.survey.form.description" />';
var const_message_survey_form_createdate = '<s:text name="message.survey.form.createdate" />';
var const_message_survey_addquestion='<s:text name="message.survey.question.addquestion" />';
var const_message_feedback_add_feedback='<s:text name="message.feedback.add.feedback" />';
var const_message_feedback_view_feedback='<s:text name="message.feedback.view.feedback" />';
var const_message_feedback_edit_feedback='<s:text name="message.feedback.edit.feedback" />';
var const_message_feedback_field_nickname='<s:text name="message.feedback.field.nickname" />';
var const_message_feedback_field_phone='<s:text name="message.feedback.field.phone" />';
var const_message_feedback_field_email='<s:text name="message.feedback.field.email" />';
var const_message_feedback_field_qq='<s:text name="message.feedback.field.qq" />';
var const_message_feedback_field_title='<s:text name="message.feedback.field.title" />';
var const_message_feedback_field_content='<s:text name="message.feedback.field.content" />';

var const_message_feedback_alert_phone='<s:text name="message.feedback.alert.phone" />';
var const_message_feedback_alert_content='<s:text name="message.feedback.alert.content" />';
var const_message_feedback_alert_email='<s:text name="message.feedback.alert.email" />';
var const_message_feedback_alert_nick='<s:text name="message.feedback.alert.nick" />';
var const_message_feedback_alert_qq='<s:text name="message.feedback.alert.qq" />';
var const_message_userinfo_realname = '<s:text name="message.userinfo.realname" />';
var const_message_resetpwd = '<s:text name="message.userinfo.resetpwd" />';

</script>



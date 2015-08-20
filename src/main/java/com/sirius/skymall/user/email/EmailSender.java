package com.sirius.skymall.user.email;

import java.util.Date;
import java.util.List;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 邮件发送器。
 */
public class EmailSender implements EmailConstant {

	// smtp服务器
	private String host;

	// 发送者名字
	private String from;

	// 登录服务器用户名
	private String name;

	// 密码
	private String password;

	//
	private boolean auth;

	// 邮箱内容字符集
	private String charset;

	// 是否开启调试
	private boolean debug;

	// 邮箱内容的格式：是否为Html格式
	private boolean isHtmlMail;

	private HtmlEmail htmlEmail;

	/**
	 * 无参构造函数，默认内容为纯文本格式邮件
	 */
	public EmailSender() {
		this(false);
	}

	/**
	 * 
	 * @param isHtmlMail
	 *            邮件类型，true表示Html邮件，false表示纯文本邮件
	 */
	public EmailSender(boolean isHtmlMail) {
		this.isHtmlMail = isHtmlMail;
		initProps();
	}

	/**
	 * 初始化属性
	 */
	protected void initProps() {
		host = EmailPropertyReader.get(MAIL_SMTP_HOST);
		from = EmailPropertyReader.get(MAIL_SMTP_FROM);
		name = EmailPropertyReader.get(MAIL_SMTP_NAME);
		password = EmailPropertyReader.get(MAIL_SMTP_PASSWORD);
		auth = "true".equals(EmailPropertyReader.get(MAIL_SMTP_AUTH));
		charset = EmailPropertyReader.get(MAIL_SMTP_CHARSET);
		debug = "true".equals(EmailPropertyReader.get(MAIL_SMTP_DEBUG));

		htmlEmail = new HtmlEmail();
		htmlEmail.setAuthentication(name, password);
		htmlEmail.setHostName(host);
		htmlEmail.setCharset(charset);

		try {
			htmlEmail.setFrom(from);
		} catch (EmailException e) {
			e.printStackTrace();
		}
		// 是否开启调试
		htmlEmail.setDebug(debug);
	}

	/**
	 * 校验配置属性
	 */
	protected void validateProps() {
		if (EmailUtils.isEmpty(host)) {
			throw new IllegalArgumentException("邮箱服务器mail.smtp.host不能为空！");
		}

		if (EmailUtils.isEmpty(from)) {
			throw new IllegalArgumentException("邮箱服务器mail.smtp.from不能为空！");
		}

		if (EmailUtils.isEmpty(name)) {
			throw new IllegalArgumentException("发送人姓名mail.smtp.name不能为空！");
		}

		if (EmailUtils.isEmpty(password)) {
			throw new IllegalArgumentException("发送人密码mail.smtp.password不能为空！");
		}

	}

	/**
	 * 发送邮件。
	 * 
	 * @param toList
	 *            收件人列表
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件正文
	 * @throws EmailException
	 */
	public void sendEmail(List<String> toList, String subject, String content)
			throws EmailException {
		sendEmail(toList, null, null, subject, content, null);
	}

	/**
	 * 发送邮件。
	 * 
	 * @param toList
	 *            收件人列表
	 * @param ccList
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件正文
	 * @throws EmailException
	 */
	public void sendEmail(List<String> toList, List<String> ccList,
			String subject, String content) throws EmailException {
		sendEmail(toList, ccList, null, subject, content, null);
	}

	public void sendEmail(List<String> toList, String subject, String content,
			List<EmailAttachment> attachmentList) throws EmailException {
		sendEmail(toList, null, null, subject, content, attachmentList);
	}

	/**
	 * 发送邮件。
	 * 
	 * @param toList
	 *            收件人列表
	 * @param ccList
	 *            抄送人列表
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件正文
	 * @param attachmentList
	 *            附件列表
	 * @throws EmailException
	 */
	public void sendEmail(List<String> toList, List<String> ccList,
			String subject, String content, List<EmailAttachment> attachmentList)
			throws EmailException {
		sendEmail(toList, ccList, null, subject, content, attachmentList);
	}

	/**
	 * 发送邮件。
	 * 
	 * @param toList
	 *            收件人列表
	 * @param ccList
	 *            抄送人列表
	 * @param bccList
	 *            暗送人列表
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件正文
	 * @throws EmailException
	 */
	public void sendEmail(List<String> toList, List<String> ccList,
			List<String> bccList, String subject, String content)
			throws EmailException {
		sendEmail(toList, ccList, bccList, subject, content, null);
	}

	/**
	 * 发送邮件。
	 * 
	 * @param toList
	 *            收件人列表
	 * @param ccList
	 *            抄送人列表
	 * @param bccList
	 *            暗送人列表
	 * @param subject
	 *            邮件主题
	 * @param content
	 *            邮件正文
	 * @param attachmentList
	 *            附件列表
	 * @throws EmailException
	 */
	public void sendEmail(List<String> toList, List<String> ccList,
			List<String> bccList, String subject, String content,
			List<EmailAttachment> attachmentList) throws EmailException {
		// 收件人不能为空
		if (EmailUtils.isEmpty(toList)) {
			throw new IllegalArgumentException("收件人不能为空！");
		}
		// 添加收件人
		for (String to : toList) {
			htmlEmail.addTo(to);
		}

		// 添加抄送人
		if (EmailUtils.notEmpty(ccList)) {
			for (String cc : ccList) {
				htmlEmail.addCc(cc);
			}
		}

		// 添加暗送人
		if (EmailUtils.notEmpty(bccList)) {
			for (String bcc : bccList) {
				htmlEmail.addBcc(bcc);
			}
		}

		// 设置主题
		htmlEmail.setSubject(subject);

		// 设置正文
		if (isHtmlMail) {
			htmlEmail.setMsg(content);
		} else {
			htmlEmail.setTextMsg(content);
		}
		htmlEmail.setSentDate(new Date());

		// 添加附件
		if (EmailUtils.notEmpty(attachmentList)) {
			for (EmailAttachment attachment : attachmentList) {
				EmailUtils.processEmaillAttachment(attachment);
				htmlEmail.attach(attachment);
			}
		}
		htmlEmail.send();

	}

}

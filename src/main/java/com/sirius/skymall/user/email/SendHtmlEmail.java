package com.sirius.skymall.user.email;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;

/**
 * 发送Html格式，不带附件的邮件。
 * 
 */
public class SendHtmlEmail {

	/**
	 * @param args
	 * @throws EmailException
	 */
	public static void main(String[] args) throws EmailException {
		testHtmlEmail();

	}

	public static void testHtmlEmail() throws EmailException {
		EmailSender emailSender = new EmailSender(true);

		// 接收者邮箱
		String subject = "测试邮件发送";
		String content = "<a href=\"http://www.baidu.com\">请点击这里验证以后即可找回密码</a>";

		String to = "768371019@qq.com";
		List<String> list = new ArrayList<String>();
		list.add(to);
		emailSender.sendEmail(list, subject, content);
		System.out.println("邮件发送完毕====");

	}
}

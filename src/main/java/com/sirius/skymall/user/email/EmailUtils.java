package com.sirius.skymall.user.email;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;

/**
 * 邮件工具类，包含了一些工具方法。
 */
public final class EmailUtils {

	private EmailUtils() {
		super();
	}

	/**
	 * 判断list是否为空
	 * 
	 * @param list
	 *            集合列表
	 * @return
	 */
	public static boolean isEmpty(List<?> list) {
		if (list == null || list.size() == 0) {
			return true;
		}
		return false;
	}

	public static boolean notEmpty(List<?> list) {
		return !isEmpty(list);
	}

	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * 
	 * @return <code>true</code> if the String is empty or null
	 * 
	 * @since Commons Lang v2.1, svn 240418
	 */
	static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 * 
	 * @param str
	 *            the String to check, may be null
	 * 
	 * @return <code>true</code> if the String is not empty and not null
	 * 
	 * @since Commons Lang v2.1, svn 240418
	 */
	public static boolean isNotEmpty(String str) {
		return (str != null) && (str.length() > 0);
	}

	/**
	 * 使用MimeUtility对文本进行编码
	 * 
	 * @param text
	 *            需要使用MimeUtility进行编码的内容
	 * @return 编码之后的内容
	 */
	public static String encodeText(String text) {
		String result = text;

		try {
			result = MimeUtility.encodeText(text);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * 
	 * 注意事项：
	 * 
	 * 1.附件路径必须设置，可以含有中文字符；附件名称是可选的，可以含有中文字符。
	 * 
	 * 2.以下情况，附件名称会出现乱码。
	 * 
	 * 2.1附件路径含有中文，附件名称没有设置。 2.2附件名称含有中文。
	 * 
	 * 最佳实践：附件路径必须设置，附件名称必须设置，附件名称需要编码。
	 * 
	 * @throws EmailException
	 */
	public static void processEmaillAttachment(EmailAttachment attachment) {
		if (attachment == null) {
			throw new IllegalArgumentException("attachment is null");
		}

		String path = attachment.getPath();
		String name = attachment.getName();
		System.out.println(attachment + "," + path + "," + name);

		if (!EmailUtils.isEmpty(path)) {
			if (EmailUtils.isEmpty(name)) {
				name = getNameByPath(path);
				attachment.setName(EmailUtils.encodeText(name));
			} else {
				// 如果不设置附件名称，会自动解析，比如"C:/东风标致/东风标致.xml"会产生"东风标致.xml"这个文件名
				attachment.setName(EmailUtils.encodeText(name));
			}
		} else {
			throw new IllegalArgumentException("附件的path不能为null！");
		}
	}

	public static void processEmaillAttachment(
			List<EmailAttachment> attachmentList) {
		if (EmailUtils.isEmpty(attachmentList)) {
			return;
		}
		for (EmailAttachment attachment : attachmentList) {
			processEmaillAttachment(attachment);
		}

	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public static String getNameByPath(String path) {
		File file = new File(path);
		return file.getName();
	}
}

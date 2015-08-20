package com.sirius.skymall.user.ws.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class ResourcesUtil  {
	
	
	
	/**
	 */
	public static String getText(Object... args) {
		if (args == null || args.length == 0) {
			throw new RuntimeException(
					"bad parameters of ResourceUtil.getText.");
		}
		String key = args[0].toString();
		
		Locale locale = null;
		String bundleName = null;
		ResourceBundle rb = ResourceBundle.getBundle(bundleName, locale);
		String msg = rb.getString(key);
		if (msg == null) {
			throw new RuntimeException("bundle key not found:" + key);
		}
			Object[] fmargs = new Object[args.length - 1];
		return MessageFormat.format(msg, fmargs);
	}


}

package com.sirius.skymall.user.email;
import java.util.Properties;

public class EmailPropertyReader {

	private static final String EMAIL_PROPERTIES = "email.properties";

	private static Properties props;

	static {
		props = CommonPropertyReader.loadProps(EMAIL_PROPERTIES);
	}

	public static String get(String key) {
		return (String) props.get(key);
	}

}

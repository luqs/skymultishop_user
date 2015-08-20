package com.sirius.skymall.user.util.base;

import java.util.ResourceBundle;

/**
 * 项目常量工具类
 * 
 * @author zzpeng
 * 
 */
public class ApplicationResourcesUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("ApplicationResources_en_US");

	public static final String get(String key) {
		return bundle.getString(key);
	}

}

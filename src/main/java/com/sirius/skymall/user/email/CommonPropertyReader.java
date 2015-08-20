package com.sirius.skymall.user.email;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * 读取某个路径下的配置文件，email.properties配置文件和本类在同一个路径下，或者在classpath路径下。
 */
public class CommonPropertyReader {

	private String filePath;

	public CommonPropertyReader() {

	}

	public CommonPropertyReader(String filePath) {
		this.filePath = filePath;
	}

	public Properties loadProperties(String filePath) {
		this.filePath = filePath;
		return loadProperties();
	}

	public Properties loadProperties() {
		Properties props = new Properties();
		InputStream in = null;
		try {
			// 当前文件所在目录
			in = CommonPropertyReader.class.getResourceAsStream(filePath);

			if (in == null) {
				// classpath路径下
				in = CommonPropertyReader.class.getClassLoader()
						.getResourceAsStream(filePath);
			}
			props.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}

	public static Properties loadProps(String filePath) {
		CommonPropertyReader commonPropertyReader = new CommonPropertyReader();
		return commonPropertyReader.loadProperties(filePath);
	}
}
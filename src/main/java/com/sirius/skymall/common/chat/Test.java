package com.sirius.skymall.common.chat;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;

public class Test {
	public static void main(String[] args) throws Exception {

//		InputStream in = new BufferedInputStream(new FileInputStream("E:/SMP_1.0/workspace/openfire-plugins/skyseas_plugins/src/plugins/chatlogs/src/redis.properties"));
		InputStream in = Test.class.getResourceAsStream("/redis.properties");
		Properties props = new Properties();
		props.load(in);
		JedisManager jedisUtil = JedisManager.getInstance(props);
		Jedis jedis = jedisUtil.getJedis();

		String key = "chatlogs";
		int i=0;
		while (true) {
			i++;
			// block invoke
			List<String> msgs = jedis.brpop(0, key);
			System.out.println(i);
			if (msgs == null)
				continue;
			String jobMsg = msgs.get(1);
			System.out.println(jobMsg);

		}
	}
	
//	public static void main(String[] args) throws IOException {
//		InputStream in = Test.class.getResourceAsStream("/redis.properties");
//		Properties props = new Properties();
//		props.load(in);
//		System.out.println(props.getProperty("redis.pool.maxActive"));
//	}

}

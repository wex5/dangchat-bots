package com.justep.dangchat.bots.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * 配置文件读取类
 * 
 * @author Lining
 * @date 2016/9/2
 *
 */
public class ConfigFileReader {

	/**
	 * 得到配置文件指定键的值
	 * 
	 * @param file
	 *            配置文件名称
	 * @param key
	 *            配置项名称
	 * @return 配置项值
	 */
	public static String getValue(String file, String key) {
		try {
			Configuration config = new XMLConfiguration(ConfigFileReader.class.getResource(file));
			return config.getString(key);
		} catch (ConfigurationException ce) {
			return null;
		}
	}

}

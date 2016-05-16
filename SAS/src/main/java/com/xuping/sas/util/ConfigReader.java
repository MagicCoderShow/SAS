package com.xuping.sas.util;


import java.util.ResourceBundle;

/**
 * 将需要配置的数据读入内存
 * @author
 *
 */
public class ConfigReader {
	private static ResourceBundle  config = ResourceBundle.getBundle("config");
	public static String getValue(String name){
		return config.getString(name);
	}
}
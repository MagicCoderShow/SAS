package com.xuping.sas.util;

import com.google.common.base.Strings;


/**
 * 模糊查询工具类
 * @author hs
 *
 */
public class Escape {
	/**
	 * sql模糊查询特殊字符处理
	 * @param str
	 * @return
	 */
	public static String escapeStr(String str) {
		if (!Strings.isNullOrEmpty(str)) {

			if (str.contains("%")) {
				str = str.replaceAll("%", "\\%");
			}

			if (str.contains("_")) {
				str = str.replaceAll("_", "\\_");
			}
			return str.trim();
		}
		return str;
	}
}
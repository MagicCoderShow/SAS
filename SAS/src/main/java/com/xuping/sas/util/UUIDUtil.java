package com.xuping.sas.util;

import java.util.UUID;

/**
 * @description	
 * @author	xuping
 * @date	2016年5月17日
 *
 */
public class UUIDUtil {
	/**
	 *获取uuid
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("\\-", "");
	}
}

package com.xuping.sas.util;

import org.apache.commons.codec.binary.Base64;


/**
 * 常规Base64加密解密实用工具类
 * 说明：
 * 作者：何杨(heyang78@gmail.com)
 * 创建时间：2010-11-29 上午07:52:01
 * 修改时间：2010-11-29 上午07:52:01
 */
public class Base64SecurityUtil{
    /**
     * 得到Base64加密后的字符串
     * 
     * 说明：
     * @param originalString
     * @return
     * 创建时间：2010-11-29 上午07:53:30
     */
    public static String getEncryptString(String originalString){
        byte[] arr = Base64.encodeBase64(originalString.getBytes(), true);
        return new String(arr);
    }
    /**
     * 加密
     * @param bytes
     * @return
     */
    public static String getEncryptString(byte[] bytes){
        byte[] arr = Base64.encodeBase64(bytes, true);
        return new String(arr);
    }
    /**
     * 得到Base64解密后的字符串
     * 
     * 说明：
     * @param encryptString
     * @return
     * 创建时间：2010-11-29 上午07:56:02
     */
    public static String getDecryptString(String encryptString){
        byte[] arr = Base64.decodeBase64(encryptString.getBytes());
        return new String(arr);
    }
    public static void main(String[] args) {
		System.out.println(getDecryptString(getEncryptString("116°23′17,39°54′27")));
	}
}
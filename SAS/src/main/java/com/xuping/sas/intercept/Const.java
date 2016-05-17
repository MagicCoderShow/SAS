package com.xuping.sas.intercept;

/**
 * 所有的常量
 * @author  wuxuping
 */
public class Const {
	public static final String COOKIE_USER_NAME="user_cookie";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_LIMITS = "sessionLimits";
	public static final String SESSION_MENUS = "sessionMenus";
	public static final String SESSION_RESOPE = "sessionResope";
	public static final String SESSION_RESOPEMAP = "sessionResopemap";
	public static final String SESSION_ROLE = "sessionRole";
	public static final String NO_INTERCEPTOR_PATH = ".*/((upload/*.xlsx)|(user/logout)|(vimage)|(login_timeout)|(login)|(getrsakey))";	//不对匹配该值的访问路径拦截（正则）後台
	public static final String STATIC_PATH=".*((jpeg)|(css)|(js)|(jpg)|(png)|(gif)|(woff)|(swf)|(docx)|(doc)|(pdf))";
	public static final String STATIC_FILE_PATH=".*((jpeg)|(jpg)|(png)|(gif)|(woff)|(swf)|(docx)|(doc)|(pdf)|(apk))";
}

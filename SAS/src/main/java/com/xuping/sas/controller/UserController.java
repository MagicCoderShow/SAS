package com.xuping.sas.controller;

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.xuping.sas.model.User;
import com.xuping.sas.service.UserService;
import com.xuping.sas.util.Base64SecurityUtil;
import com.xuping.sas.util.MD5Helper;
import com.xuping.sas.util.RSAUtils;

/**
 * @description
 * @author xuping
 * @date 2016年4月17日
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * 登陆
	 * @param data 用户名，密码，验证码加密串
	 * @param session
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(String data,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("home/index");
		//解密
		data = Base64SecurityUtil.getEncryptString(data);
		//获取私钥
		PrivateKey privatekey = (PrivateKey) session.getAttribute("private");
		String decryptData = "";
		data = data.replaceAll(" ", "");
		Map<String,String> params = new HashMap<String,String>();
		if(privatekey != null){
			decryptData = RSAUtils.jsDecryptString(data,privatekey);
			params = (Map<String, String>) JSON.parse(data);
		}
		params = (Map<String, String>) JSON.parse(data);
		String loginname = params.get("loginname");
		String password =params.get("password");;
		String code = params.get("code");
		//验证参数
		if(null!=loginname&&!Strings.isNullOrEmpty(password)&&Strings.isNullOrEmpty(code)){
			//查询用户
			User user = userService.selectByLoginName(loginname);
			if(null!=user&&null!=user.getLoginPwd()){
				password = MD5Helper.getMD5ofStr(password);
				//判断密码是否正确
				if(password.equals(user.getLoginPwd())){
					//密码正确
					user.setLoginPwd(null);
					session.setAttribute("sessionUser",user);
				}else{
					//用户名密码错误
					mv.setViewName("home/login");
					mv.addObject("warn", "用户名密码错误");
				}
			}
		}else{
			mv.setViewName("home/login");
		}
		return mv;
	}
}

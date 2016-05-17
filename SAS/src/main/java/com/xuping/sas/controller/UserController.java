package com.xuping.sas.controller;

import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	 * 获取公钥
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="getrsakey",method=RequestMethod.POST)
	public Map<String, String> getrsakey(HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
		HashMap<String, Object> rsmap = RSAUtils.getKeys();   
		//生成公钥和私钥    
        RSAPublicKey publicKey = (RSAPublicKey) rsmap.get("public");    
        RSAPrivateKey privateKey = (RSAPrivateKey) rsmap.get("private");  
        session.setAttribute("privateKey", privateKey);//私钥保存在session中，用于解密  
        //公钥信息保存在页面，用于加密  
        String publicKeyExponent = publicKey.getPublicExponent().toString(16);  
        String publicKeyModulus = publicKey.getModulus().toString(16);  

		Map<String, String> map = new HashMap<String, String>();
		map.put("modulus", publicKeyModulus);
		map.put("exponent", publicKeyExponent);
		return map;
	}
	
	/**
	 * 登陆
	 * @param data 用户名，密码，验证码加密串
	 * @param session
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="signup",method=RequestMethod.POST)
	public ModelAndView signup(String data,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("home/index");
		//解密
//		data = Base64SecurityUtil.getEncryptString(data);
		//获取私钥
		RSAPrivateKey privateKey = (RSAPrivateKey)session.getAttribute("privateKey"); 
		String decryptData = "";
		data = data.replaceAll(" ", "");
		Map<String,String> params = new HashMap<String,String>();
		if(privateKey != null){
			try {
				decryptData = RSAUtils.decryptByPrivateKey(data,privateKey);
				StringBuffer buffer = new StringBuffer(decryptData);
				decryptData = buffer.reverse().toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		params = (Map<String, String>) JSON.parse(decryptData);
		String loginname = params.get("loginname");
		String email = params.get("email");
		String password =params.get("password");
		String repassword =params.get("repassword");
		String validatecode = params.get("validatecode");
		//验证参数
		if(!Strings.isNullOrEmpty(validatecode)){
			//验证验证码是否正确
			if(validatecode.equals(session.getAttribute("validatecode"))){
				if(!Strings.isNullOrEmpty(password)&&!Strings.isNullOrEmpty(repassword)&&password.equals(repassword)){
					if(!Strings.isNullOrEmpty(loginname)){
						if(!Strings.isNullOrEmpty(email)){
							//所有参数正确，校验用户名是否存在
							User tempUser = userService.selectByLoginName(loginname);
							if(null==tempUser){
								//校验邮箱是否存在
								tempUser = userService.selectByEmail(email);
								if(null==tempUser){
									User user = new User();
									user.setLoginName(loginname);
									user.setUserEmail(email);
									user.setLoginPassword(MD5Helper.getMD5ofStr(password));
									userService.insertSelective(user);
								}else{
									//邮箱已经注册
								}
							}else{
								//用户名已经存在
							}
							
						}else{
							//密码不能为空
						}
					}else{
						//用户名不能为空
					}
				}else{
					//密码与重复密码不一致
				}
			}else{
				//验证码不正确
			}
		}else{
			mv.setViewName("home/login");
		}
		return mv;
	}
	
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
//		data = Base64SecurityUtil.getEncryptString(data);
		//获取私钥
		RSAPrivateKey privateKey = (RSAPrivateKey)session.getAttribute("privateKey"); 
		String decryptData = "";
		data = data.replaceAll(" ", "");
		Map<String,String> params = new HashMap<String,String>();
		if(privateKey != null){
			try {
				decryptData = RSAUtils.decryptByPrivateKey(data,privateKey);
				StringBuffer buffer = new StringBuffer(decryptData);
				decryptData = buffer.reverse().toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		params = (Map<String, String>) JSON.parse(decryptData);
		String loginname = params.get("loginname");
		String password =params.get("password");;
		String code = params.get("code");
		//验证参数
		if(null!=loginname&&!Strings.isNullOrEmpty(password)&&Strings.isNullOrEmpty(code)){
			//查询用户
			User user = userService.selectByLoginName(loginname);
			if(null!=user&&null!=user.getLoginPassword()){
				password = MD5Helper.getMD5ofStr(password);
				//判断密码是否正确
				if(password.equals(user.getLoginPassword())){
					//密码正确
					user.setLoginPassword(null);
					session.setAttribute("sessionUser",user);
				}else{
					//用户名密码错误
					mv.setViewName("home/signin");
					mv.addObject("warn", "用户名或密码错误");
				}
			}else{
				//用户名密码错误
				mv.setViewName("home/signin");
				mv.addObject("warn", "用户名或密码错误");
			}
		}else{
			mv.setViewName("home/signin");
		}
		return mv;
	}
}

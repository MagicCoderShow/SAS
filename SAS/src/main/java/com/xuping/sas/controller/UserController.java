package com.xuping.sas.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;
import com.xuping.sas.model.User;
import com.xuping.sas.service.UserService;

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
	 * @param user
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ModelAndView login(User user,HttpSession session,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("home/index");
		if(null!=user&&!Strings.isNullOrEmpty(user.getLoginName())&&Strings.isNullOrEmpty(user.getLoginPwd())){
			
		}else{
			mv.setViewName("home/login");
		}
		return mv;
	}
}

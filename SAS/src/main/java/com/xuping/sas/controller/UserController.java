package com.xuping.sas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

}

package com.xuping.sas.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuping.sas.mapper.UserMapper;
import com.xuping.sas.model.User;
import com.xuping.sas.service.UserService;
import com.xuping.sas.util.UUIDUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xuping.sas.service.UserService#deleteByPrimaryKey(java.lang.Integer)
	 */
	public int deleteByPrimaryKey(String id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xuping.sas.service.UserService#insert(com.xuping.sas.model.User)
	 */
	public String insert(User record) {
		record.setId(UUIDUtil.getUUID());
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		userMapper.insert(record);
		return record.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xuping.sas.service.UserService#insertSelective(com.xuping.sas.model.
	 * User)
	 */
	public String insertSelective(User record) {
		record.setId(UUIDUtil.getUUID());
		record.setCreateDate(new Date());
		record.setUpdateDate(new Date());
		userMapper.insertSelective(record);
		return record.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xuping.sas.service.UserService#selectByPrimaryKey(java.lang.Integer)
	 */
	public User selectByPrimaryKey(String id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xuping.sas.service.UserService#updateByPrimaryKeySelective(com.xuping
	 * .sas.model.User)
	 */
	public int updateByPrimaryKeySelective(User record) {
		record.setUpdateDate(new Date());
		return userMapper.updateByPrimaryKey(record);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xuping.sas.service.UserService#updateByPrimaryKey(com.xuping.sas.
	 * model.User)
	 */
	public int updateByPrimaryKey(User record) {
		record.setUpdateDate(new Date());
		return userMapper.updateByPrimaryKey(record);
	}

	/* (non-Javadoc)
	 * @see com.xuping.sas.service.UserService#selectByLoginName(java.lang.String)
	 */
	@Override
	public User selectByLoginName(String loginname) {
		return userMapper.selectByLoginName(loginname);
	}

	/* (non-Javadoc)
	 * @see com.xuping.sas.service.UserService#selectByEmail(java.lang.String)
	 */
	@Override
	public User selectByEmail(String email) {
		return userMapper.selectByEmail(email);
	}

}

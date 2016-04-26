package com.xuping.sas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xuping.sas.mapper.UserMapper;
import com.xuping.sas.model.User;
import com.xuping.sas.service.UserService;

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
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.xuping.sas.service.UserService#insert(com.xuping.sas.model.User)
	 */
	public int insert(User record) {
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
	public int insertSelective(User record) {
		userMapper.insertSelective(record);
		return record.getId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.xuping.sas.service.UserService#selectByPrimaryKey(java.lang.Integer)
	 */
	public User selectByPrimaryKey(Integer id) {
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
		return userMapper.updateByPrimaryKey(record);
	}

}

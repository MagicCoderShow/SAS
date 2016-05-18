package com.xuping.sas.service;

import com.xuping.sas.model.User;

/**
 * @description	
 * @author	xuping
 * @date	2016年4月17日
 *
 */

public interface UserService {
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 插入
	 * @param record
	 * @return
	 */
	String insert(User record);

    /**
     * 插入部分字段
     * @param record
     * @return
     */
    String insertSelective(User record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(String id);

    /**
     * 更新部分字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新所有字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);
    
    /**
     * 根据用户名查询用户
     * @param loginname
     * @return
     */
    User selectByLoginName(String loginname);
    
    /**
     * 根据email查询用户
     * @param email
     * @return
     */
    User selectByEmail(String email);
}

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
	int deleteByPrimaryKey(Integer id);

	/**
	 * 插入
	 * @param record
	 * @return
	 */
    int insert(User record);

    /**
     * 插入部分字段
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

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
}

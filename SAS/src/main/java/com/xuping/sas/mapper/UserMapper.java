package com.xuping.sas.mapper;

import org.apache.ibatis.annotations.Param;

import com.xuping.sas.model.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    String insert(User record);

    String insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    /**
     * 根据用户名查询用户
     * @param loginname
     * @return
     */
    User selectByLoginName(@Param("loginname")String loginname);
    
    /**
     * 根据email查询用户
     * @param email
     * @return
     */
    User selectByEmail(@Param("email")String email);
}
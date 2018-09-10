package org.hyj.dao;

import org.apache.ibatis.annotations.Param;
import org.hyj.bean.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByName(String UserName);

    User selectUserByNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    User selectUserPasswordByUserId(Integer userId);

}
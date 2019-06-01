package com.lgc.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.lgc.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * @program: javaConfig-ssm
 * @description: user dao
 * @author: lgc
 * @create: 2019-06-01 17:52
 **/
public interface UserDao extends BaseMapper<User> {
    User findById(@Param("userCode") String code, @Param("userPwd") String pwd);

    Integer updateUser(@Param("userName")String userName, @Param("userCode")String userCode);
}

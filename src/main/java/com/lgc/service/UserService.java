package com.lgc.service;

import com.lgc.model.User;

/**
 * @program: javaConfig-ssm
 * @description: user interface
 * @author: lgc
 * @create: 2019-06-01 17:50
 **/
public interface UserService {
    User findById(String code, String pwd);

    Integer updateUser(String userName, String userCode);

}


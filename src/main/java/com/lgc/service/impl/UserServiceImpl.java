package com.lgc.service.impl;

import com.lgc.dao.UserDao;
import com.lgc.model.User;
import com.lgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: javaConfig-ssm
 * @description: user impl
 * @author: lgc
 * @create: 2019-06-01 17:51
 **/
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findById(String code, String pwd) {
        return null;
    }

    @Override
    public Integer updateUser(String userName, String userCode) {
        Integer num = userDao.updateUser(userName, userCode);
        return num;
    }
}

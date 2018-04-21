package com.wei.service;

import com.wei.domain.User;

public interface UserService {

    //登录方法
    User getUserByCodePassword(User user);

    //保存用户
    void saveUser(User user);
}

package com.wei.dao;

import com.wei.domain.User;

public interface UserDao {

    //根据登录名称查询user对象
    User getByUserCode(String usercode);

    //保存用户
    void save(User user);
}

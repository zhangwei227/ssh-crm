package com.wei.service.impl;

import com.wei.dao.UserDao;
import com.wei.domain.User;
import com.wei.service.UserService;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = true)
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Override
    public User getUserByCodePassword(User user) {
        //1.根据登录名称查询登录用户
        User existU = userDao.getByUserCode(user.getUser_code());
        //2.判断用户是否存在,不存在抛出异常,提示用户不存在
        if (existU == null) {
            throw new RuntimeException("用户名不存在!");
        }
        //3.判断用户密码是否存在,不存在抛出异常,提示密码错误
        if (!existU.getUser_password().equals(user.getUser_password())) {
            throw new RuntimeException("密码错误!");
        }
        //4.返回查询到的用户对象
        return user;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED, readOnly = false)
    public void saveUser(User user) {
        userDao.save(user);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}

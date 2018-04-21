package com.wei.test;

import com.wei.dao.UserDao;
import com.wei.domain.User;
import com.wei.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//测试hibernate框架
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class HibernateTest {

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Test
    public void fun1() {
        Configuration conf = new Configuration().configure();

        SessionFactory sessionFactory = conf.buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        User user = new User();
        user.setUser_code("tom");
        user.setUser_name("汤姆");
        user.setUser_password("1234");

        session.save(user);
        transaction.commit();

        session.close();

        sessionFactory.close();
    }

    @Test
    public void fun2() {

        Session session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();


        User user = new User();
        user.setUser_code("tom");
        user.setUser_name("汤姆1122221112323231");
        user.setUser_password("1234");

        session.save(user);

        session.close();

        sessionFactory.close();
    }

    @Resource(name = "userDao")
    private UserDao userDao;

    @Test
    public void fun3() {
        User user = userDao.getByUserCode("tom");
        System.out.println(user);
    }

    @Resource(name = "userService")
    private UserService userService;

    @Test
    public void fun4() {
        User user = new User();
        user.setUser_code("tom");
        user.setUser_name("111汤姆23333");
        user.setUser_password("1234");
        userService.saveUser(user);
        System.out.println(user);
    }
}

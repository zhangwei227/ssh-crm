package com.wei.dao.impl;

import com.wei.dao.UserDao;
import com.wei.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

    private HibernateTemplate hibernateTemplate;

    @Override
    public User getByUserCode(final String usercode) {
        //HQl
        /*return getHibernateTemplate().execute(new HibernateCallback<User>() {
            @Override
            public User doInHibernate(Session session) throws HibernateException {
                String hql = "from User where user_code = ?";
                Query query = session.createQuery(hql);
                query.setParameter(0, usercode);
                User user = (User) query.uniqueResult();
                return user;
            }
        });*/
        //Criteria
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("user_code", usercode));
        List<User> list = (List<User>) getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void save(User user) {
        getHibernateTemplate().save(user);
    }
}

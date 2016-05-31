package com.sample.login.dao.impl;

import org.apache.ibatis.session.SqlSession;

import com.sample.common.base.DaoBase;
import com.sample.common.model.User;
import com.sample.login.dao.UserDao;

public class UserDaoImpl extends DaoBase implements UserDao {
    public User getUser(String name) {
    	SqlSession session = sqlSessionFactory.openSession();
    	User user = session.selectOne("user.mybatis.selectAll", name);
    	session.close();
    	return user;
    }
}

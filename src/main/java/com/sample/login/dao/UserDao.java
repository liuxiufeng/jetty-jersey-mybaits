package com.sample.login.dao;

import org.apache.ibatis.session.SqlSession;

import com.sample.common.base.DaoBase;
import com.sample.common.model.User;

public class UserDao extends DaoBase {
    public User getUser(String name) {
    	SqlSession session = sqlSessionFactory.openSession();
    	User user = session.selectOne("user.mybatis.selectAll", name);
    	return user;
    }
}

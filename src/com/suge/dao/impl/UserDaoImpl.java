package com.suge.dao.impl;

import com.suge.bean.User;
import com.suge.dao.UserDao;

import java.sql.Connection;

/**
 * @author suu
 * @create 2020-12-03 17:27
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        //按名字查找
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";

//        User user = queryForOne(User.class, sql, username);
//        return user;

          return queryForOne(User.class, sql, username);

    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {

        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username,password);
    }

    @Override
    public int saveUser(User user) {

        String sql = "insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update( sql, user.getUsername(),user.getPassword(),user.getEmail());
    }
}

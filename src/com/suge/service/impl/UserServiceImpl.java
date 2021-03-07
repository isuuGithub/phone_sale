package com.suge.service.impl;

import com.suge.bean.User;
import com.suge.dao.UserDao;
import com.suge.dao.impl.UserDaoImpl;
import com.suge.service.UserService;

/**
 * @author suu
 * @create 2020-12-04 21:47
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        //对象非空 说明对象已存在，用户名不可用
        if (user!=null){
//          System.out.println("用户已存在");
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);

    }

    @Override
    public User login(User user) {

        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());

    }
}

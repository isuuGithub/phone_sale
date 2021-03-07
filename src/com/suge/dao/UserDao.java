package com.suge.dao;

import com.suge.bean.User;

/**
 *
 * 用户类(注册页面)操作数据库的相关方法
 * @author suu
 * @create 2020-12-03 17:19
 */
public interface UserDao {


    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 如果返回 null,说明没有这个用户。如果返回一个对象值说明 用户已存在
     */
    public User queryUserByUsername(String username);

    /**
     * 根据 用户名和密码查询用户信息(用于登录)
     * @param username
     * @param password
     * @return 如果返回 null,说明用户名或密码错误,如果返回一个对象值说明用户已存在，即账户密码正确
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息（用于注册）
     * @param user
     * @return 返回-1 表示操作失败，其他是 sql 语句影响的行数
     */
    public int saveUser(User user);
}

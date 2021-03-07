package com.suge.service;

import com.suge.bean.User;

/**
 * @author suu
 * @create 2020-12-04 21:44
 */
public interface UserService {

    /**
     * 检查 用户名是否可用
     * @param username
     * @return 返回 true 表示用户名已存在(即不可用)，返回 false 表示用户名可用
     */
    public boolean existsUsername(String username);

    /**
     * 注册用户
     * （验证用户名可用后）
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return 如果返回 null，说明登录失败，返回有值，是登录成功
     */
    public User login(User user);


}

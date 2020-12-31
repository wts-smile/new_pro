package com.seven.service;

import com.seven.pojo.User;

public interface UserService {
    User getUserById(int id);

    User getUserByName(String name);

    User login(String name, String pass);

    // 0-ok 1-用户名已存在 2-两次密码不一样 3-角色赋值不正确
    int register(String name, String pass, String repPass, String detail, int role);
}

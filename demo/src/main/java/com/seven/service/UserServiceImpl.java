package com.seven.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.dao.UserDao;
import com.seven.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.selectById(id);
    }

    @Override
    public User getUserByName(String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<User> users = userDao.selectByMap(map);
        if (users.size() == 0) return null;

        return users.iterator().next();
    }

    @Override
    public User login(String name, String pass) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<User> users = userDao.selectByMap(map);
        if (users.size() == 0) return null;
        User u = users.iterator().next();
        if (pass.equals(u.getPass())) {
            return u;
        }
        return null;
    }

    @Override
    public User recharge(String name, double money) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        User u = userDao.selectOne(wrapper);
        if (u == null) {
            return u;
        }
        u.setBalance(u.getBalance() + money);
        userDao.updateById(u);
        return u;
    }

    @Override
    public User changePass(String name, String pass, String newPass) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", name);
        User u = userDao.selectOne(wrapper);
        if (u == null) {
            return u;
        }
        u.setPass(newPass);
        userDao.updateById(u);
        return u;
    }

    @Override
    public int register(String name, String pass, String repPass, String detail, int role) {
        if (!pass.equals(repPass)) return 2;
        if (role != 1 && role != 2 && role != 3) return 3;
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<User> users = userDao.selectByMap(map);
        if (users.size() != 0) return 1;
        User u = new User();
        u.setName(name);
        u.setPass(pass);
        u.setDetail(detail);
        u.setRole(role);
        u.setBalance(0.0);
        userDao.insert(u);
        return 0;
    }
}

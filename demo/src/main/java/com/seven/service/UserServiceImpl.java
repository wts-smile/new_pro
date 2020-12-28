package com.seven.service;

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
    public User getUserByName(String name) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        List<User> users = userDao.selectByMap(map);
        if (users.size() == 0) return null;

        User u = users.iterator().next();
        return u;
    }
}

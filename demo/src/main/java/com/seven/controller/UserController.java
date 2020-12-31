package com.seven.controller;

import com.seven.dao.UserDao;
import com.seven.models.reqobjs.LoginReq;
import com.seven.models.reqobjs.RegisterReq;
import com.seven.models.resobjs.LoginRes;
import com.seven.models.resobjs.RegisterRes;
import com.seven.pojo.User;
import com.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public LoginRes userLogin(LoginReq req) {
        String name = req.getUsername();
        String pass = req.getPassword();
        User u = userService.login(name, pass);
        LoginRes res = new LoginRes();
        if (u != null) {
            res.setErrno(0);
            res.setUser(u);
        } else {
            res.setErrno(1);
            res.setUser(u);
        }
        return res;
    }

    @PostMapping("/user/register")
    public RegisterRes userRegister(RegisterReq req) {
        String name = req.getUsername();
        String pass = req.getPassword();
        String checkpass = req.getCheckpass();
        String detail = req.getDetail();
        int role = req.getRole();
        int n = userService.register(name, pass, checkpass, detail, role);
        RegisterRes res = new RegisterRes();
        res.setErrno(n);
        if (n == 0) {
            res.setError("");
        } else if (n == 1) {
            res.setError("用户名已存在");
        }else if (n == 1) {
            res.setError("两次密码不一样");
        }else {
            res.setError("角色值不正确");
        }
        return res;
    }

    @GetMapping("/user/get")
    public User userGet(@RequestParam("uid") int uid) {
        //Integer.parseInt(uid)
        return userService.getUserById(uid);
    }
}

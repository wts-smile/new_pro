package com.seven.controller;

import com.seven.models.reqobjs.LoginReq;
import com.seven.models.reqobjs.RegisterReq;
import com.seven.models.resobjs.LoginRes;
import com.seven.models.resobjs.RegisterRes;
import com.seven.models.resobjs.UserWithAddrRes;
import com.seven.pojo.Address;
import com.seven.pojo.User;
import com.seven.service.AddrService;
import com.seven.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AddrService addrService;

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

    @PostMapping("/user/changepass")
    public UserWithAddrRes userChangepass(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("newpass") String newpass) {
        User u = userService.changePass(username, password, newpass);
        List<Address> list = addrService.listAllAddrByUser(username);
        UserWithAddrRes res = new UserWithAddrRes();
        res.setAddr(list);
        res.setBalance(u.getBalance());
        res.setDetail(u.getDetail());
        res.setBalance(u.getBalance());
        res.setId(u.getId());
        res.setName(u.getName());
        res.setPass(u.getPass());
        res.setRole(u.getRole());
        return res;
    }

    @GetMapping("/user/get")
    public UserWithAddrRes userGet(@RequestParam("uid") int uid) {
        //Integer.parseInt(uid)
        User u = userService.getUserById(uid);
        List<Address> list = addrService.listAllAddrByUser(u.getName());
        UserWithAddrRes res = new UserWithAddrRes();
        res.setAddr(list);
        res.setBalance(u.getBalance());
        res.setDetail(u.getDetail());
        res.setBalance(u.getBalance());
        res.setId(u.getId());
        res.setName(u.getName());
        res.setPass(u.getPass());
        res.setRole(u.getRole());
        return res;
    }

    @PostMapping("/user/recharge")
    public UserWithAddrRes userRecharge(@RequestParam("username") String username, @RequestParam("money") double money){
        User u = userService.recharge(username, money);
        List<Address> list = addrService.listAllAddrByUser(u.getName());
        UserWithAddrRes res = new UserWithAddrRes();
        res.setAddr(list);
        res.setBalance(u.getBalance());
        res.setDetail(u.getDetail());
        res.setBalance(u.getBalance());
        res.setId(u.getId());
        res.setName(u.getName());
        res.setPass(u.getPass());
        res.setRole(u.getRole());
        return res;
    }
}

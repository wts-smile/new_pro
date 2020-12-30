package com.seven.models.resobjs;

import com.seven.pojo.User;

public class LoginRes {
    private int errno;
    private User user;

    public LoginRes() {
    }

    public LoginRes(int errno, User user) {
        this.errno = errno;
        this.user = user;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginRes{" +
                "errno=" + errno +
                ", user=" + user +
                '}';
    }
}

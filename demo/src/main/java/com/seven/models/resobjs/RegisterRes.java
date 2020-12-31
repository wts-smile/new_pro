package com.seven.models.resobjs;

public class RegisterRes {
    private int errno;
    private String error;

    public RegisterRes() {
    }

    public RegisterRes(int errno, String error) {
        this.errno = errno;
        this.error = error;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "LoginRes{" +
                "errno=" + errno +
                ", error='" + error + '\'' +
                '}';
    }
}

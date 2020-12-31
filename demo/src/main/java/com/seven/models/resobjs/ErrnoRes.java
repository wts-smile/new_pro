package com.seven.models.resobjs;

public class ErrnoRes {
    private int errno;

    public ErrnoRes() {
    }

    public ErrnoRes(int errno) {
        this.errno = errno;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    @Override
    public String toString() {
        return "ErrnoRes{" +
                "errno=" + errno +
                '}';
    }
}

package com.seven.models.reqobjs;

public class RegisterReq {
    private String username;
    private String password;
    private String checkpass;
    private String detail;
    private int role;

    public RegisterReq() {
    }

    public RegisterReq(String username, String password, String checkpass, String detail, int role) {
        this.username = username;
        this.password = password;
        this.checkpass = checkpass;
        this.detail = detail;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckpass() {
        return checkpass;
    }

    public void setCheckpass(String checkpass) {
        this.checkpass = checkpass;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "RegisterReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", checkpass='" + checkpass + '\'' +
                ", detail='" + detail + '\'' +
                ", role=" + role +
                '}';
    }
}

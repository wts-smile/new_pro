package com.seven.models.resobjs;

import com.seven.pojo.Address;

import java.util.List;

public class UserWithAddrRes {
    private int id;
    private String name;
    private String pass;
    private String detail;
    private Double balance;
    private int role;
    private List<Address> addr;

    public UserWithAddrRes() {
    }

    public UserWithAddrRes(int id, String name, String pass, String detail, Double balance, int role, List<Address> addr) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.detail = detail;
        this.balance = balance;
        this.role = role;
        this.addr = addr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Address> getAddr() {
        return addr;
    }

    public void setAddr(List<Address> addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "UserWithAddrRes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", detail='" + detail + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                ", addressList=" + addr +
                '}';
    }
}

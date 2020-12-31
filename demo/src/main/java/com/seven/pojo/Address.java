package com.seven.pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Address implements Serializable {
    private int id;
    private String name;
    private String addr;
    private String isdefault;

    public Address() { }

    public Address(int id, String name, String addr, String isdefault) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.isdefault = isdefault;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", addr='" + addr + '\'' +
                ", isdefault='" + isdefault + '\'' +
                '}';
    }
}

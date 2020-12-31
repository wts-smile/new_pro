package com.seven.pojo;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Order implements Serializable {
    private int id;
    private String username;
    private int productid;
    private int status;
    private String starttime;
    private String endtime;

    public Order() {
    }

    public Order(int id, String username, int productid, int status, String starttime, String endtime) {
        this.id = id;
        this.username = username;
        this.productid = productid;
        this.status = status;
        this.starttime = starttime;
        this.endtime = endtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", productid=" + productid +
                ", status=" + status +
                ", starttime='" + starttime + '\'' +
                ", endtime='" + endtime + '\'' +
                '}';
    }
}

package com.seven.pojo;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Cart implements Serializable {
    private int id;
    private String username;
    private int productid;
    private int count;

    public Cart() {
    }

    public Cart(int id, String username, int productid, int count) {
        this.id = id;
        this.username = username;
        this.productid = productid;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", productid=" + productid +
                ", count=" + count +
                '}';
    }
}

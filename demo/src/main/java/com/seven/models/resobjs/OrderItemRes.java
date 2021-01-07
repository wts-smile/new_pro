package com.seven.models.resobjs;

import com.seven.pojo.Product;

public class OrderItemRes {
    private int orderId;
    private String username;
    private Product product;
    private int num;
    private int status;
    private String startTime;
    private String endTime;

    public OrderItemRes() {
    }

    public OrderItemRes(int orderId, String username, Product product, int num, int status, String startTime, String endTime) {
        this.orderId = orderId;
        this.username = username;
        this.product = product;
        this.num = num;
        this.status = status;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "OrderItemRes{" +
                "orderId=" + orderId +
                ", username='" + username + '\'' +
                ", product=" + product +
                ", num=" + num +
                ", status=" + status +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}

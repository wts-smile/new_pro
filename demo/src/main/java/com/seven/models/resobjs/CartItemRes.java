package com.seven.models.resobjs;

import com.seven.pojo.Product;

public class CartItemRes {
    private int id;
    private int count;
    private Product product;

    public CartItemRes() {
    }

    public CartItemRes(int id, int count, Product product) {
        this.id = id;
        this.count = count;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartItemRes{" +
                "cartItemId=" + id +
                ", count=" + count +
                ", product=" + product +
                '}';
    }
}

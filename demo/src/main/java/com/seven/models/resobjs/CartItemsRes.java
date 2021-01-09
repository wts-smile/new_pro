package com.seven.models.resobjs;

import com.seven.pojo.Product;

public class CartItemsRes {
    private int num;
    private Product product;

    public CartItemsRes() {
    }

    public CartItemsRes(int num, Product product) {
        this.num = num;
        this.product = product;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CartItemsRes{" +
                "num=" + num +
                ", product=" + product +
                '}';
    }
}

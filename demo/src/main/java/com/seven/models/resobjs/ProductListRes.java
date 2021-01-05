package com.seven.models.resobjs;

import com.seven.pojo.Product;

import java.util.List;

public class ProductListRes {
    private int total;
    private int pageNum;
    private int pageSize;
    List<Product> productList;

    public ProductListRes() {
    }

    public ProductListRes(int total, int pageNum, int pageSize, List<Product> productList) {
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.productList = productList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "ProductListRes{" +
                "total=" + total +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", productList=" + productList +
                '}';
    }
}

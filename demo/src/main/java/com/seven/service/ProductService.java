package com.seven.service;

import com.seven.models.resobjs.ProductListRes;
import com.seven.pojo.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(int id);

    List<Product> getProductList(int pageNum, int pageSize);

    ProductListRes getProductListByType(String type, int pageNum, int pageSize);

    List<String> getAllTypes();

    int getProductNum();
}

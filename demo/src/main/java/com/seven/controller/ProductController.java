package com.seven.controller;

import com.seven.models.resobjs.ErrnoRes;
import com.seven.models.resobjs.ProductListRes;
import com.seven.pojo.Product;
import com.seven.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/product/list")
    public ProductListRes productList(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        List<Product> list = productService.getProductList(pageNum, pageSize);
        int total = productService.getProductNum();
        return new ProductListRes(total, pageNum, pageSize, list);
    }

    @PostMapping("/product/list/type")
    public ProductListRes productListType(@RequestParam("productType") String pType, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        return productService.getProductListByType(pType, pageNum, pageSize);
    }

    @GetMapping("/product/get")
    public Product productGet(@RequestParam("productId") int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/product/types")
    public List<String> productTypes() {
        return productService.getAllTypes();
    }

    @PostMapping("/product/onstore")
    public Product productOnStore(@RequestParam("productId") int productId, @RequestParam("stat") int stat) {
        return productService.ProductOnStore(productId, stat);
    }
}

package com.seven.controller;

import com.seven.models.resobjs.ErrnoRes;
import com.seven.models.resobjs.ProductListRes;
import com.seven.pojo.Product;
import com.seven.service.ProductService;
import com.seven.utils.ProgramUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    private ProgramUtils programUtils;

    @PostMapping("/product/list")
    @CrossOrigin
    public ProductListRes productList(@RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        List<Product> list = productService.getProductList(pageNum, pageSize);
        int total = productService.getProductNum();
        return new ProductListRes(total, pageNum, pageSize, list);
    }

    @PostMapping("/product/list/type")
    @CrossOrigin
    public ProductListRes productListType(@RequestParam("productType") String pType, @RequestParam("pageSize") int pageSize, @RequestParam("pageNum") int pageNum) {
        return productService.getProductListByType(pType, pageNum, pageSize);
    }

    @GetMapping("/product/get")
    @CrossOrigin
    public Product productGet(@RequestParam("productId") int productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/product/types")
    @CrossOrigin
    public List<String> productTypes() {
        return productService.getAllTypes();
    }

    @PostMapping("/product/onstore")
    @CrossOrigin
    public Product productOnStore(@RequestParam("productId") int productId, @RequestParam("stat") int stat) {
        return productService.ProductOnStore(productId, stat);
    }

    @GetMapping("/product/list/keyword")
    @CrossOrigin
    public List<Product> productListKeyword(@RequestParam("keyword") String keyword) {
        return productService.getProductByKeyWord(keyword);
    }

    @PostMapping("/product/add")
    @CrossOrigin
    public Product productAdd(@RequestParam("productName") String productName, @RequestParam("productPtype") String productPtype, @RequestParam("productPic") MultipartFile productPic,@RequestParam("productDetail") String productDetail,@RequestParam("productPrice") double productPrice)
    {
        System.out.println(productName);
        System.out.println(productPtype);
        String filename = "";
        if(!productPic.isEmpty())
        {
            String path = programUtils.getPicFolder();
            filename = UUID.randomUUID().toString()+productPic.getOriginalFilename();
            File file = new File(path, filename);
            if(!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            try
            {productPic.transferTo(new File(path+"/"+filename));}
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        Product p = new Product();
        p.setSalecnt(0);
        p.setDetail(productDetail);
        p.setName(productName);
        p.setPtype(productPtype);
        p.setPic(filename);
        p.setPrice(productPrice);
        p.setStatus(1);
        return productService.addProduct(p);
    }
}

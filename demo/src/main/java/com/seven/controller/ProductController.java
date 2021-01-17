package com.seven.controller;

import com.seven.models.resobjs.ErrnoRes;
import com.seven.models.resobjs.ProductListRes;
import com.seven.pojo.Product;
import com.seven.service.ProductService;
import com.seven.utils.ProgramUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/product/add")
    public Product productAdd(@RequestParam("productName") String productName, @RequestParam("productPtype") String productPtype, @RequestParam("productPic") MultipartFile productPic,@RequestParam("productDetail") String productDetail,@RequestParam("productPrice") double productPrice,@RequestParam("productStatus") int productStatus,@RequestParam("productSalecnt") int productSalecnt)
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
        p.setSalecnt(productSalecnt);
        p.setDetail(productDetail);
        p.setName(productName);
        p.setPtype(productPtype);
        p.setPic(filename);
        p.setPrice(productPrice);
        p.setStatus(productStatus);
        return productService.addProduct(p);
    }
}

package com.seven.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seven.dao.ProductDao;
import com.seven.models.resobjs.ProductListRes;
import com.seven.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;

    @Override
    public Product getProductById(int id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> getProductList(int pageNum, int pageSize) {
        Page<Product> page = new Page<>(pageNum, pageSize);

        IPage<Product> iPage = productDao.selectPage(page, null);
        return iPage.getRecords();
    }

    @Override
    public ProductListRes getProductListByType(String type, int pageNum, int pageSize) {
        QueryWrapper<Product> wrapper = new QueryWrapper<>();
        wrapper.eq("ptype", type);
        Page<Product> page = new Page<>(pageNum, pageSize);

        IPage<Product> iPage = productDao.selectPage(page, wrapper);
        return new ProductListRes((int) iPage.getTotal(), pageNum, pageSize,iPage.getRecords());
    }

    @Override
    public List<String> getAllTypes() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.groupBy("ptype");
        List<Product> pList = productDao.selectList(queryWrapper);
        List<String> list = new ArrayList<>();
        for (Product l : pList) {
            list.add(l.getPtype());
        }
        return list;
    }

    @Override
    public int getProductNum() {
        return productDao.selectCount(null);
    }
}

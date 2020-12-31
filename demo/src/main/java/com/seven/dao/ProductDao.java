package com.seven.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.pojo.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends BaseMapper<Product> {
}

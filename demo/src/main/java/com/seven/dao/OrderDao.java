package com.seven.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seven.pojo.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao extends BaseMapper<Order> {
}

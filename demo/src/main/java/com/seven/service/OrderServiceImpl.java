package com.seven.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.dao.OrderDao;
import com.seven.dao.ProductDao;
import com.seven.models.resobjs.OrderItemRes;
import com.seven.pojo.Order;
import com.seven.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Override
    public List<OrderItemRes> getAllOrders(String username) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<Order> oList = orderDao.selectList(wrapper);
        List<OrderItemRes> res = new ArrayList<>();
        for (Order o : oList) {
            OrderItemRes item = new OrderItemRes();
            item.setEndTime(o.getEndtime());
            item.setStartTime(o.getStarttime());
            item.setOrderId(o.getId());
            item.setNum(o.getNum());
            item.setProduct(productDao.selectById(o.getProductid()));
            item.setStatus(o.getStatus());
            item.setUsername(username);
            res.add(item);
        }
        return res;
    }
}

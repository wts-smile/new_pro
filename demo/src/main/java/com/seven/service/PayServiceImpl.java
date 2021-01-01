package com.seven.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.seven.dao.OrderDao;
import com.seven.dao.ProductDao;
import com.seven.dao.UserDao;
import com.seven.pojo.Cart;
import com.seven.pojo.Order;
import com.seven.pojo.Product;
import com.seven.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PayServiceImpl implements PayService {
    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    OrderDao orderDao;

    @Override
    public boolean payForProduct(String username, int productId, int cnt) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", username);
        User u = userDao.selectOne(wrapper);
        Product p = productDao.selectById(productId);
        // 检查是否付得起
        if (u.getBalance() < p.getPrice() * cnt) return false;
        // 生成订单
        Order o = new Order();
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        o.setStarttime(df.format(date));
        o.setEndtime(df.format(date));
        o.setNum(cnt);
        o.setStatus(2);
        o.setUsername(username);
        o.setProductid(productId);
        orderDao.insert(o);
        // 扣钱
        u.setBalance(u.getBalance() - p.getPrice() * cnt);
        userDao.updateById(u);
        return false;
    }
}

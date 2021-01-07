package com.seven.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.seven.dao.CartDao;
import com.seven.dao.OrderDao;
import com.seven.dao.ProductDao;
import com.seven.dao.UserDao;
import com.seven.models.resobjs.CartItemRes;
import com.seven.models.resobjs.CartItemsRes;
import com.seven.pojo.Cart;
import com.seven.pojo.Order;
import com.seven.pojo.Product;
import com.seven.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;

    @Autowired
    OrderDao orderDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    @Override
    public void addProductToUserCart(String username, int proudctId, int count) {
        Cart cart = new Cart();
        cart.setUsername(username);
        cart.setProductid(proudctId);
        cart.setCount(count);
        cartDao.insert(cart);
        Order order = new Order();
        order.setUsername(username);
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        order.setStarttime(df.format(date));
        order.setProductid(proudctId);
        order.setStatus(1);
        orderDao.insert(order);
    }

    @Override
    public List<CartItemRes> getCartByUser(String username) {
        QueryWrapper<Cart> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        List<Cart> cList = cartDao.selectList(wrapper);
        List<CartItemRes> list = new ArrayList<>();
        for (Cart c : cList) {
            CartItemRes cartItemRes = new CartItemRes();
            cartItemRes.setCount(c.getCount());
            cartItemRes.setId(c.getId());
            cartItemRes.setProduct(productDao.selectById(c.getProductid()));
            list.add(cartItemRes);
        }
        return list;
    }

    @Override
    public void deleteProductInCart(String username, String idList) {
        String[] strs = idList.split(",");
        for (String s : strs) {
            int productId = Integer.parseInt(s);
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productid", productId).eq("username", username);
            List<Cart> cartItems = cartDao.selectList(queryWrapper);
            if (cartItems.size() == 0) continue;
            Cart cart = cartItems.iterator().next();
            UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
            wrapper.eq("username", username).eq("productid", productId).eq("status", 1);
            Order order = new Order();
            order.setProductid(productId);
            order.setStatus(3);
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            order.setEndtime(df.format(date));
            orderDao.update(order, wrapper);
            cartDao.deleteById(cart.getId());
        }
    }

    @Override
    public boolean payProductInCart(String username, String idList) {
        String[] strs = idList.split(",");
        // 检查是否付得起
        List<Cart> cartItemList = new ArrayList<>();
        double allPrices = 0;
        for (String s : strs) {
            int productId = Integer.parseInt(s);
            QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("productid", productId).eq("username", username);
            List<Cart> cartItems = cartDao.selectList(queryWrapper);
            if (cartItems.size() == 0) continue;
            Cart cartItem = cartItems.iterator().next();
            cartItemList.add(cartItem);
            Product product = productDao.selectById(productId);
            allPrices += cartItem.getCount() * product.getPrice();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", username);
        List<User> users = userDao.selectByMap(map);
        if (users.size() == 0) return false;
        if (users.iterator().next().getBalance() < allPrices) return false;
        // 更改订单状态 + 删购物车
        for (Cart cartItem : cartItemList) {
            UpdateWrapper<Order> wrapper = new UpdateWrapper<>();
            wrapper.eq("username", username).eq("productid", cartItem.getProductid()).eq("status", 1);
            Order order = new Order();
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
            order.setEndtime(df.format(date));
            order.setStatus(2);
            order.setProductid(cartItem.getProductid());
            orderDao.update(order, wrapper);
            cartDao.deleteById(cartItem.getId());
        }
        // 付款
        User uu = users.get(0);
        uu.setBalance(uu.getBalance() - allPrices);
        userDao.updateById(uu);
        return true;
    }
}

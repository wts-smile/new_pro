package com.seven.service;

import com.seven.models.resobjs.OrderItemRes;

import java.util.List;

public interface OrderService {
    List<OrderItemRes> getAllOrders(String username);
}

package com.seven.controller;

import com.seven.models.resobjs.OrderItemRes;
import com.seven.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/order/list")
    @CrossOrigin
    public List<OrderItemRes> orderList(@RequestParam("username") String username) {
        return orderService.getAllOrders(username);
    }
}

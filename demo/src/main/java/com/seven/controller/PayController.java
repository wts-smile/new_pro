package com.seven.controller;

import com.seven.models.resobjs.ErrnoRes;
import com.seven.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PayController {
    @Autowired
    PayService payService;

    @PostMapping("/pay")
    public ErrnoRes doPay(@RequestParam("username") String username, @RequestParam("productId") int productId,@RequestParam("cnt") int cnt) {
        payService.payForProduct(username, productId, cnt);
        return new ErrnoRes(0);
    }
}

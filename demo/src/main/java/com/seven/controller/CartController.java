package com.seven.controller;

import com.seven.models.resobjs.CartItemRes;
import com.seven.models.resobjs.ErrnoRes;
import com.seven.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/cart/add")
    @CrossOrigin
    public ErrnoRes cartAdd(@RequestParam("username") String username, @RequestParam("pid") int pid, @RequestParam("cnt") int cnt) {
        cartService.addProductToUserCart(username,pid,cnt);
        return new ErrnoRes(0);
    }

    @PostMapping("/cart/edit")
    @CrossOrigin
    public ErrnoRes cartEdit(@RequestParam("username") String username,@RequestParam("idList") String idList) {
        cartService.deleteProductInCart(username, idList);
        return new ErrnoRes(0);
    }

    @PostMapping("/cart/pay")
    @CrossOrigin
    public ErrnoRes cartPay(@RequestParam("username") String username,@RequestParam("idList") String idList) {
        cartService.payProductInCart(username,idList);
        return new ErrnoRes(0);
    }

    @GetMapping("/cart/list")
    @CrossOrigin
    public List<CartItemRes> cartItem(@RequestParam("username") String username) {
        return cartService.getCartByUser(username);
    }
}

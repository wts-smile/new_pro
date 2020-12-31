package com.seven.service;

import com.seven.models.resobjs.CartItemRes;
import com.seven.models.resobjs.CartItemsRes;
import com.seven.pojo.Cart;

import java.util.List;

public interface CartService {
    void addProductToUserCart(String username, int proudctId, int count);

    List<CartItemRes> getCartByUser(String username);

    void deleteProductInCart(String username, String idList);

    boolean payProductInCart(String username, String idList);
}

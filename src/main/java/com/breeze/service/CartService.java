package com.breeze.service;

import com.breeze.pojo.Cart;
import org.springframework.data.domain.Page;

public interface CartService {
    void addCart(Cart cart);

    Page<Cart> searchCart(Cart cart);
}

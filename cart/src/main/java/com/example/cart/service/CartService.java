package com.example.cart.service;

import com.example.cart.vo.CartVo;

import java.util.Optional;

public interface CartService {
    public Optional<CartVo> getCartByID(Long cartId);
    public Long saveCart(CartVo cart);
}

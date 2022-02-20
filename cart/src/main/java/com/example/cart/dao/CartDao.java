package com.example.cart.dao;

import com.example.cart.dao.entity.Cart;
import com.example.cart.vo.CartVo;

import java.util.Optional;

public interface CartDao {
  public Optional<Cart> getCartByID(Long cartId);
  public Cart saveCart(Cart cart);
  public void removeCart(Cart cart);
}

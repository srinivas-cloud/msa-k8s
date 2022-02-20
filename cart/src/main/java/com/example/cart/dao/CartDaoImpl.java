package com.example.cart.dao;

import com.example.cart.dao.entity.Cart;
import com.example.cart.dao.entity.Item;
import com.example.cart.exce.CartNotFound;
import com.example.cart.vo.CartItemVo;
import com.example.cart.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class CartDaoImpl implements CartDao{
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Optional<Cart> getCartByID(Long cartId) {
        return Optional.of(cartRepository.getById(cartId));
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void removeCart(Cart cart) {
        cartRepository.delete(cart);
    }
}

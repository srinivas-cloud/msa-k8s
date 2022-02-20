package com.example.cart.vo;

import com.example.cart.dao.entity.Item;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class CartVo {
    private Long cartId;
    private Set<CartItemVo> items;
}

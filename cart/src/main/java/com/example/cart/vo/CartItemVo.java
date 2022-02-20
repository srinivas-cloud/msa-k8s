package com.example.cart.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemVo {
    private Long itemId;
    private Integer productId;
    private Integer skuId;
    private String productName;
    private Double itemPrice;
    private Integer quantity;
}

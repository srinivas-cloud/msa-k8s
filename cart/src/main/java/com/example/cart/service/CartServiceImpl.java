package com.example.cart.service;

import com.example.cart.dao.CartDao;
import com.example.cart.dao.entity.Cart;
import com.example.cart.dao.entity.Item;
import com.example.cart.exce.CartNotFound;
import com.example.cart.vo.CartItemVo;
import com.example.cart.vo.CartVo;
import com.example.cart.vo.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@Transactional
public class CartServiceImpl implements CartService{
    @Autowired
    private CartDao cartDao;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Optional<CartVo> getCartByID(Long cartId) {
        Optional<Cart> persistedCart = cartDao.getCartByID(cartId);
        if(persistedCart.isPresent()) {
            return Optional.of(getCartVo(persistedCart));
        }
        return Optional.empty();
    }

    @Override
    public Long saveCart(CartVo cartVo) {
        Cart existingCart = null;
        Long cartId = 0000L;
        if(cartVo.getCartId() != null) {
            Optional<Cart> persistedCart = cartDao.getCartByID(cartVo.getCartId());
            if (persistedCart.isPresent()) {
                existingCart = persistedCart.get();
            }
        }
        if(existingCart != null) {
            Cart cartSaved = updateCart(cartVo, existingCart);
            if(cartSaved != null) {
                cartId = cartSaved.getId();
            }
        } else {
            if (cartVo.getItems() != null && !cartVo.getItems().isEmpty()) {
                Cart cartEntity = new Cart();
                Set<Item> items = getItems(cartVo);
                cartEntity.setItems(items);
                cartId = cartDao.saveCart(cartEntity).getId();
            }
        }
        return cartId;
    }

    private Cart updateCart(CartVo cartVo, Cart existingCart) {
        Cart cartSaved = null;
        Set<Item> extItems = existingCart.getItems();
        if(extItems != null) {
            //update qty
            //delete item
            Map<Integer, Item> existingItemsMap = new HashMap<Integer, Item>();
            for (Item item : extItems) {
                existingItemsMap.put(item.getProductId(), item);
            }

            for (CartItemVo itemVo : cartVo.getItems()) {
                if(existingItemsMap.get(itemVo.getProductId()) != null) {
                    if(itemVo.getQuantity() <= 0) {
                        extItems.remove(existingItemsMap.get(itemVo.getProductId()));
                    }
                } else {
                    extItems.add(getItem(itemVo));
                }
            }
            if(extItems.size() == 0) {
                cartDao.removeCart(existingCart);
                return cartSaved;
            } else {
                existingCart.setItems(extItems);
                cartSaved = cartDao.saveCart(existingCart);
            }
        } else {
            Set<Item> items = getItems(cartVo);
            existingCart.setItems(items);
            cartSaved = cartDao.saveCart(existingCart);
        }
        return cartSaved;
    }

    private Set<Item> getItems(CartVo cartVo) {
        Set<Item> items = new HashSet<Item>();
        for (CartItemVo itemVo : cartVo.getItems()) {
            Item item = new Item();
            item.setSkuId(itemVo.getSkuId());
            item.setQuantity(itemVo.getQuantity());
            item.setProductId(itemVo.getProductId());
            item.setProductName(itemVo.getProductName());
            Optional<Double> price = populateSkuPrice(itemVo.getSkuId());
            if(price.isPresent()) {
                item.setItemPrice(price.get());
            }
            items.add(item);
        }
        return items;
    }

    private Item getItem(CartItemVo cartItemVo) {
            Item item = new Item();
            item.setSkuId(cartItemVo.getSkuId());
            item.setQuantity(cartItemVo.getQuantity());
            item.setProductId(cartItemVo.getProductId());
            item.setProductName(cartItemVo.getProductName());
            Optional<Double> price = populateSkuPrice(cartItemVo.getSkuId());
            if(price.isPresent()) {
                item.setItemPrice(price.get());
            }
        return item;
    }

    private CartVo getCartVo(Optional<Cart> cart) {
        CartVo cartVo = new CartVo();
        cartVo.setCartId(cart.get().getId());
        Set<CartItemVo> items = new HashSet<>();
        for(Item item : cart.get().getItems()) {
            CartItemVo cartItem = new CartItemVo();
            cartItem.setSkuId(item.getSkuId());
            cartItem.setItemId(item.getId());
            cartItem.setItemPrice(item.getItemPrice());
            cartItem.setProductId(item.getProductId());
            cartItem.setQuantity(item.getQuantity());
            cartItem.setProductName(item.getProductName());
            items.add(cartItem);
        }
        cartVo.setItems(items);
        return cartVo;
    }

    private Optional<Double> populateSkuPrice(Integer skuId) {
        String url = "http://ProductCatalog/catalog/product/price/"+skuId;
        Price skuPrice = restTemplate.getForObject(url, Price.class);
        if(skuPrice != null) {
            return Optional.of(skuPrice.getPrice());
        }
        return Optional.empty();
    }

}

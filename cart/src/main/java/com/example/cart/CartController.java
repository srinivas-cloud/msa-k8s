package com.example.cart;

import com.example.cart.exce.CartNotFound;
import com.example.cart.service.CartService;
import com.example.cart.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart/{id}", method= RequestMethod.GET)
    public ResponseEntity<CartVo> findCart(@PathVariable String id){
        Optional<CartVo> cart = cartService.getCartByID(Long.parseLong(id));

        if(cart.isPresent()) {
            return new ResponseEntity<CartVo>(cart.get(), HttpStatus.OK);
        } else {
            throw new CartNotFound();
        }
    }

    @RequestMapping(value = "/addOrUpdateCart", method = RequestMethod.POST)
    public ResponseEntity<Long> addOrUpdateCart(@RequestBody CartVo cartVo) {
        Long cartId = cartService.saveCart(cartVo);
        if(cartId != null) {
            return new ResponseEntity<>(cartId, HttpStatus.OK);
        } else {
            throw new RuntimeException();
        }
    }
}

package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.CartDto;
import com.PhoneX.Backend.Service.CartService;
import com.PhoneX.Backend.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ResponseBody
@CrossOrigin
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService){
        this.cartService =cartService;
    }
    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@RequestBody Cart cart){
        return ResponseEntity.accepted().body(cartService.addToCart(cart));
    }

    @GetMapping("/getCartProduct/{id}/{productId}")
    public ResponseEntity<Boolean> getCartProduct(@PathVariable long id, @PathVariable long productId){
        return ResponseEntity.ok().body(cartService.checkProductInCart(id,productId));
    }

    @GetMapping("/getCartProducts/{id}")
    public ResponseEntity<List<Cart>> getCartProducts(@PathVariable long id){
        return ResponseEntity.ok().body(cartService.getProductsInCart(id));
    }

    @DeleteMapping("/delete/{productId}/{userId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId , @PathVariable long userId){
        return ResponseEntity.ok().body(cartService.delete(productId,userId));
    }
}

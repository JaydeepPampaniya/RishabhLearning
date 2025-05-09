package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.AddToCartDTO;
import com.PhoneX.Backend.Service.CartService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.Cart;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addToCart")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_CART + "')")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartDTO addToCartDTO) {
        return ResponseEntity.accepted().body(cartService.addToCart(addToCartDTO));
    }

    @GetMapping("/getCartProduct/{id}/{productId}")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_CART + "')")
    public ResponseEntity<Boolean> getCartProduct(@PathVariable long id, @PathVariable long productId) {
        return ResponseEntity.ok().body(cartService.checkProductInCart(id, productId));
    }

    @GetMapping("/getCartProducts/{id}")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_CART + "')")
    public ResponseEntity<List<Cart>> getCartProducts(@PathVariable long id) {
        return ResponseEntity.ok().body(cartService.getProductsInCart(id));
    }

    @DeleteMapping("/delete/{productId}/{userId}")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_CART + "')")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId, @PathVariable long userId) {
        return ResponseEntity.ok().body(cartService.delete(productId, userId));
    }
}

package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.Service.ProductService;
import com.PhoneX.Backend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.findAllProducts());
    }

    @GetMapping("/getProductImage/{id}/image")
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        byte[] imageData = productService.getImage(id);
        return ResponseEntity.ok().body(imageData);
    }
    @GetMapping("/{id}/getProduct")
    public ResponseEntity<?> getProduct(@PathVariable int id){
        return ResponseEntity.ok().body(productService.getProduct(id));
    }
}

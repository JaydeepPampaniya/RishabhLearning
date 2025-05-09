package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.Service.ProductService;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.globalException.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@ResponseBody
@RequestMapping("/admin")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(product, imageFile));
        } catch (IOException e) {
            throw new BadRequestException("Failed to upload the image.");
        }
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

    @PatchMapping("/{id}/updateProduct")
    public ResponseEntity<String> updateProduct(@RequestPart Product product,MultipartFile imageFile){
        try {
            return ResponseEntity.accepted().body(productService.updateProduct(product,imageFile));
        } catch (IOException e) {
            throw new BadRequestException("Failed to update a product");
        }
    }
    @DeleteMapping("/{deviceName}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable String deviceName){
        return ResponseEntity.accepted().body(productService.deleteProduct(deviceName));
    }
}

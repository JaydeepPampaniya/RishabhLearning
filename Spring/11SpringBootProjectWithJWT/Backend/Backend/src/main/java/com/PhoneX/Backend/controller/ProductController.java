package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.AddProductDTO;
import com.PhoneX.Backend.DTO.UpdateProductDto;
import com.PhoneX.Backend.DTO.responseDTO.ProductResponseDTO;
import com.PhoneX.Backend.Service.ProductService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.globalException.BadRequestException;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

@RestController
@ResponseBody
@RequestMapping("/products")

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProducts")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_PRODUCTS + "')")
    public ResponseEntity<Page<ProductResponseDTO>> getProducts(@RequestParam(required = false) String deviceType, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok().body(productService.findAllProducts(deviceType, pageable));
    }

    @PostMapping("/addProduct")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_PRODUCTS + "')")
    public ResponseEntity<String> addProduct(@RequestPart("imageFile") MultipartFile imageFile, @RequestPart("addProductDTO") AddProductDTO addProductDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(imageFile, addProductDTO));
        } catch (IOException e) {
            throw new BadRequestException("Failed to upload the image.");
        }
    }

    @GetMapping("/{id}/getProduct")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_PRODUCTS + "')")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    @PatchMapping("/{id}/updateProduct")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_PRODUCTS + "')")
    public ResponseEntity<String> updateProduct(@RequestPart("updateProductDto") UpdateProductDto updateProductDto, @RequestPart(("imageFile")) MultipartFile imageFile) {
        try {
            return ResponseEntity.accepted().body(productService.updateProduct(updateProductDto, imageFile));
        } catch (IOException e) {
            throw new BadRequestException("Failed to update a product");
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('" + UserPermissions.WRITE_PRODUCTS + "')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.accepted().body(productService.deleteProduct(id));
    }
}


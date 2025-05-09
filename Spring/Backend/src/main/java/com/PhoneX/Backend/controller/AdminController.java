package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.*;
import com.PhoneX.Backend.Service.AdminService;
import com.PhoneX.Backend.Service.ProductService;
import com.PhoneX.Backend.entity.Admin;
import com.PhoneX.Backend.globalException.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final ProductService productService;

    @Autowired
    public AdminController(AdminService adminService,ProductService productService){
        this.adminService = adminService;
        this.productService=productService;

    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationAdminDTO registrationAdminDTO) {
        String message = adminService.registerForAdmin(registrationAdminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

//    @PostMapping("/login")
//    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginDTO loginDTO){
//        return ResponseEntity.status(HttpStatus.OK).body(adminService.login(loginDTO));
//    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<Admin>> getAllAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdmin());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteAdmin(id));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestPart("addProductDTO") AddProductDTO addProductDTO, @RequestPart("imageFile") MultipartFile imageFile) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(addProductDTO, imageFile));
        } catch (IOException e) {
            throw new BadRequestException("Failed to upload the image.");
        }
    }



    @PatchMapping("/{id}/updateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateProduct(@RequestPart("updateProductDto") UpdateProductDto updateProductDto,@RequestPart(("imageFile")) MultipartFile imageFile){
        try {
            return ResponseEntity.accepted().body(productService.updateProduct(updateProductDto,imageFile));
        } catch (IOException e) {
            throw new BadRequestException("Failed to update a product");
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        return ResponseEntity.accepted().body(productService.deleteProduct(id));
    }
}

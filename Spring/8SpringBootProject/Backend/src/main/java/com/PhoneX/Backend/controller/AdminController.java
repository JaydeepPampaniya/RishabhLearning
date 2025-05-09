package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.LoginDTO;
import com.PhoneX.Backend.DTO.RegistrationAdminDTO;
import com.PhoneX.Backend.Service.AdminService;
import com.PhoneX.Backend.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationAdminDTO registrationAdminDTO) {
        String message = adminService.registerForAdmin(registrationAdminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody LoginDTO loginDTO){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.login(loginDTO));
    }

    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<Admin>> getAllAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdmin());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteAdmin(id));
    }
}

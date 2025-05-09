package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.*;
import com.PhoneX.Backend.Service.AdminService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationAdminDTO registrationAdminDTO) {
        String message = adminService.registerForAdmin(registrationAdminDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/getAllAdmin")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ALL_USERS + "')")
    public ResponseEntity<Page<Admin>> getAllAdmin(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdmin(pageable));
    }
}

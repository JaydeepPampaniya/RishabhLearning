package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.LoginDTO;
import com.PhoneX.Backend.Service.UserService;
import com.PhoneX.Backend.constants.UserPermissions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(loginDTO));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ALL_USERS + "')")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }
}

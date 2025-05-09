package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.ChangePasswordDTO;
import com.PhoneX.Backend.DTO.LoginDTO;
import com.PhoneX.Backend.DTO.RegistrationCustomerDTO;
import com.PhoneX.Backend.Service.UserService;
import com.PhoneX.Backend.entity.Customer;
import com.PhoneX.Backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/customer")
public class CustomerController {
    private final UserService userService;

    @Autowired
    public CustomerController(UserService userService){
        this.userService=userService;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationCustomerDTO registrationCustomerDTO) {
        String message = userService.registerForCustomer(registrationCustomerDTO); // This will throw ConflictException if username or email already exists
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> loginAsCustomer(@RequestBody LoginDTO login){
        return ResponseEntity.status(HttpStatus.OK).body(userService.login(login.getEmail(),login.getPassword(), User.Role.CUSTOMER));
    }

    @PatchMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO password){
        return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(password.getId(),password.getOldPassword(),password.getNewPassword(),User.Role.CUSTOMER));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteCustomer(id));
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUerUser());
    }
}

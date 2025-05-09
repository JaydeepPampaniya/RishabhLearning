package com.PhoneX.Backend.controller;

import com.PhoneX.Backend.DTO.ChangePasswordDTO;
import com.PhoneX.Backend.DTO.RegistrationCustomerDTO;
import com.PhoneX.Backend.Service.UserService;
import com.PhoneX.Backend.constants.UserPermissions;
import com.PhoneX.Backend.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final UserService userService;

    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegistrationCustomerDTO registrationCustomerDTO) {
        String message = userService.registerForCustomer(registrationCustomerDTO); // This will throw ConflictException if username or email already exists
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/getAllCustomer")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ALL_CUSTOMERS + "')")
    public ResponseEntity<Page<Customer>> getAllUser(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllCustomers(pageable));
    }

    @PatchMapping("/changePassword")
    @PreAuthorize("hasAuthority('" + UserPermissions.UPDATE_USER + "')")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO password) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.changePassword(password.getId(), password.getOldPassword(), password.getNewPassword()));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('" + UserPermissions.READ_ALL_CUSTOMERS + "')")
    public ResponseEntity<String> deleteCustomer(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteCustomer(id));
    }
}

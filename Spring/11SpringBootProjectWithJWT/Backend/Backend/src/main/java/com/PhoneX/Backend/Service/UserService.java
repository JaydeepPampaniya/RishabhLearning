package com.PhoneX.Backend.Service;


import com.PhoneX.Backend.DTO.LoginDTO;
import com.PhoneX.Backend.DTO.RegistrationCustomerDTO;
import com.PhoneX.Backend.config.PasswordEncoderConfig;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Customer;
import com.PhoneX.Backend.entity.Permission;
import com.PhoneX.Backend.entity.Role;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.globalException.UnauthorizedException;
import com.PhoneX.Backend.repository.CartRepository;
import com.PhoneX.Backend.repository.CustomerRepository;
import com.PhoneX.Backend.repository.RoleRepository;
import com.PhoneX.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final CartRepository cartRepository;
    private final RoleRepository roleRepository;

    @Transactional
    public String registerForCustomer(RegistrationCustomerDTO registrationCustomerDTO) {
        if (userRepository.existsByEmail(registrationCustomerDTO.getEmail())) {
            throw new ConflictException(MessageConstants.EMAIL_ALREADY_EXISTS);
        }
        if (customerRepository.existsByUsername(registrationCustomerDTO.getUsername())) {
            throw new ConflictException(MessageConstants.USERNAME_ALREADY_EXISTS);
        } else if (customerRepository.existsByContactNo(registrationCustomerDTO.getContactNo())) {
            throw new ConflictException(MessageConstants.CONTACT_ALREADY_EXISTS);
        }

        User user = new User();
        Role role = roleRepository.findByName(registrationCustomerDTO.getRole()).orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));
        user.setEmail(registrationCustomerDTO.getEmail());
        user.setRole(role);
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(registrationCustomerDTO.getPassword()));
        LocalDateTime localDateTime = LocalDateTime.now();
        user.setCreatedTime(localDateTime);
        userRepository.save(user);

        Customer customer = new Customer();
        customer.setUsername(registrationCustomerDTO.getUsername());
        customer.setAge(registrationCustomerDTO.getAge());
        customer.setAddress(registrationCustomerDTO.getAddress());
        customer.setGender(registrationCustomerDTO.getGender());
        customer.setContactNo(registrationCustomerDTO.getContactNo());
        customer.setUser(user);
        customerRepository.save(customer);

        return "Registered Successfully";
    }

    @Transactional
    public Map<String, Object> login(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(() -> new UnauthorizedException(MessageConstants.INVALID_CREDENTIALS));
        Map<String, Object> response = new HashMap<>();
        Role role = roleRepository.findById(user.getRole().getId()).orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));
        if (passwordEncoderConfig.passwordEncoder().matches(loginDTO.getPassword(), user.getPassword())) {
            Set<Permission> permissions = role.getPermissions();
            List<String> permissionNames = permissions.stream()
                    .map(Permission::getName)
                    .toList();

            switch (role.getName()) {
                case "ADMIN", "OWNER" ->
                        response.put("token", jwtService.generateToken(user.getId(), user.getAdmin().getAdminname(), user.getAdmin().getGender(), user.getRole().getName(), user.getEmail(), permissionNames));
                case "CUSTOMER" ->
                        response.put("token", jwtService.generateToken(user.getId(), user.getCustomer().getUsername(), user.getCustomer().getGender(), user.getRole().getName(), user.getEmail(), permissionNames));

                default -> throw new UnauthorizedException("You don't have access to login");
            }
            response.put("message", "Login Successfully");
            return response;

        } else {
            throw new ConflictException(MessageConstants.INCORRECT_PASSWORD);
        }
    }


    @Transactional
    public String changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.USER_NOT_FOUND));
        if (!passwordEncoderConfig.passwordEncoder().matches(oldPassword, user.getPassword())) {
            throw new BadRequestException("Old password is incorrect");
        } else if (passwordEncoderConfig.passwordEncoder().matches(newPassword, user.getPassword())) {
            throw new BadRequestException("Old password and new password cannot be same");
        } else {
            user.setPassword(passwordEncoderConfig.passwordEncoder().encode(newPassword));
            userRepository.save(user);
            return "Password Changed Successfully";
        }
    }

    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Transactional
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.USER_NOT_FOUND));
        cartRepository.deleteByUserId(user.getId());
        userRepository.deleteById(user.getId());
        return "User Deleted.";
    }

    @Transactional
    public String deleteCustomer(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.USER_NOT_FOUND));
        cartRepository.deleteByUserId(user.getId());
        customerRepository.deleteByUserId(user.getId());
        return "Customer Deleted.";
    }
}

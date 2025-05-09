package com.PhoneX.Backend.Service;


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
import com.PhoneX.Backend.repository.AdminRepository;
import com.PhoneX.Backend.repository.CustomerRepository;
import com.PhoneX.Backend.repository.RoleRepository;
import com.PhoneX.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final CustomerRepository customerRepository;
    private final JwtService jwtService;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository , PasswordEncoderConfig passwordEncoderConfig, CustomerRepository customerRepository, JwtService jwtService,AdminRepository adminRepository,RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.passwordEncoderConfig=passwordEncoderConfig;
        this.customerRepository=customerRepository;
        this.jwtService=jwtService;
        this.adminRepository=adminRepository;
        this.roleRepository=roleRepository;
    }

    public String registerForCustomer(RegistrationCustomerDTO registrationCustomerDTO) {
        if (userRepository.existsByEmail(registrationCustomerDTO.getEmail())) {
            throw new ConflictException(MessageConstants.EMAIL_ALREADY_EXISTS);
        }
        if (customerRepository.existsByUsername(registrationCustomerDTO.getUsername())) {
            throw new ConflictException(MessageConstants.USERNAME_ALREADY_EXISTS);
        } else if (customerRepository.existsByContactNo(registrationCustomerDTO.getContactNo())) {
            throw new ConflictException(MessageConstants.CONTACT_ALREADY_EXISTS);
        }

        Role customerRole = roleRepository.findByName(registrationCustomerDTO.getRole())
                .orElseThrow(() -> new NotFoundException("Role CUSTOMER not found"));

        User user = new User();
        user.setEmail(registrationCustomerDTO.getEmail());
        user.setRole(customerRole);
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(registrationCustomerDTO.getPassword())); // Hash password
        user.setCreatedTime(LocalDateTime.now());
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


    public Map<String, Object> login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        Map<String, Object> response = new HashMap<>();

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoderConfig.passwordEncoder().matches(password, user.getPassword())) {
                Set<String> permissions = user.getRole().getPermissions().stream()
                        .map(Permission::getName)
                        .collect(Collectors.toSet());

                if (user.getRole().getName().equals("CUSTOMER") && user.getCustomer() != null) {
                    response.put("userName", user.getCustomer().getUsername());
                    response.put("gender", user.getCustomer().getGender());
                    String token = jwtService.generateToken(user.getId(), user.getCustomer().getUsername(), user.getRole().getName(), permissions);
                    response.put("token", token);
                    response.put("message", "Login successfully");
                } else if (user.getRole().getName().equals("ADMIN") && user.getAdmin() != null) {
                    response.put("userName", user.getAdmin().getAdminname());
                    String token = jwtService.generateToken(user.getId(), user.getAdmin().getAdminname(), user.getRole().getName(), permissions);
                    response.put("token", token);
                    response.put("message", "Login successfully");
                }
                return response;
            } else {
                throw new BadRequestException(MessageConstants.INCORRECT_PASSWORD);
            }
        } else {
            throw new UnauthorizedException(MessageConstants.INVALID_CREDENTIALS);
        }
    }


    public String changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id).orElse(new User());
        if(!passwordEncoderConfig.passwordEncoder().matches(oldPassword,user.getPassword())){
            throw new BadRequestException("Old password is incorrect");
        } else if (passwordEncoderConfig.passwordEncoder().matches(newPassword,user.getPassword())) {
            throw new BadRequestException("Old password and new password cannot be same");
        } else{
            user.setPassword(passwordEncoderConfig.passwordEncoder().encode(newPassword));
            userRepository.save(user);
            return "Password Changed Successfully";
        }
    }

    public List<Customer> getAllUerUser() {
        return customerRepository.findAll();
    }

    public String deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return "User Deleted.";
        }else{
            throw new UnauthorizedException(MessageConstants.USER_NOT_FOUND);
        }
    }
    public String deleteCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            customerRepository.deleteById(id);
            return "Customer Deleted.";
        }else{
            throw new UnauthorizedException(MessageConstants.USER_NOT_FOUND);
        }
    }
}

package com.PhoneX.Backend.Service;


import com.PhoneX.Backend.DTO.RegistrationCustomerDTO;
import com.PhoneX.Backend.config.PasswordEncoderConfig;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Customer;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.UnauthorizedException;
import com.PhoneX.Backend.repository.CustomerRepository;
import com.PhoneX.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final CustomerRepository customerRepository;

    @Autowired
    public UserService(UserRepository userRepository ,PasswordEncoderConfig passwordEncoderConfig,CustomerRepository customerRepository){
        this.userRepository=userRepository;
        this.passwordEncoderConfig=passwordEncoderConfig;
        this.customerRepository=customerRepository;
    }

    public String registerForCustomer(RegistrationCustomerDTO registrationCustomerDTO) {
        if(customerRepository.existsByUsername(registrationCustomerDTO.getUsername())) {
            throw new ConflictException(MessageConstants.USERNAME_ALREADY_EXISTS);
        }else if (userRepository.existsByEmail(registrationCustomerDTO.getEmail())) {
            throw new ConflictException(MessageConstants.EMAIL_ALREADY_EXISTS);
        }else if(customerRepository.existsByContactNo(registrationCustomerDTO.getContactNo())){
            throw new ConflictException(MessageConstants.CONTACT_ALREADY_EXISTS);
        }
        User user = new User();
        Customer customer = new Customer();
        user.setEmail(registrationCustomerDTO.getEmail());
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(registrationCustomerDTO.getPassword())); // Encode the password
        user.setRole(User.Role.CUSTOMER);
        user.setCreatedTime(LocalDateTime.now());
        userRepository.save(user);
        customer.setContactNo(registrationCustomerDTO.getContactNo());
        customer.setAge(registrationCustomerDTO.getAge());
        customer.setAddress(registrationCustomerDTO.getAddress());
        customer.setGender(registrationCustomerDTO.getGender());
        customer.setUsername(registrationCustomerDTO.getUsername());
        customer.setUser(user);
        customerRepository.save(customer);
        return "Registered Successfully";
    }

    public Map<String, Object> login(String email,String password,User.Role role) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        Map<String, Object> response = new HashMap<>();
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getRole() != role) {
                throw new UnauthorizedException("You do not have permission to access this resource.");
            }else if(passwordEncoderConfig.passwordEncoder().matches(password,user.getPassword())) {
                response.put("userId", user.getId());
                response.put("userName", user.getCustomer().getUsername());
                response.put("gender", user.getCustomer().getGender());
                response.put("email", user.getEmail());
                response.put("message", "Login successfully");
                return response;
            }else
                throw new BadRequestException(MessageConstants.INCORRECT_PASSWORD);
        } else
            throw new UnauthorizedException(MessageConstants.INVALID_CREDENTIALS);
    }

    public String changePassword(Long id, String oldPassword, String newPassword,User.Role role) {
        User user = userRepository.findById(id).orElse(new User());
        if (user.getRole() != role) {
            throw new UnauthorizedException("You do not have permission to access this resource.");
        }else if(!passwordEncoderConfig.passwordEncoder().matches(oldPassword,user.getPassword())){
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

package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.DTO.LoginDTO;
import com.PhoneX.Backend.DTO.RegistrationAdminDTO;
import com.PhoneX.Backend.config.PasswordEncoderConfig;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Admin;
import com.PhoneX.Backend.entity.Customer;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.UnauthorizedException;
import com.PhoneX.Backend.repository.AdminRepository;
import com.PhoneX.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository,PasswordEncoderConfig passwordEncoderConfig){
        this.adminRepository=adminRepository;
        this.userRepository=userRepository;
        this.passwordEncoderConfig=passwordEncoderConfig;
    }

    public String registerForAdmin(RegistrationAdminDTO registrationAdminDTO) {
        if(adminRepository.existsByAdminname(registrationAdminDTO.getAdminname())) {
            throw new ConflictException(MessageConstants.USERNAME_ALREADY_EXISTS);
        }else if (userRepository.existsByEmail(registrationAdminDTO.getEmail())) {
            throw new ConflictException(MessageConstants.EMAIL_ALREADY_EXISTS);
        }else if(adminRepository.existsByContactNo(registrationAdminDTO.getContactNo())){
            throw new ConflictException(MessageConstants.CONTACT_ALREADY_EXISTS);
        }
        User user = new User();
        Admin admin=new Admin();
        user.setEmail(registrationAdminDTO.getEmail());
        user.setRole(User.Role.ADMIN);
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(registrationAdminDTO.getPassword())); // Encode the password
        user.setCreatedTime(LocalDateTime.now());
        userRepository.save(user);
        admin.setContactNo(registrationAdminDTO.getContactNo());
        admin.setAdminname(registrationAdminDTO.getAdminname());
        admin.setUser(user);
        adminRepository.save(admin);
        return "Registered Successfully";
    }

    public Map<String,Object> login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByEmail(loginDTO.getEmail());
        Map<String, Object> response = new HashMap<>();
        if(userOptional.isPresent()) {
            User user = userOptional.get();
            if(passwordEncoderConfig.passwordEncoder().matches(loginDTO.getPassword(), user.getPassword())){
                response.put("adminId", user.getId());
                response.put("message", "Login successfully");
                return response;
            }else
                throw new ConflictException(MessageConstants.INCORRECT_PASSWORD);
        } else
            throw new BadRequestException(MessageConstants.INVALID_CREDENTIALS);
    }

    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }

    public String deleteAdmin(Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if(admin.isPresent()){
            adminRepository.deleteById(id);
            return "Customer Deleted.";
        }else{
            throw new UnauthorizedException(MessageConstants.USER_NOT_FOUND);
        }
    }
}

package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.DTO.RegistrationAdminDTO;
import com.PhoneX.Backend.config.PasswordEncoderConfig;
import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.Admin;
import com.PhoneX.Backend.entity.Role;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.globalException.ConflictException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.AdminRepository;
import com.PhoneX.Backend.repository.RoleRepository;
import com.PhoneX.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final PasswordEncoderConfig passwordEncoderConfig;
    private final RoleRepository roleRepository;


    @Transactional
    public String registerForAdmin(RegistrationAdminDTO registrationAdminDTO) {
        if (adminRepository.existsByAdminname(registrationAdminDTO.getAdminname())) {
            throw new ConflictException(MessageConstants.USERNAME_ALREADY_EXISTS);
        } else if (userRepository.existsByEmail(registrationAdminDTO.getEmail())) {
            throw new ConflictException(MessageConstants.EMAIL_ALREADY_EXISTS);
        } else if (adminRepository.existsByContactNo(registrationAdminDTO.getContactNo())) {
            throw new ConflictException(MessageConstants.CONTACT_ALREADY_EXISTS);
        }
        Role role = roleRepository.findByName(registrationAdminDTO.getRole()).orElseThrow(() -> new NotFoundException(MessageConstants.ROLE_NOT_FOUND));
        User user = new User();
        Admin admin = new Admin();
        user.setEmail(registrationAdminDTO.getEmail());
        user.setRole(role);
        user.setPassword(passwordEncoderConfig.passwordEncoder().encode(registrationAdminDTO.getPassword())); // Encode the password
        user.setCreatedTime(LocalDateTime.now());
        userRepository.save(user);
        admin.setContactNo(registrationAdminDTO.getContactNo());
        admin.setAdminname(registrationAdminDTO.getAdminname());
        admin.setGender(registrationAdminDTO.getGender());
        admin.setUser(user);
        adminRepository.save(admin);
        return "Registered Successfully";
    }

    @Transactional(readOnly = true)
    public Page<Admin> getAllAdmin(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }
}

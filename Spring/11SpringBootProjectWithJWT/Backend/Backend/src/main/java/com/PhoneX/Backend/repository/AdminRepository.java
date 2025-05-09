package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsByAdminname(String username);
    boolean existsByContactNo(long contactNo);
    Optional<Admin> findByAdminname(String adminname);
}

package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin,Long> {
    boolean existsByAdminname(String username);
    boolean existsByContactNo(long contactNo);
}

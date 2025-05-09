package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Optional<Role> findByName(String admin);

    boolean existsByName(String name);
}

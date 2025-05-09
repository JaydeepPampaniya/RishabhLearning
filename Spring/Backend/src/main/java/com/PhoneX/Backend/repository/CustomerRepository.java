package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByUsername(String username);
    Optional<Customer> findByUsername(String username);
    boolean existsByContactNo(long contactNo);
}

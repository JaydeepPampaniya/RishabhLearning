package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    boolean existsByUsername(String username);

    boolean existsByContactNo(long contactNo);
}

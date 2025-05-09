package com.PhoneX.Backend.repository;


import com.PhoneX.Backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByDeviceName(String deviceName);

    Optional<Product> findById(long productId);
}

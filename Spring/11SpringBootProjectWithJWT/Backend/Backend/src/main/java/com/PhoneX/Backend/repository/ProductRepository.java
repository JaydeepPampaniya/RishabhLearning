package com.PhoneX.Backend.repository;


import com.PhoneX.Backend.DTO.responseDTO.ProductResponseDTO;
import com.PhoneX.Backend.entity.DeviceType;
import com.PhoneX.Backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByDeviceName(String deviceName);

    Optional<Product> findById(long productId);

    void deleteByDeviceTypeId(int deviceTypeId);

    Page<Product> findByDeviceType(DeviceType deviceType, Pageable pageable);
}

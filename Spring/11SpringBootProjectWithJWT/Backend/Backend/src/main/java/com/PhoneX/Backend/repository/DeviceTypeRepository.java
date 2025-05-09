package com.PhoneX.Backend.repository;

import com.PhoneX.Backend.entity.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeviceTypeRepository extends JpaRepository<DeviceType, Integer> {
    boolean existsByName(String name);

    Optional<DeviceType> findById(long id);

    Optional<DeviceType> findByName(String name);
}

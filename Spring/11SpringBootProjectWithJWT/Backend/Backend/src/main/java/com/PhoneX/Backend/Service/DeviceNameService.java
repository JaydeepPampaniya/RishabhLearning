package com.PhoneX.Backend.Service;

import com.PhoneX.Backend.constants.MessageConstants;
import com.PhoneX.Backend.entity.DeviceType;
import com.PhoneX.Backend.entity.Product;
import com.PhoneX.Backend.entity.User;
import com.PhoneX.Backend.globalException.BadRequestException;
import com.PhoneX.Backend.globalException.NotFoundException;
import com.PhoneX.Backend.repository.DeviceTypeRepository;
import com.PhoneX.Backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceNameService {

    private final DeviceTypeRepository deviceTypeRepository;
    private final ProductRepository productRepository;

    @Transactional
    public String addDeviceType(String name) {
        if (deviceTypeRepository.existsByName(name)) {
            throw new BadRequestException("Device type already exists!");
        } else {
            DeviceType deviceType = new DeviceType();
            deviceType.setName(name);
            deviceTypeRepository.save(deviceType);
            return "Device type saved";
        }
    }

    public Page<DeviceType> getDeviceType(Pageable pageable) {
        return deviceTypeRepository.findAll(pageable);
    }

    public String deleteDeviceType(int id) {
        DeviceType deviceType = deviceTypeRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageConstants.DEVICE_TYPE_NOT_FOUND));
        productRepository.deleteByDeviceTypeId(deviceType.getId());
        deviceTypeRepository.deleteById(deviceType.getId());
        return "Device type deleted";
    }
}

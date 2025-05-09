package com.PhoneX.Backend.DTO.responseDTO;

import com.PhoneX.Backend.entity.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
    private Long id;
    private String imageData;
    private String companyName;
    private String deviceName;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private long discount;
    private String description;
    private DeviceType deviceType;
}

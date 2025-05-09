package com.PhoneX.Backend.DTO;

import jakarta.persistence.Lob;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateProductDto {
    private String imageName;
    private String imageType;
    @Lob //means large object
    private byte[] imageData;

    private String companyName;
    private String deviceName;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private long discount;
    private String description;

    private Long deviceTypeId;
}

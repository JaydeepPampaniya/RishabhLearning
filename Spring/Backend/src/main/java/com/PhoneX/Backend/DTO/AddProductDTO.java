package com.PhoneX.Backend.DTO;

import com.PhoneX.Backend.entity.Categories;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductDTO {
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

    private Long categories;
}

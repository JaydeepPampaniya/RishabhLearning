package com.PhoneX.Backend.DTO;

import lombok.Data;

@Data
public class CartDto {
    private long userId;
    private long productId;
    private long quantity;
}

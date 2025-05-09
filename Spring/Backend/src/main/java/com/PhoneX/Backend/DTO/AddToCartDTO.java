package com.PhoneX.Backend.DTO;

import lombok.Data;

@Data
public class AddToCartDTO {
    private long productId;
    private long userId;
    private int quantity;
}

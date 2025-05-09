package com.PhoneX.Backend.DTO;

import lombok.Data;

@Data
public class ChangePasswordDTO {
    private Long id;
    private String oldPassword;
    private String newPassword;
}

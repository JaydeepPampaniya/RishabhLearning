package com.PhoneX.Backend.DTO;

import lombok.Data;

@Data
public class RegistrationAdminDTO {
    private String adminname;
    private String email;
    private String password;
    private long contactNo;
    private String gender;
    private String role;
}

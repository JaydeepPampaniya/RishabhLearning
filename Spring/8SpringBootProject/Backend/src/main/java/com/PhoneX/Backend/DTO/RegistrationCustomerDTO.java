package com.PhoneX.Backend.DTO;

import lombok.Data;

@Data
public class RegistrationCustomerDTO {
    private String username;
    private String email;
    private String password;
    private String gender;
    private int age;
    private String address;
    private long contactNo;
}

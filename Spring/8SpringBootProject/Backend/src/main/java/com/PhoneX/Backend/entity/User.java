package com.PhoneX.Backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    private Role role;
    private LocalDateTime createdTime;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore  // Prevents serialization of the customer field to avoid infinite recursion
    private Customer customer;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore  // Prevents serialization of the customer field to avoid infinite recursion
    private Admin admin;

    public enum Role{
        ADMIN,CUSTOMER
    }

//    @OneToMany(mappedBy = "user") // Maps to the "user" field in the Cart entity
//    private Set<Cart> carts;

}

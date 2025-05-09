package com.PhoneX.Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false,unique = true)
    private String username;

    private String gender;
    private String address;
    private int age;

    @Column(name="contact_no",nullable = false,unique = true)
    private long contactNo;

    @OneToOne
    @JoinColumn(name = "user_id")
    @Cascade(CascadeType.DELETE_ORPHAN)
    private User user;
}

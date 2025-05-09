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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="adminname", nullable = false,unique = true)
    private String adminname;

    @Column(name = "contact_no",unique = true,nullable = false)
    private long contactNo;

    @OneToOne
    @JoinColumn(name="admin_id")
    @Cascade(CascadeType.DELETE_ORPHAN)
    private User user;

}

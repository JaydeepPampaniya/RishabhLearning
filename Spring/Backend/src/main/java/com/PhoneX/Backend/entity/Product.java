package com.PhoneX.Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String imageName;
    private String imageType;
    @Lob //means large object
    private byte[] imageData;

    private String companyName;
    private String deviceName;
    private BigDecimal originalPrice;
    private BigDecimal currentPrice;
    private long discount;

    @Column(name = "description" ,length = 2000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Categories categories;

//    @OneToMany(mappedBy = "products")
//    private Set<Cart> carts;

}

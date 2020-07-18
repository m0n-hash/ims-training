package com.sdm.ims.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity(name = "ProductEntity")
@Table(name = "tbl_products")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductGroup productGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductType productType;

    @ManyToOne(fetch = FetchType.EAGER)

    private UnitOfMeasurement unitOfMeasurement;

    @Column(length = 50,nullable = false)
    private String code;

    @Column(length = 255,nullable = false)
    private String name;

    @Column(length = 500,nullable = true)
    private String description;

    @Column(nullable = false)
    private int qty;
}

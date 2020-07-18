package com.sdm.ims.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "CustomerEntity")
@Table(name = "tbl_customers")
@Data
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50,nullable = false)
    private String code;

    @Column(length = 255,nullable = false)
    private String name;

    @Column(length = 255,nullable = false)
    private String company;
}

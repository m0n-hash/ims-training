package com.sdm.ims.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "SaleItemEntity")
@Table(name = "tbl_sale_items")
@Data
@NoArgsConstructor
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int saleVoucherId;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Product product;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private int qty;
}

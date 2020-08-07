package com.sdm.ims.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "PurchaseItem")
@Table(name = "tbl_purchaseItem")
@Data
@NoArgsConstructor
public class PurchaseItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private int purchaseVoucherId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column
    private int price;

    @Column
    private int qty;

    public PurchaseItem(Integer Id){this.Id=Id;}
}

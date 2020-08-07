package com.sdm.ims.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Table(name = "tbl_purchaseVoucher")
@Entity(name = "PurchaseVoucher")
@Data
@NoArgsConstructor
public class PurchaseVoucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(length = 255, nullable = false)
    private String voucher_no;

    @ManyToOne(fetch = FetchType.EAGER)
    private Vendor vendor;

    @OneToMany(mappedBy = "purchaseVoucherId",fetch = FetchType.EAGER)
    private Set<PurchaseItem> purchaseItemSet;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date voucher_dt;
}

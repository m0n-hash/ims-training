package com.sdm.ims.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity(name = "SaleVoucherEntity")
@Table(name = "tbl_sale_vouchers")
@Data
@NoArgsConstructor
public class SaleVoucher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50,nullable = false)
    private String voucherNo;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Customer customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date saleVoucherDate;

    @OneToMany(mappedBy = "saleVoucherId",fetch = FetchType.EAGER)
    private Set<SaleItem> saleItems;
}

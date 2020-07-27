package com.sdm.ims.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;


@Entity(name = "Vendor")
@Table(name = "tbl_vendor")
@Data
@NoArgsConstructor
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255,nullable = false)
    private String code;

    @Column(length = 500,nullable = false)
    private String name;

    @Column(length = 500,nullable = false)
    private String contact_person;

    @Column(length = 700,nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(length = 1000)
    private String remark;

    @Column
    private boolean isActive;

    @Column
    private boolean isDefault;
}

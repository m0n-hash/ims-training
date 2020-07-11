package com.sdm.ims.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "UnitOfMeasurementEntity")
@Table(name = "tbl_unit_of_measurements")
@Data
@NoArgsConstructor
public class UnitOfMeasurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50,nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private boolean def;

    @Column
    private boolean active;
}

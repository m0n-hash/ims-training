package com.sdm.ims.repository;

import com.sdm.ims.entity.UnitOfMeasurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitOfMeasurementRepository extends JpaRepository<UnitOfMeasurement,Integer> {
    public UnitOfMeasurement findOneByCodeAndName(String code,String name);

    @Query(value = "SELECT * FROM tbl_unit_of_measurements WHERE code=:code AND name=:name",nativeQuery = true)
    UnitOfMeasurement helloWorld(@Param("code") String code,@Param("name") String name);

    @Query(value = "SELECT uom FROM #{#entityName} uom WHERE code=:code AND name=:name")
    UnitOfMeasurement helloWorld2(@Param("code") String code,@Param("name") String name);
}

package com.sdm.ims.repository;

import com.sdm.ims.entity.ProductGroup;
import com.sdm.ims.entity.SaleVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleVoucherRepository extends JpaRepository<SaleVoucher,Integer> {
}

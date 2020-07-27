package com.sdm.ims.repository;

import com.sdm.ims.entity.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleItemRepository extends JpaRepository<SaleItem,Integer> {
}

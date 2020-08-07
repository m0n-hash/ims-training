package com.sdm.ims.repository;

import com.sdm.ims.entity.PurchaseItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem,Integer> {
}

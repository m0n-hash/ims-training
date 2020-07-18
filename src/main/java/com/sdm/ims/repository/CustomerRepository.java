package com.sdm.ims.repository;

import com.sdm.ims.entity.Customer;
import com.sdm.ims.entity.ProductGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
}

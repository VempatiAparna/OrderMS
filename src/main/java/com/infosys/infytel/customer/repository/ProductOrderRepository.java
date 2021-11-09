package com.infosys.infytel.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infytel.customer.entity.ProductOrder;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Integer>{

}

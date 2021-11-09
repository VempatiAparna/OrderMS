package com.infosys.infytel.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infosys.infytel.customer.entity.Orderdb;


public interface OrderRepository extends JpaRepository<Orderdb, Integer>{

}

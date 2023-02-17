package com.ty.springBoot_Hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springBoot_Hospital.dto.MedOrders;

public interface MedOrdersRepo extends JpaRepository<MedOrders, Integer>{

}

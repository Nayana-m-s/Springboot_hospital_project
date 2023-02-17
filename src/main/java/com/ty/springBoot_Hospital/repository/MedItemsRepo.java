package com.ty.springBoot_Hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springBoot_Hospital.dto.MedItems;

public interface MedItemsRepo extends JpaRepository<MedItems, Integer>{

}

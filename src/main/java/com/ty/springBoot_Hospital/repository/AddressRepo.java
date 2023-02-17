package com.ty.springBoot_Hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springBoot_Hospital.dto.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>{

}

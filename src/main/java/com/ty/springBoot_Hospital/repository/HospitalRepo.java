package com.ty.springBoot_Hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ty.springBoot_Hospital.dto.Hospital;

public interface HospitalRepo extends JpaRepository<Hospital, Integer>{
	public Hospital getByEmail(String email);
}

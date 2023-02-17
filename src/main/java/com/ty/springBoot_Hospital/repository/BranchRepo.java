package com.ty.springBoot_Hospital.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.dto.Hospital;

public interface BranchRepo extends JpaRepository<Branch, Integer>{
	public List<Branch> getByHospital(Hospital hospital);
}

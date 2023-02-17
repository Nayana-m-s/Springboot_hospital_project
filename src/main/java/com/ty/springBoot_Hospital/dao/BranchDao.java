package com.ty.springBoot_Hospital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springBoot_Hospital.dto.Address;
import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.dto.Hospital;
import com.ty.springBoot_Hospital.repository.BranchRepo;

@Repository
public class BranchDao {
	@Autowired
	private BranchRepo branchRepo;

	@Autowired
	private HospitalDao hospitalDao;

	@Autowired
	private AddressDao addressDao;

	public Branch saveBranch(int hid, int aid, Branch branch) {
		Hospital hospital = hospitalDao.getHospitalById(hid);
		Address address = addressDao.getAddressById(aid);
		branch.setHospital(hospital);
		branch.setAddress(address);
		return branchRepo.save(branch);
	}

	public Branch updateBranch(Branch branch, int bid) {
		if (branchRepo.findById(bid).isPresent()) {
			Branch dbBranch = branchRepo.findById(bid).get();
			branch.setId(bid);
			branch.setHospital(dbBranch.getHospital());
			branch.setAddress(dbBranch.getAddress());
			return branchRepo.save(branch);

		} else {
			return null;
		}
	}

	public Branch deleteBranch(int id) {

		if (branchRepo.findById(id).isPresent()) {
			Branch branch = branchRepo.findById(id).get();
			branchRepo.delete(branch);
			return branch;
		} else {
			return null;
		}
	}

	public Branch getBranchById(int id) {
		if (branchRepo.findById(id).isPresent()) {
			return branchRepo.findById(id).get();

		} else {
			return null;
		}
	}

	public List<Branch> getAllBranchByHospital(int hid) {
		Hospital hospital = hospitalDao.getHospitalById(hid);

		if (hospital != null) {
			return branchRepo.getByHospital(hospital);
		} else {
			return null;
		}
	}

}

package com.ty.springBoot_Hospital.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.BranchDao;
import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class BranchService {
	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<Response<Branch>> saveBranch(Branch branch, int hid, int aid) {
		Response<Branch> response = new Response<>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(branchDao.saveBranch(hid, aid, branch));
		return new ResponseEntity<Response<Branch>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Branch>> updateBranch(Branch branch, int bid) {

		Branch daoBranch = branchDao.updateBranch(branch, bid);
		if (daoBranch != null) {
			Response<Branch> response = new Response<>();
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(daoBranch);
			return new ResponseEntity<Response<Branch>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not Found");
		}

	}

	public ResponseEntity<Response<Branch>> deleteBranch(int id) {

		Branch daoBranch = branchDao.deleteBranch(id);
		if (daoBranch != null) {
			Response<Branch> response = new Response<>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(daoBranch);
			return new ResponseEntity<Response<Branch>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id not Found");
		}

	}

	public ResponseEntity<Response<Branch>> getBranchById(int id) {

		Branch daoBranch = branchDao.getBranchById(id);
		if (daoBranch != null) {
			Response<Branch> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(daoBranch);
			return new ResponseEntity<Response<Branch>>(response, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Id not Found");
		}

	}

	public ResponseEntity<Response<List<Branch>>> getAllBranchByHospital(int hid) {
		List<Branch> daoBranch = branchDao.getAllBranchByHospital(hid);
		if (daoBranch != null) {
			Response<List<Branch>> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(daoBranch);
			return new ResponseEntity<Response<List<Branch>>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Id not Found");
		}

	}
}

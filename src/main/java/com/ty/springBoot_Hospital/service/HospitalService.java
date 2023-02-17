package com.ty.springBoot_Hospital.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.HospitalDao;
import com.ty.springBoot_Hospital.dto.Hospital;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class HospitalService {
	@Autowired
	private HospitalDao dao;

	public ResponseEntity<Response<Hospital>> saveHospital(Hospital hospital) {
		Response<Hospital> response = new Response<Hospital>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(dao.saveHospital(hospital));

		return new ResponseEntity<Response<Hospital>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Hospital>> updateHospital(int id, Hospital hospital) {

		Hospital daoHospital = dao.updateHospital(id, hospital);
		if (daoHospital != null) {
			Response<Hospital> response = new Response<Hospital>();
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(daoHospital);

			return new ResponseEntity<Response<Hospital>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");
		}

	}

	public ResponseEntity<Response<Hospital>> deleteHospital(int id) {
		Hospital daoHospital = dao.deleteHospital(id);
		if (daoHospital != null) {
			Response<Hospital> response = new Response<Hospital>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(daoHospital);

			return new ResponseEntity<Response<Hospital>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");

		}
	}

	public ResponseEntity<Response<Hospital>> getHospitalById(int id) {
		Hospital daoHospital = dao.getHospitalById(id);
		if (daoHospital != null) {
			Response<Hospital> response = new Response<Hospital>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(daoHospital);

			return new ResponseEntity<Response<Hospital>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Given Id Not Found");

		}
	}

}

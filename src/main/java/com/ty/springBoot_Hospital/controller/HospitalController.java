package com.ty.springBoot_Hospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.springBoot_Hospital.dao.HospitalDao;
import com.ty.springBoot_Hospital.dto.Hospital;
import com.ty.springBoot_Hospital.service.HospitalService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private  HospitalDao hospitalDao;
	
	@ApiOperation(value = "Save Hospital", notes="API is used to save the hospital")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved")
	})
	@PostMapping("/hospital")
	public ResponseEntity<Response<Hospital>> saveHospital(@Valid @RequestBody Hospital hospital){
		return hospitalService.saveHospital(hospital);
	}
	
	
	@ApiOperation(value = "Update Hospital", notes="API is used to update the hospital with given hospital ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully updated"),
			@ApiResponse(code=404,message="Id not found for the given hospital ID")
	})
	@PutMapping("/hospital/{id}")
	public ResponseEntity<Response<Hospital>> updateHospital(@PathVariable int id,@RequestBody Hospital hospital){
		return hospitalService.updateHospital(id,hospital);
	}
	
	
	@ApiOperation(value = "Delete Hospital", notes="API is used to delete the hospital with given hospital ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given hospital ID")
	})
	@DeleteMapping("/hospital/{id}")
	public ResponseEntity<Response<Hospital>> deleteHospital(@PathVariable int id){
		return hospitalService.deleteHospital(id);
	}
	
	
	@ApiOperation(value = "Get Hospital By ID", notes="API is used to fetch the hospital with given hospital ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given hospital ID")
	})
	@GetMapping("/hospital/{id}")
	public ResponseEntity<Response<Hospital>> getHospitalById(@PathVariable int id){
		return hospitalService.getHospitalById(id);
	}
	
	
	@ApiOperation(value = "Get all hospital", notes="API is used to fetch all the hospitals present in database")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given hospital ID")
	})
	@GetMapping("/Allhospitals")
	public List<Hospital> getAllHospital() {
		return hospitalDao.getAllHospital();
	}
}

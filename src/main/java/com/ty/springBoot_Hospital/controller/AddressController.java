package com.ty.springBoot_Hospital.controller;

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

import com.ty.springBoot_Hospital.dto.Address;
import com.ty.springBoot_Hospital.service.AddressService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService service;
	
	
	@ApiOperation(value = "Save Address", notes="API is used to save the address")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved")
	})
	@PostMapping("/address")
	public ResponseEntity<Response<Address>> saveAddress(@Valid @RequestBody Address address){
		return service.saveAddress(address);
	}
	
	
	@ApiOperation(value = "Update Address", notes="API is used to update the address with given address ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully updated"),
			@ApiResponse(code=404,message="Id not found for the given address ID")
	})
	@PutMapping("/address/{id}")
	public ResponseEntity<Response<Address>> updateAddress(@PathVariable int id,@RequestBody Address address){
		return service.updateAddress(id,address);
	}
	
	
	@ApiOperation(value = "Delete Address", notes="API is used to delete the address with given address ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given address ID")
	})
	@DeleteMapping("/address/{id}")
	public ResponseEntity<Response<Address>> deleteAddress(@PathVariable int id){
		return service.deleteAddress(id);
	}
	
	
	@ApiOperation(value = "Fetch Address", notes="API is used to fetch the address with given address ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given address ID")
	})
	@GetMapping("/address/{id}")
	public ResponseEntity<Response<Address>> getAddressById(@PathVariable int id){
		return service.getAddressById(id);
	}
}

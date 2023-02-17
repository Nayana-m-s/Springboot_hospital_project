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

import com.ty.springBoot_Hospital.dto.MedOrders;
import com.ty.springBoot_Hospital.service.MedOrdersService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MedOrdersController {
	@Autowired
	private MedOrdersService medOrdersService;
	
	@ApiOperation(value = "Save MedOrders", notes="API is used to save the medOrders with given encounter ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved")
	})
	@PostMapping("/medOrders/{encounterId}")
	public ResponseEntity<Response<MedOrders>> saveMedOrders(@Valid @RequestBody MedOrders medOrders,@PathVariable int encounterId){
		return medOrdersService.saveMedOrders(medOrders, encounterId);
	}
	
	
	@ApiOperation(value = "Update MedOrders", notes="API is used to update the medOrders with given existing medOrder ID and encounter ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully updated"),
			@ApiResponse(code=404,message="Id not found for the given medOrders ID")
	})
	@PutMapping("/medOrders/{medOrdersId}/{encounterId}")
	public ResponseEntity<Response<MedOrders>> updateMedOrders(@RequestBody MedOrders medOrders,@PathVariable int medOrdersId,@PathVariable int encounterId){
		return medOrdersService.updateMedOrders(medOrders,medOrdersId,encounterId);
	}
	
	@ApiOperation(value = "delete MedOrders", notes="API is used to delete the medOrder with given existing medOrder ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given medOrders ID")
	})
	@DeleteMapping("/medOrders/{medOrderId}")
	public ResponseEntity<Response<MedOrders>> deleteMedOrders(@PathVariable int medOrderId){
		return medOrdersService.deleteMedOrder(medOrderId);
	}
	@ApiOperation(value = "Fetch MedOrders", notes="API is used to fetch the medOrder with given existing medOrder ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given medOrders ID")
	})
	@GetMapping("/medOrders/{medOrderId}")
	public ResponseEntity<Response<MedOrders>> getMedOrdersById(@PathVariable int medOrderId){
		return medOrdersService.getMedOrdersById(medOrderId);
	}
}

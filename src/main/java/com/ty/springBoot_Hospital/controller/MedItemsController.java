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

import com.ty.springBoot_Hospital.dto.MedItems;
import com.ty.springBoot_Hospital.service.MedItemsService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class MedItemsController {
	@Autowired
	private MedItemsService medItemsService;
	
	@ApiOperation(value = "Save MedItems", notes="API is used to save the medItems with given medOrder ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved")
	})
	@PostMapping("/medItems/{medOrderId}")
	public ResponseEntity<Response<MedItems>> saveMedItems(@Valid @RequestBody MedItems medItems,@PathVariable int medOrderId){
		return medItemsService.saveMedItems(medItems, medOrderId);
	}
	
	
	@ApiOperation(value = "Update MedItems", notes="API is used to update the medItems with given medItem ID and medOrder ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved"),
			@ApiResponse(code=404,message="Id not found for the given medItems ID")
	})
	@PutMapping("/medItems/{medItemId}/{medOrderId}")
	public ResponseEntity<Response<MedItems>> updateMedItems(@RequestBody MedItems medItems,@PathVariable int medItemId,@PathVariable int medOrderId){
		return medItemsService.updateMedItems(medItems,medItemId,medOrderId);
	}
	
	
	@ApiOperation(value = "Delete MedItems", notes="API is used to delete the medItems with given medItem ID ")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given medItems ID")
	})
	@DeleteMapping("/medItems/{medItemId}")
	public ResponseEntity<Response<MedItems>> deleteMedItems(@PathVariable int medItemId){
		return medItemsService.deleteMedItems(medItemId);
	}
	
	
	@ApiOperation(value = "Fetch MedItems", notes="API is used to fetch the medItems with given medItem ID ")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given medItems ID")
	})
	@GetMapping("/medItems/{medItemId}")
	public ResponseEntity<Response<MedItems>> getMedItemsById(@PathVariable int medItemId){
		return medItemsService.getMedItemById(medItemId);
	}
}

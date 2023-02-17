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

import com.ty.springBoot_Hospital.dto.Encounter;
import com.ty.springBoot_Hospital.service.EncounterService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class EncounterController {
	@Autowired
	private EncounterService encounterService;
	
	@ApiOperation(value = "Save Encounter", notes="API is used to save the encounter with given person ID and branch ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully created"),
			@ApiResponse(code=404,message="Id not found for the given encounter ID")
	})
	@PostMapping("/encounter/{personId}/{branchId}")
	public ResponseEntity<Response<Encounter>> saveEncounter(@Valid @RequestBody Encounter encounter,@PathVariable int personId,@PathVariable int branchId){
		return encounterService.saveEncounter(encounter, personId, branchId);
	}
	
	
	@ApiOperation(value = "Update Encounter", notes="API is used to update the encounter with given existing encounter ID and branch ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully updated"),
			@ApiResponse(code=404,message="Id not found for the given encounter ID")
	})
	@PutMapping("/encounter/{encounterId}/{branchId}")
	public ResponseEntity<Response<Encounter>> updateEncounter(@RequestBody Encounter encounter,@PathVariable int encounterId,@PathVariable int branchId){
		return encounterService.updateEncounter(encounter,encounterId,branchId);
	}
	
	
	@ApiOperation(value = "Delete Encounter", notes="API is used to delete the encounter with given encounter ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given encounter ID")
	})
	@DeleteMapping("/encounter/{encounterId}")
	public ResponseEntity<Response<Encounter>> deleteEncounter(@PathVariable int encounterId){
		return encounterService.deleteEncounter(encounterId);
	}
	
	
	@ApiOperation(value = "Get Encounter by id", notes="API is used to get the encounter with given encounter ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully Found"),
			@ApiResponse(code=404,message="Id not found for the given encounter ID")
	})
	@GetMapping("/encounter/{encounterId}")
	public ResponseEntity<Response<Encounter>> getEncounterById(int encounterId){
		return encounterService.getEncounterById(encounterId);
	}
}

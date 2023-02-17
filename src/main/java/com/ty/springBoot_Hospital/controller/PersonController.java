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

import com.ty.springBoot_Hospital.dto.Person;
import com.ty.springBoot_Hospital.service.PersonService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	
	
	@ApiOperation(value = "Save Person", notes="API is used to save the Person")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved")
	})
	@PostMapping("/person")
	public ResponseEntity<Response<Person>> savePerson(@Valid @RequestBody Person person){
		return personService.savePerson(person);
	}
	
	
	@ApiOperation(value = "Update Person", notes="API is used to update the person with given person ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully updated"),
			@ApiResponse(code=404,message="Id not found for the given person ID")
	})
	@PutMapping("/person/{id}")
	public ResponseEntity<Response<Person>> updatePerson(@RequestBody Person person,@PathVariable int id){
		return personService.updatePerson(id,person);
	}
	
	
	@ApiOperation(value = "Delete Person", notes="API is used to delete the person with given person ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given person ID")
	})
	@DeleteMapping("/person/{id}")
	public ResponseEntity<Response<Person>> deletePerson(@PathVariable int id){
		return personService.deletePerson(id);
	}
	
	
	@ApiOperation(value = "Fetch Person", notes="API is used to fetch the person with given person ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given person ID")
	})
	@GetMapping("/person/{id}")
	public ResponseEntity<Response<Person>> getPersonById(@PathVariable int id){
		return personService.getPersonById(id);
	}
}

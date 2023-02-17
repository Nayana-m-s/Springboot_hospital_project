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

import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.service.BranchService;
import com.ty.springBoot_Hospital.util.Response;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class BranchController {
	@Autowired
	private BranchService branchService;
	
	
	@ApiOperation(value = "Save Branch", notes="API is used to save the branch.")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully saved"),
	})
	@PostMapping("/branch/{hid}/{aid}")
	public ResponseEntity<Response<Branch>> saveBranch(@PathVariable int hid,@PathVariable int aid,@Valid @RequestBody Branch branch){
		return branchService.saveBranch(branch, hid,aid);
	}
	
	
	@ApiOperation(value = "Update Branch", notes="API is used to update branch with given branch ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully updated"),
			@ApiResponse(code=404,message="Id not found for the given branch ID")
	})
	@PutMapping("/branch/{bid}")
	public ResponseEntity<Response<Branch>> updateBranch(@PathVariable int bid,@RequestBody Branch branch){
		return branchService.updateBranch(branch,bid);
	}
	
	
	@ApiOperation(value = "Delete branch", notes="API is used to delete the branch with given branch ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully deleted"),
			@ApiResponse(code=404,message="Id not found for the given branch ID")
	})
	@DeleteMapping("/branch/{id}")
	public ResponseEntity<Response<Branch>> deleteBranch(@PathVariable int id){
		return branchService.deleteBranch(id);
	}
	
	
	@ApiOperation(value = "Get Branch", notes="API is used to fetch the branch with given branch ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Id not found for the given branch ID")
	})
	@GetMapping("/branch/{id}")
	public ResponseEntity<Response<Branch>> getBranchById(@PathVariable int id){
		return branchService.getBranchById(id);
	}
	
	
	@ApiOperation(value = "Get branches of hospital", notes="API is used to fetch all the branches with given hospital ID")
	@ApiResponses(value = {
			@ApiResponse(code = 201,message="Successfully fetched"),
			@ApiResponse(code=404,message="Brancehs not found for the given hospital ID")
	})
	@GetMapping("/getallbranch/{hid}")
	public ResponseEntity<Response<List<Branch>>> getAllBranchByHospital(@PathVariable int hid){
		return branchService.getAllBranchByHospital(hid);
	}
}
package com.ty.springBoot_Hospital.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.BranchDao;
import com.ty.springBoot_Hospital.dao.EncounterDao;
import com.ty.springBoot_Hospital.dao.PersonDao;
import com.ty.springBoot_Hospital.dto.Branch;
import com.ty.springBoot_Hospital.dto.Encounter;
import com.ty.springBoot_Hospital.dto.Person;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class EncounterService {
	@Autowired
	private PersonDao personDao;
	@Autowired
	private EncounterDao encounterDao;
	@Autowired
	private BranchDao branchDao;

	public ResponseEntity<Response<Encounter>> saveEncounter(Encounter encounter, int personId, int branchId) {
		Person person = personDao.getPersonById(personId);
		Branch branch = branchDao.getBranchById(branchId);
		Response<Encounter> response = new Response<>();
		ArrayList<Branch> branches = new ArrayList<>();
		branches.add(branch);
		encounter.setBranches(branches);
		encounter.setPerson(person);
		response.setMessage("Successfully saved");
		response.setData(encounterDao.saveEncounter(encounter));
		response.setHttpStatus(HttpStatus.FOUND.value());

		return new ResponseEntity<Response<Encounter>>(response, HttpStatus.FOUND);
	}

	public ResponseEntity<Response<Encounter>> updateEncounter(Encounter encounter, int encounterId, int branchId) {
		Encounter dbEncounter = encounterDao.getEncounterById(encounterId);
		Branch branch = branchDao.getBranchById(branchId);
		List<Branch> branches = dbEncounter.getBranches();
		branches.add(branch);
		if (encounterDao.updateEncounter(encounterId, dbEncounter) != null) {
			encounter.setBranches(branches);
			encounter.setPerson(dbEncounter.getPerson());
			Response<Encounter> response = new Response<>();
			response.setMessage("Successfully updated");
			response.setData(encounterDao.updateEncounter(encounterId, encounter));
			response.setHttpStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<Response<Encounter>>(response, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("ID not found");
		}
	}

	public ResponseEntity<Response<Encounter>> deleteEncounter(int encounterId) {
		Encounter dbEncounter = encounterDao.getEncounterById(encounterId);
		if (dbEncounter != null) {
			Response<Encounter> response = new Response<Encounter>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(encounterDao.deleteEncounter(encounterId));

			return new ResponseEntity<Response<Encounter>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");

		}
	}

	public ResponseEntity<Response<Encounter>> getEncounterById(int encounterId) {
		Encounter daoEncounter = encounterDao.getEncounterById(encounterId);
		if (daoEncounter != null) {
			Response<Encounter> response = new Response<Encounter>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(daoEncounter);

			return new ResponseEntity<Response<Encounter>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Given Id Not Found");

		}
	}
}

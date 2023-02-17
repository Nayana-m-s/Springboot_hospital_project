package com.ty.springBoot_Hospital.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springBoot_Hospital.dto.Encounter;
import com.ty.springBoot_Hospital.repository.EncounterRepo;

@Repository
public class EncounterDao {
	@Autowired
	private EncounterRepo encounterRepo;

	public Encounter saveEncounter(Encounter encounter) {
		return encounterRepo.save(encounter);
	}

	public Encounter updateEncounter(int encounterId, Encounter encounter) {
		if (encounterRepo.findById(encounterId).isPresent()) {
			Encounter getEncounter = encounterRepo.findById(encounterId).get();
		
			encounter.setId(encounterId);
			
			return encounterRepo.save(encounter );
		} else {
			return null;
		}
	}

	public Encounter getEncounterById(int encounterId) {
		return encounterRepo.findById(encounterId).get();
	}

	public Encounter deleteEncounter(int encounterId) {
		if (encounterRepo.findById(encounterId).isPresent()) {
			Encounter encounter = encounterRepo.findById(encounterId).get();
			encounterRepo.delete(encounter);
			return encounter;
		} else {
			return null;
		}
	}
}

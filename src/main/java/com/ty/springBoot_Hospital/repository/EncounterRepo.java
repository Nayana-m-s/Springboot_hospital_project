package com.ty.springBoot_Hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springBoot_Hospital.dto.Encounter;

public interface EncounterRepo extends JpaRepository<Encounter, Integer>{

}

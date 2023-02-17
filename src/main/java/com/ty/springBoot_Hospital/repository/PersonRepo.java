package com.ty.springBoot_Hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.springBoot_Hospital.dto.Person;

public interface PersonRepo extends JpaRepository<Person, Integer>{

}

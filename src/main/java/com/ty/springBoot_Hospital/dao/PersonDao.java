package com.ty.springBoot_Hospital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springBoot_Hospital.dto.Person;
import com.ty.springBoot_Hospital.repository.PersonRepo;

@Repository
public class PersonDao {
	@Autowired
	private PersonRepo personRepo;

	public Person savePerson(Person person) {
		return personRepo.save(person);
	}

	public Person updatePerson(int id, Person person) {
		if (personRepo.findById(id).isPresent()) {
			person.setId(id);
			return personRepo.save(person);
		} else {
			return null;
		}
	}

	public Person deletePerson(int id) {
		if (personRepo.findById(id).isPresent()) {
			Person person = personRepo.findById(id).get();
			personRepo.delete(person);
			return person;
		} else {
			return null;
		}
	}

	public Person getPersonById(int id) {
		if (personRepo.findById(id).isPresent()) {
			return personRepo.findById(id).get();
		} else {
			return null;
		}
	}

	public List<Person> getAllPerson() {
		return personRepo.findAll();
	}
}

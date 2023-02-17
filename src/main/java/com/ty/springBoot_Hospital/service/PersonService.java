package com.ty.springBoot_Hospital.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.PersonDao;
import com.ty.springBoot_Hospital.dto.Person;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class PersonService {
	@Autowired
	private PersonDao personDao;

	public ResponseEntity<Response<Person>> savePerson(Person person) {
		personDao.savePerson(person);
		Response<Person> response = new Response<>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(person);
		return new ResponseEntity<Response<Person>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Person>> updatePerson(int id, Person person) {
		Person dbPerson = personDao.updatePerson(id, person);
		if (dbPerson != null) {
			Response<Person> response = new Response<Person>();
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(person);

			return new ResponseEntity<Response<Person>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");
		}
	}

	public ResponseEntity<Response<Person>> deletePerson(int id) {
		Person dbPerson = personDao.deletePerson(id);
		if (dbPerson != null) {
			Response<Person> response = new Response<Person>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(dbPerson);

			return new ResponseEntity<Response<Person>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");

		}
	}

	public ResponseEntity<Response<Person>> getPersonById(int id) {
		Person dbPerson = personDao.getPersonById(id);
		if (dbPerson != null) {
			Response<Person> response = new Response<Person>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(dbPerson);

			return new ResponseEntity<Response<Person>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Given Id Not Found");

		}
	}
}

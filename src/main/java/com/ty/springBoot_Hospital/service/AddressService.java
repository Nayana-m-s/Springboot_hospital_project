package com.ty.springBoot_Hospital.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.AddressDao;
import com.ty.springBoot_Hospital.dto.Address;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class AddressService {
	@Autowired
	private AddressDao dao;

	public ResponseEntity<Response<Address>> saveAddress(Address address) {
		Response<Address> response = new Response<Address>();
		response.setMessage("successfully saved");
		response.setHttpStatus(HttpStatus.CREATED.value());
		response.setData(dao.saveAddress(address));

		return new ResponseEntity<Response<Address>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<Response<Address>> updateAddress(int id, Address address) {

		Address daoAddress = dao.updateAddress(id, address);
		if (daoAddress != null) {
			Response<Address> response = new Response<Address>();
			response.setMessage("successfully updated");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(daoAddress);

			return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");
		}

	}

	public ResponseEntity<Response<Address>> deleteAddress(int id) {
		Address daoAddress = dao.deleteAddress(id);
		if (daoAddress != null) {
			Response<Address> response = new Response<Address>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(daoAddress);

			return new ResponseEntity<Response<Address>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");

		}
	}

	public ResponseEntity<Response<Address>> getAddressById(int id) {
		Address daoAddress = dao.getAddressById(id);
		if (daoAddress != null) {
			Response<Address> response = new Response<Address>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(daoAddress);

			return new ResponseEntity<Response<Address>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Given Id Not Found");
		}
	}
}

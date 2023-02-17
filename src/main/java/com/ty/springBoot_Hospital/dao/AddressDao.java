package com.ty.springBoot_Hospital.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springBoot_Hospital.dto.Address;
import com.ty.springBoot_Hospital.repository.AddressRepo;

@Repository
public class AddressDao {
	@Autowired
	private AddressRepo addressRepo;

	public Address saveAddress(Address address) {
		return addressRepo.save(address);
	}

	public Address updateAddress(int id, Address address) {
		if (addressRepo.findById(id).isPresent()) {
			address.setId(id);
			return addressRepo.save(address);
		} else {
			return null;
		}
	}

	public Address deleteAddress(int id) {
		if (addressRepo.findById(id).isPresent()) {
			Address address = addressRepo.findById(id).get();
			addressRepo.delete(address);
			return address;
		} else {
			return null;
		}
	}

	public Address getAddressById(int id) {
		if (addressRepo.findById(id).isPresent()) {
			return addressRepo.findById(id).get();
		} else {
			return null;
		}
	}

	public List<Address> getAllAddress() {
		return addressRepo.findAll();
	}
}

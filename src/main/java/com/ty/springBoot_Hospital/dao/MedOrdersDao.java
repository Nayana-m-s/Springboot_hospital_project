package com.ty.springBoot_Hospital.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springBoot_Hospital.dto.MedOrders;
import com.ty.springBoot_Hospital.repository.MedOrdersRepo;

@Repository
public class MedOrdersDao {
	@Autowired
	private MedOrdersRepo medOrdersRepo;

	public MedOrders saveMedOrders(MedOrders medOrders) {
		return medOrdersRepo.save(medOrders);
	}

	public MedOrders updateMedOrders(int medOrdersId, MedOrders medOrders) {
		if (medOrdersRepo.findById(medOrdersId).isPresent()) {
			medOrders.setId(medOrdersId);
			return medOrdersRepo.save(medOrders);
		} else {
			return null;
		}
	}

	public MedOrders getMedOrdersById(int medOrdersId) {
		return medOrdersRepo.findById(medOrdersId).get();
	}

	public MedOrders deleteMedOrders(int medOrdersId) {
		if (medOrdersRepo.findById(medOrdersId).isPresent()) {
			MedOrders medOrders = medOrdersRepo.findById(medOrdersId).get();
			medOrdersRepo.delete(medOrders);
			return medOrders;
		} else {
			return null;
		}
	}
}

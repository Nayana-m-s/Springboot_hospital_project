package com.ty.springBoot_Hospital.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.EncounterDao;
import com.ty.springBoot_Hospital.dao.MedOrdersDao;
import com.ty.springBoot_Hospital.dto.Encounter;
import com.ty.springBoot_Hospital.dto.MedOrders;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class MedOrdersService {
	@Autowired
	private MedOrdersDao medOrdersDao;
	@Autowired
	private EncounterDao encounterDao;

	public ResponseEntity<Response<MedOrders>> saveMedOrders(MedOrders medOrders, int encounterId) {
		Encounter encounter = encounterDao.getEncounterById(encounterId);
		Response<MedOrders> response = new Response<>();
		medOrders.setEncounter(encounter);
		response.setMessage("Successfully saved");
		response.setData(medOrdersDao.saveMedOrders(medOrders));
		response.setHttpStatus(HttpStatus.FOUND.value());

		return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.FOUND);
	}

	public ResponseEntity<Response<MedOrders>> updateMedOrders(MedOrders medOrders, int medOrdersId, int encounterId) {
		Encounter encounter = encounterDao.getEncounterById(encounterId);
		Response<MedOrders> response = new Response<>();
		if (medOrdersDao.updateMedOrders(medOrdersId, medOrders) != null) {
			medOrders.setEncounter(encounter);
			response.setMessage("Successfully updated");
			response.setData(medOrdersDao.updateMedOrders(medOrdersId, medOrders));
			response.setHttpStatus(HttpStatus.FOUND.value());

			return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Given ID not found");
		}
	}

	public ResponseEntity<Response<MedOrders>> deleteMedOrder(int medOrderId) {
		MedOrders dbMedOrder = medOrdersDao.getMedOrdersById(medOrderId);
		if (dbMedOrder != null) {
			Response<MedOrders> response = new Response<>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(medOrdersDao.deleteMedOrders(medOrderId));

			return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");
		}
	}

	public ResponseEntity<Response<MedOrders>> getMedOrdersById(int medOrderId) {
		MedOrders dbMedOrder = medOrdersDao.getMedOrdersById(medOrderId);
		if (dbMedOrder != null) {
			Response<MedOrders> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(dbMedOrder);

			return new ResponseEntity<Response<MedOrders>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Given Id Not Found");
		}
	}
}

package com.ty.springBoot_Hospital.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.springBoot_Hospital.Exception.IdNotFoundException;
import com.ty.springBoot_Hospital.dao.MedItemsDao;
import com.ty.springBoot_Hospital.dao.MedOrdersDao;
import com.ty.springBoot_Hospital.dto.MedItems;
import com.ty.springBoot_Hospital.dto.MedOrders;
import com.ty.springBoot_Hospital.util.Response;

@Service
public class MedItemsService {
	@Autowired
	private MedOrdersDao medOrdersDao;
	@Autowired
	private MedItemsDao medItemsDao;

	public ResponseEntity<Response<MedItems>> saveMedItems(MedItems medItems, int medOrderId) {
		MedOrders medOrders = medOrdersDao.getMedOrdersById(medOrderId);
		Response<MedItems> response = new Response<>();
		medItems.setMedOrders(medOrders);
		response.setMessage("Successfully saved");
		response.setData(medItemsDao.saveMedItems(medItems));
		response.setHttpStatus(HttpStatus.FOUND.value());

		return new ResponseEntity<Response<MedItems>>(response, HttpStatus.FOUND);
	}

	public ResponseEntity<Response<MedItems>> updateMedItems(MedItems medItems, int medItemId, int medOrderId) {
		MedOrders medOrders = medOrdersDao.getMedOrdersById(medOrderId);
		Response<MedItems> response = new Response<>();
		if (medItemsDao.updateMedItems(medItemId, medItems) != null) {
			medItems.setMedOrders(medOrders);
			response.setMessage("Successfully updated");
			response.setData(medItemsDao.updateMedItems(medItemId, medItems));
			response.setHttpStatus(HttpStatus.FOUND.value());

			return new ResponseEntity<Response<MedItems>>(response, HttpStatus.FOUND);
		} else {
			throw new IdNotFoundException("Given ID not found");
		}
	}

	public ResponseEntity<Response<MedItems>> deleteMedItems(int medItemId) {
		MedItems dbMedItem = medItemsDao.getMedItemById(medItemId);
		if (dbMedItem != null) {
			Response<MedItems> response = new Response<>();
			response.setMessage("successfully deleted");
			response.setHttpStatus(HttpStatus.OK.value());
			response.setData(medItemsDao.deleteMedItem(medItemId));

			return new ResponseEntity<Response<MedItems>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Given Id Not Found");
		}
	}

	public ResponseEntity<Response<MedItems>> getMedItemById(int medItemId) {
		MedItems dbMedItem = medItemsDao.getMedItemById(medItemId);
		if (dbMedItem != null) {
			Response<MedItems> response = new Response<>();
			response.setMessage("Found");
			response.setHttpStatus(HttpStatus.FOUND.value());
			response.setData(dbMedItem);

			return new ResponseEntity<Response<MedItems>>(response, HttpStatus.FOUND);
		} else {
			throw new NoSuchElementException("Given Id Not Found");
		}
	}
}

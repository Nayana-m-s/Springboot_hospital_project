package com.ty.springBoot_Hospital.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.springBoot_Hospital.dto.MedItems;
import com.ty.springBoot_Hospital.repository.MedItemsRepo;

@Repository
public class MedItemsDao {
	@Autowired
	private MedItemsRepo medItemsRepo;

	public MedItems saveMedItems(MedItems medItems) {
		return medItemsRepo.save(medItems);
	}

	public MedItems updateMedItems(int medItemId, MedItems medItems) {
		if (medItemsRepo.findById(medItemId).isPresent()) {
			medItems.setId(medItemId);
			return medItemsRepo.save(medItems);
		} else {
			return null;
		}
	}

	public MedItems getMedItemById(int medItemId) {
		return medItemsRepo.findById(medItemId).get();
	}

	public MedItems deleteMedItem(int medItemId) {
		if (medItemsRepo.findById(medItemId).isPresent()) {
			MedItems medItems = medItemsRepo.findById(medItemId).get();
			medItemsRepo.delete(medItems);
			return medItems;
		} else {
			return null;
		}
	}
}

package com.cognizant.drugsmicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.drugsmicroservice.exception.DrugNotFoundException;
import com.cognizant.drugsmicroservice.exception.InvalidTokenException;
import com.cognizant.drugsmicroservice.feign.AuthorizationClient;
import com.cognizant.drugsmicroservice.model.Drugs;
import com.cognizant.drugsmicroservice.model.DrugsLocation;
import com.cognizant.drugsmicroservice.model.Stock;
import com.cognizant.drugsmicroservice.repository.DrugsLocationRepo;
import com.cognizant.drugsmicroservice.repository.DrugsRepo;

@Service
public class DrugsService {

	@Autowired
	private DrugsRepo drugRepo;
	@Autowired
	private DrugsLocationRepo locationRepo;

	@Autowired
	private AuthorizationClient authorizationClient;

	public List<Drugs> getAllDrugs() {
		return drugRepo.findAll();
	}

	public Drugs getDrugById(String id, String token) throws InvalidTokenException, DrugNotFoundException {
		if (authorizationClient.getValidity(token)) {
			Optional<Drugs> opt = drugRepo.findById(id);
			if (opt.isEmpty()) {
				throw new DrugNotFoundException();
			}
			return opt.get();
		}
		throw new InvalidTokenException("Invalid Credentials");
	}

	public Drugs getDrugByName(String name, String token) throws InvalidTokenException, DrugNotFoundException {
		if (authorizationClient.getValidity(token)) {
			Optional<Drugs> opt = drugRepo.findBydrugName(name);
			if (opt.isEmpty()) {
				throw new DrugNotFoundException();
			}
			return opt.get();
		}
		throw new InvalidTokenException("Invalid Credentials");
	}

	public Stock getDispatchableDrugStock(String id, String location, String token)
			throws InvalidTokenException, DrugNotFoundException {
		if (authorizationClient.getValidity(token)) {
			Optional<Drugs> opt = drugRepo.findById(id);
			if (opt.isEmpty()) {
				throw new DrugNotFoundException();
			}
			Drugs drug = opt.get();
			Optional<DrugsLocation> opt1 = locationRepo.findByDrugIdAndLocation(id, location);
			int availableStock = 0;
			if (opt1.isPresent()) {
				availableStock = opt1.get().getQuantity();
			}
			Stock stock = new Stock(drug.getDrugId(), location, drug.getDrugName(), drug.getExpiryDate(),
					availableStock);
			return stock;
		}
		throw new InvalidTokenException("Invalid Credentials");
	}

}

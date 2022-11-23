package com.cognizant.drugsmicroservice.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Stock {

	private String drugId;
	private String location;
	private String drugName;
	private Date expiryDate;
	private int quantity;

}

/*
 * Drug ID, Name, Expiry Date, Available Stock in the given location
 */
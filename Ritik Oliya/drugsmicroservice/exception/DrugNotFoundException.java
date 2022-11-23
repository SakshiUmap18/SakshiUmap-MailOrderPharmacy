package com.cognizant.drugsmicroservice.exception;

public class DrugNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrugNotFoundException() {
		super("Drug Not found");
	}
}

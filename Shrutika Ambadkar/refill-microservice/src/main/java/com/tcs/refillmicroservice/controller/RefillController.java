package com.tcs.refillmicroservice.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.refillmicroservice.model.RefillOrder;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class RefillController {

	@ApiOperation(value = "View status of the subscriptions per id", response = RefillOrder.class)
	@GetMapping("/viewRefillStatus/{Subscription_ID}")
	public RefillOrder viewRefillStatus(@RequestHeader("Authorization") String token,
			@PathVariable int Subscription_ID) {
		return null;
	}

	@ApiOperation(value = "View refill dues as of date", response = RefillOrder.class)
	@GetMapping("/getRefillDuesAsOfDate/{Subscription_ID}/{fromDate}")
	public RefillOrder getRefillDuesAsOfDate(@RequestHeader("Authorization") String token,@PathVariable("Subscription_ID") int Subscription_ID,
			@PathVariable("fromDate") Date fromDate) {
		return null;
	}

	@ApiOperation(value = "Request refill", response = RefillOrder.class)
	@PostMapping("/requestAdhocRefill")
	public RefillOrder requestAdhocRefill(@RequestHeader("Authorization") String token
			//Input: Policy_ID, Member_ID, Subscription_ID, Location
			) {
		return null;
	}

}

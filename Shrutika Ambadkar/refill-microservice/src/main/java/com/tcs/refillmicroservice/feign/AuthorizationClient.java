package com.tcs.refillmicroservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "${authservice.name}", url = "${authservice.url}")
public interface AuthorizationClient {
	
	@RequestMapping(value = { "/validate" }, method = { RequestMethod.GET })
	boolean getValidity(@RequestHeader("Authorization") final String tokenHeader);
	
}

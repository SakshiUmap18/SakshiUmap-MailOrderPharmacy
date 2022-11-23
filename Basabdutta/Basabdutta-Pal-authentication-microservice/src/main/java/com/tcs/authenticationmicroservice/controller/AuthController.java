package com.cognizant.authenticationmicroservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.authenticationmicroservice.model.JwtRequestModel;
import com.cognizant.authenticationmicroservice.model.JwtResponseModel;
import com.cognizant.authenticationmicroservice.service.CustomerDetailsService;
import com.cognizant.authenticationmicroservice.service.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthController {
	@Autowired
	private JwtTokenUtil jwtTokenutil;
	@Autowired
	private CustomerDetailsService customerDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@GetMapping(path = "/health")
	public ResponseEntity<Object> healthCheckup() {
		LOGGER.info("Health Check ");
		return new ResponseEntity<>("", HttpStatus.OK);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<JwtResponseModel> login(@RequestBody JwtRequestModel request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		UserDetails userDetails = customerDetailsService.loadUserByUsername(request.getUsername());
		String jwtToken = jwtTokenutil.generateJwtToken(userDetails);
		return ResponseEntity.ok(new JwtResponseModel(jwtToken));
	}

	@GetMapping("/validate")
	public ResponseEntity<Boolean> validate(@RequestHeader(name = "Authorization") String tokenHeader) {
		if (tokenHeader == null || !tokenHeader.startsWith("Bearer ")) {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
		String token = tokenHeader.substring(7);
		try {
			UserDetails user = customerDetailsService.loadUserByUsername(jwtTokenutil.getUsernameFromToken(token));

			if (jwtTokenutil.validateJwtToken(token, user)) {
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);
		}
	}
}

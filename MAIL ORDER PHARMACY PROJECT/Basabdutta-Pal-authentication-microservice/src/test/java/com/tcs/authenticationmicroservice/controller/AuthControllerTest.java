package com.cognizant.authenticationmicroservice.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.authenticationmicroservice.model.JwtRequestModel;
import com.cognizant.authenticationmicroservice.model.JwtResponseModel;
import com.cognizant.authenticationmicroservice.repository.UserRepo;
import com.cognizant.authenticationmicroservice.service.CustomerDetailsService;
import com.cognizant.authenticationmicroservice.service.JwtTokenUtil;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

	@Autowired
	AuthController authController;

	@Test
	void loginTest() throws Exception {
		JwtRequestModel user = new JwtRequestModel("admin1", "admin1");
		ResponseEntity<?> response = authController.login(user);
		assertEquals(200, response.getStatusCodeValue());
	}

	@Test
	void loginTestFailed() {
		JwtRequestModel user = new JwtRequestModel("admin", "admin");
		Exception e = assertThrows(Exception.class, () -> {
			ResponseEntity<?> response = authController.login(user);
			assertEquals(403, response.getStatusCodeValue()); // 403 forbidden
		});
	}

	@Test
	void validateTestValidtoken() throws Exception {

		JwtRequestModel user = new JwtRequestModel("admin2", "admin2");
		ResponseEntity<JwtResponseModel> response = authController.login(user);
		String token = response.getBody().getToken();
		System.out.println(token);
		ResponseEntity<?> validity = authController.validate("Bearer "+token);
		assertEquals(true, validity.getBody().toString().contains("true"));

	}

	@Test
	void validateTestInValidtoken() {
		ResponseEntity<?> validity = authController.validate("bearer token");
		assertEquals(true, validity.getBody().toString().contains("false"));
	}

	@Test
	void testhealth() {
		ResponseEntity<?> healthCheckup = authController.healthCheckup();
		assertEquals(200, healthCheckup.getStatusCodeValue());

	}

}

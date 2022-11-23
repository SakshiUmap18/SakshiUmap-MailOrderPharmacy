package com.cognizant.authenticationmicroservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JwtRequestModelTest extends JwtRequestModel {

	private JwtRequestModel jwtRequestModel = new JwtRequestModel();

	@Test
	void testCheckUsername() {
		jwtRequestModel.setUsername("user");
		assertEquals("user", jwtRequestModel.getUsername());
	}

	@Test
	void testCheckPassword() {
		jwtRequestModel.setPassword("password");
		assertEquals("password", jwtRequestModel.getPassword());
	}

}

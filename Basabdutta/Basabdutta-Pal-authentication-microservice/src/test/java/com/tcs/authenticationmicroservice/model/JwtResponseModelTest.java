package com.cognizant.authenticationmicroservice.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JwtResponseModelTest {
	
	private JwtResponseModel jwtResponseModel = new JwtResponseModel("dummy");

	@Test
	void testCheckToken() {
		jwtResponseModel.setToken("token");
		assertEquals( "token", jwtResponseModel.getToken());
	}

}

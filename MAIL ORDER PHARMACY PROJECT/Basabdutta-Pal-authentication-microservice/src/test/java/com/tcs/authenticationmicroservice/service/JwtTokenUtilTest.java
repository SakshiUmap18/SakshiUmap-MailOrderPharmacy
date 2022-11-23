package com.cognizant.authenticationmicroservice.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.cognizant.authenticationmicroservice.repository.UserRepo;

@SpringBootTest
class JwtTokenUtilTest {

	@Autowired
	JwtTokenUtil jwtUtil;

	String generateToken = "";
	UserDetails userdetails;

	@BeforeEach
	void beforEach() {
		userdetails = new User("admin1", "admin1", new ArrayList<>());
		generateToken = jwtUtil.generateJwtToken(userdetails);
	}

	@Test
	void generateTokenTest() {
		assertNotNull(generateToken);
	}

	@Test
	void validateTokenTestWithValidToken() {
		Boolean validateToken = jwtUtil.validateJwtToken(generateToken, userdetails);
		assertEquals(true, validateToken);
	}

	@Test
	void validateTokenTestWithInvalidToken() {
		try {
			Boolean validateToken = jwtUtil.validateJwtToken("token", userdetails);
			assertEquals(false, validateToken);
		} catch (Exception e) {

		}
	}

	@Test
	void validateTokenWithNameTest() {
		Boolean validateToken = jwtUtil.validateJwtToken(generateToken, userdetails);
		assertEquals(true, validateToken);
	}

	@Test
	void getUsernameFromTokenTest() {
		assertEquals("admin1", jwtUtil.getUsernameFromToken(generateToken));
	}

}

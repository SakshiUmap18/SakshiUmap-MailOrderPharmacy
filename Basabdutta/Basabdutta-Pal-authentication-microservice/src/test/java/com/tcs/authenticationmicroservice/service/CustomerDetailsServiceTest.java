package com.cognizant.authenticationmicroservice.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@SpringBootTest
class CustomerDetailsServiceTest {

	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	

	@Test
	void testLoadUserByUsernameBadCredentials() {
		assertThatThrownBy(() -> customerDetailsService.loadUserByUsername("fake"))
				.isInstanceOf(UsernameNotFoundException.class);
	}

	@Test
	void testLoadUserByUsernameRightCredentials() {
		UserDetails userDetails = customerDetailsService.loadUserByUsername("admin1");
		assertEquals("admin1", userDetails.getUsername());
	}

}

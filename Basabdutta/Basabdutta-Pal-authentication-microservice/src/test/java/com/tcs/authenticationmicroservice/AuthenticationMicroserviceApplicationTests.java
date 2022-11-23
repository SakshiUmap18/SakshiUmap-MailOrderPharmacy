package com.cognizant.authenticationmicroservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuthenticationMicroserviceApplicationTests {

	@Test
	 void main() {
		AuthenticationMicroserviceApplication.main(new String[] {});
		assertTrue(true);
	}

}

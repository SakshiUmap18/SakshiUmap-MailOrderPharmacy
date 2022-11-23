package com.cognizant.drugsmicroservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DrugsMicroserviceApplicationTests {

	@Test
	void contextLoads() {
		DrugsMicroserviceApplication.main(new String[] {});
		assertTrue(true);
	}

}

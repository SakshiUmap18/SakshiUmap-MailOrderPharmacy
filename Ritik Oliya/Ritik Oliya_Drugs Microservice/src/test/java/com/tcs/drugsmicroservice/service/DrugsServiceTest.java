package com.cognizant.drugsmicroservice.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cognizant.drugsmicroservice.exception.DrugNotFoundException;
import com.cognizant.drugsmicroservice.exception.InvalidTokenException;
import com.cognizant.drugsmicroservice.feign.AuthorizationClient;
import com.cognizant.drugsmicroservice.model.Drugs;
import com.cognizant.drugsmicroservice.model.DrugsLocation;
import com.cognizant.drugsmicroservice.repository.DrugsLocationRepo;
import com.cognizant.drugsmicroservice.repository.DrugsRepo;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DrugsServiceTest {

	@InjectMocks
	DrugsService drugsService;
	@Mock
	AuthorizationClient authClient;
	@Mock
	private DrugsRepo drugsRepo;
	@Mock
	private DrugsLocationRepo locationRepo;
	Date date;
	List<DrugsLocation> list;
	Drugs drug;
	Drugs drug1;

	@BeforeEach
	void setUp() throws Exception {
		date = new Date();
		list = new ArrayList<DrugsLocation>();
		list.add(new DrugsLocation("1", "D1", 30, "Chennai"));
		drug = new Drugs("D1", "Paracetmol", "Lalitha", date, date, list);
		drug1 = new Drugs("D2", "Para", "Alembic", date, date, list);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	// TEST TO GET ALL DRUGS LIST
	@Test
	void testGetAllDrugs() {
		// given - precondition or setup
		given(drugsRepo.findAll()).willReturn(List.of(drug, drug1));

		// when - action or the behaviour that we are going test
		List<Drugs> drugsList = drugsService.getAllDrugs();

		// then - verify the output
		assertThat(drugsList).isNotNull();
		assertThat(drugsList.size()).isEqualTo(2);

	}

	// TEST FIND DRUG BY ID
	@Test
	void testGetDrugByIdWithProperTokenAndProperId() throws InvalidTokenException, DrugNotFoundException {
		// given
		String token = "";
		given(drugsRepo.findById("D1")).willReturn(Optional.of(drug));

		// when
		Drugs drug = drugsService.getDrugById("D1", token);

		// then
		assertThat(drug).isNotNull();

	}

	@Test
	void testGetDrugByIdWithWrongTokenAndProperId() throws InvalidTokenException, DrugNotFoundException {
		// given
		String token = "";
		given(drugsRepo.findById("D1")).willReturn(Optional.of(drug));

		// when
		Drugs drug = drugsService.getDrugById("D1", token);

		// then
		assertThat(drug).isNotNull();
	}

	@Test
	void testGetDrugByIdWithProperTokenAndWrongId() throws InvalidTokenException, DrugNotFoundException {
		// given
		String token = "";
		given(drugsRepo.findById("D1")).willReturn(Optional.of(drug));

		// when
		Drugs drug = drugsService.getDrugById("D1", token);

		// then
		assertThat(drug).isNotNull();
	}

	// TEST FIND DRUG BY NAME
	@Test
	void testGetDrugByNameWithProperTokenAndProperName() throws InvalidTokenException, DrugNotFoundException {
		// given
		String token = "";
		given(drugsRepo.findBydrugName("Paracetmol")).willReturn(Optional.of(drug));

		// when
		Drugs drug = drugsService.getDrugByName("D1", token);

		// then
		assertThat(drug).isNotNull();

	}

	@Test
	void testGetDrugByNameWithWrongTokenAndProperName() throws InvalidTokenException, DrugNotFoundException {
		// given
		String token = "";
		given(drugsRepo.findBydrugName("Paracetmol")).willReturn(Optional.of(drug));

		// when
		Drugs drug = drugsService.getDrugByName("Paracetmol", token);

		// then
		assertThat(drug).isNotNull();

	}

	@Test
	void testGetDrugByNameWithProperTokenAndWrongName() throws InvalidTokenException, DrugNotFoundException {
		// given
		String token = "";
		given(drugsRepo.findBydrugName("Paracetmol")).willReturn(Optional.of(drug));

		// when
		Drugs drug = drugsService.getDrugByName("Paracetmol", token);

		// then
		assertThat(drug).isNotNull();

	}

	// TEST FIND DRUG BY NAME AND LOCATION
	@Test
	void testGetDispatchableDrugStockWithWrongToken() {

	}

	@Test
	void testGetDispatchableDrugStockWithRightTokenWrongId() {

	}

	@Test
	void testGetDispatchableDrugStockWithRightTokenWrongLocation() {

	}

	@Test
	void testGetDispatchableDrugStockWithProperTokenAndProperDetails() {

	}

}

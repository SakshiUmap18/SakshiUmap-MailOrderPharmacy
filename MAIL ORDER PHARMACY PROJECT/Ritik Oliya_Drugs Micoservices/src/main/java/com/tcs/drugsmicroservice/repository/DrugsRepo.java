package com.cognizant.drugsmicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.drugsmicroservice.model.Drugs;

@Repository
public interface DrugsRepo extends JpaRepository<Drugs,String>  {

	Optional<Drugs> findBydrugName(String name);

}

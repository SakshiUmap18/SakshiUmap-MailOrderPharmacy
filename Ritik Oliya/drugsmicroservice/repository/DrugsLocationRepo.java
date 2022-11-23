package com.cognizant.drugsmicroservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.drugsmicroservice.model.DrugsLocation;


@Repository
public interface DrugsLocationRepo extends JpaRepository<DrugsLocation, String> {

	Optional<DrugsLocation> findByDrugIdAndLocation(String id, String location);

}

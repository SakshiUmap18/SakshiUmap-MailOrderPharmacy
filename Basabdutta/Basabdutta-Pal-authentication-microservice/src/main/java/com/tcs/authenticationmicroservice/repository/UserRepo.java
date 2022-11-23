package com.cognizant.authenticationmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.authenticationmicroservice.model.JwtRequestModel;

@Repository
public interface UserRepo extends JpaRepository<JwtRequestModel,String> {

}

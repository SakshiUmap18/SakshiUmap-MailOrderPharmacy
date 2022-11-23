package com.cognizant.authenticationmicroservice.service;

import java.util.ArrayList;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cognizant.authenticationmicroservice.model.JwtRequestModel;
import com.cognizant.authenticationmicroservice.repository.UserRepo;

@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			JwtRequestModel user = repo.findById(username).orElse(null);
			return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		} catch (Exception e) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
	}
}
package com.elearn.app.services;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.elearn.app.dtos.CustomUserDetails;
import com.elearn.app.entities.User;
import com.elearn.app.repositories.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	
	private UserRepo userRepo;
	
	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByEmail(username)
							.orElseThrow(()-> new BadCredentialsException("User Not Found"));
		
		return new CustomUserDetails(user);
	}

}

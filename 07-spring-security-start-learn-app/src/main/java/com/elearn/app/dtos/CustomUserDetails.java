package com.elearn.app.dtos;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.elearn.app.entities.User;

public class CustomUserDetails implements UserDetails {

	
	private User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	//role and authorization
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getEmail();
	}


}

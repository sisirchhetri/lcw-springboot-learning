package com.elearn.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearn.app.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

	
	//load/fetch user via user-name for spring-security UserService
	Optional<User> findByEmail(String email);
}

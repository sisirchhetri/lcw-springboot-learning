package com.elearn.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearn.app.entities.User;

public interface UserRepo extends JpaRepository<User, String> {

}

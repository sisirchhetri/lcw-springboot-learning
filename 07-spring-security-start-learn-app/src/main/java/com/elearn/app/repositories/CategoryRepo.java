package com.elearn.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearn.app.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String> {

}

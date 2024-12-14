package com.jpa.in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.hibernate.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}

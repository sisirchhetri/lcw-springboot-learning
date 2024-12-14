package com.jpa.hibernate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.hibernate.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}

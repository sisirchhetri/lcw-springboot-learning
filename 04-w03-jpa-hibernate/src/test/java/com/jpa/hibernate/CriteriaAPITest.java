package com.jpa.hibernate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.hibernate.entities.Category;
import com.jpa.hibernate.repositories.services.CategoryService;

@SpringBootTest
public class CriteriaAPITest {
	
	@Autowired
	CategoryService categoryService;
	
	
	@Test
	void getAllCategories() {
		
	List<Category> catList = categoryService.getAllCategories();
	
	catList.forEach(System.out::println);
	
	
	
	}

}

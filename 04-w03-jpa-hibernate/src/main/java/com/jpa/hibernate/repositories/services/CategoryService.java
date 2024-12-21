package com.jpa.hibernate.repositories.services;

import java.lang.reflect.Constructor;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa.hibernate.entities.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Service
public class CategoryService {
	
	
	EntityManager entityManager;
	
	//Constructor Injection
	public CategoryService(EntityManager entityManager){
		this.entityManager = entityManager;
		
	}
	
	
	//Using Criteria API Here 
	public List<Category> getAllCategories(){
		
		//Get CriteriaBuilder 
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		//Create Criteria Query
		CriteriaQuery<Category> criteriaQuery = criteriaBuilder.createQuery(Category.class);
		
		//root entity
		Root<Category> root = criteriaQuery.from(Category.class);
		
		
		//Predicate
		Predicate predicate1 = criteriaBuilder.equal(root.get("title"), "trending");
		Predicate predicate2 = criteriaBuilder.equal(root.get("title"), "mobile phone");
		Predicate predicate3 = criteriaBuilder.or(predicate1, predicate2);
		Order order =criteriaBuilder.asc(root.get("title"));
		
		//Selection ----------
		criteriaQuery.select(root)
		             .where(predicate3)
		             .orderBy(order);
		
		
		//Optional Filtering
		
		
		
		//Build  & Execute Query
		List<Category> categoryList = entityManager.createQuery(criteriaQuery).getResultList();
		
		
		return categoryList;
		
		
	}

}

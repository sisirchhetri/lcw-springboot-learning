package com.jpa.hibernate.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="jpa_category")
public class Category {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String title;
		
/*	@OneToOne(mappedBy = "category")
	private Product product;
*/
	
	@OneToMany(mappedBy ="category", cascade = CascadeType.ALL)
	private List<Product> productList;
	
//	@ManyToMany(mappedBy ="category")
//	private List<Product> productList;
}

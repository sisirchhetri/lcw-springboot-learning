package com.jpa.hibernate.repositories.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa.hibernate.entities.Product;
import com.jpa.hibernate.repositories.ProductRepository;

@Service
public class ProductService {
	
	ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
		System.out.println(productRepository);
		System.out.println(productRepository.getClass().getClass());
	}
	
	
	public Product create(Product product) {
		Product saveProduct = productRepository.save(product);
		return saveProduct;
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	
	public Product byId(int productId) {
		Product product = productRepository.findById(productId).orElseThrow(()-> new RuntimeException("Product Not Found"));
		return product;
	}
	
	public void delete(int productId) {
		Product product = productRepository.findById(productId).get();
		productRepository.delete(product);
	}

}

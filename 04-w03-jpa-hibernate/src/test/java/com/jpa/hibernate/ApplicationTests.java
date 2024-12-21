package com.jpa.hibernate;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa.hibernate.entities.Category;
import com.jpa.hibernate.entities.Product;
import com.jpa.hibernate.repositories.ProductRepository;
import com.jpa.in.repositories.CategoryRepository;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("Starting The Test");
	}
	
	@Autowired
	private ProductRepository productRepository;
	
	@Test
	void testProductRepository() {
		System.out.println("Test Product Repository");
	 List<Product> productList = productRepository.findByTitleContaining("lg");
	 productList  = productRepository.findByTitleNotContaining("lg");
	 
	 productList.forEach(System.out::println);
	 
	}
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	void createCategory() {
		
		Category category = new Category();
		
		category.setId(1);
		category.setTitle("trending");
		categoryRepository.save(category);
		
		category = new Category();
		category.setId(2);
		category.setTitle("mobile phone");
		categoryRepository.save(category);
	}
	
	@Test
	void updateProduct() {
		
		productRepository.findById(1).ifPresentOrElse(product->{
			//if product present
			Category category = categoryRepository.findById(1).get();
			product.setCategory(category);
			productRepository.save(product);
			
		}, ()->{
			//If product not found
			System.out.println("Product 1 Not Found");
		});
		
		
		productRepository.findById(2).ifPresentOrElse(product->{
			//if product present
			Category category = categoryRepository.findById(1).get();
			product.setCategory(category);
			productRepository.save(product);
			
		}, ()->{
			//If product not found
			System.out.println("Product 2 Not Found");
		});
	}
	
	
	@Test
	void getCategoryByTitleTest() {
	
		List<Product> productList = productRepository.getProductByCategoryTitle("trending");
		productList.forEach(System.out::println);
		
		for(Product product : productList) {
			System.out.print("Product Title : " + product.getTitle() + " || ");
			System.out.print("Category Title : " + product.getCategory().getTitle());
			System.out.println();
		}
		
		
	}

}

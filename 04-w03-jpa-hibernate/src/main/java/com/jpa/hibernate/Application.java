package com.jpa.hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jpa.hibernate.entities.Product;
import com.jpa.hibernate.repositories.services.ProductService;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.jpa.in.repositories", "com.jpa.hibernate.repositories" })
public class Application implements CommandLineRunner{

	@Autowired
	ProductService productService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
	//	System.out.println("starting application...");
		Product  product = new Product();
		
		product.setTitle("LG TV");
		product.setDescription("This is a awesome TV");
		product.setPrice(22_000);
		product.setLive(true);
		
//		Product product1 =  productService.create(product);
//		System.out.println(product1);
		
		
		//productService.findAll().forEach(System.out::println);
		System.out.println(productService.byId(1));
		
		
		
	}
	
	

}

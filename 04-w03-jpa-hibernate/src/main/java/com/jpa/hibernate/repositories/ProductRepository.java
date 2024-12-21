package com.jpa.hibernate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jpa.hibernate.entities.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

	//1. Default - CRUD operations functions are there
	
	//2. Protocol - Custom Finder Methods 
	List<Product> findByPrice(double price);

	List<Product> findByTitleAndPrice(String title, double price);
	
	int countByPrice(double price);
	
	boolean existsByTitle(String title);
	
	
	//3. Custom Query Methods
	//@Query(value = "select p from Product p") //JPQL Query
	@Query(nativeQuery = true, 
		   value = "select * from jpa_products") //Native Query
	List<Product> getMyCustomNativeQueryProducts();
	
	@Query(
	"select p from Product p WHERE p.title=:title and p.price =:price")
	List<Product> getProductByTile(
			@Param("title") String title, 
			@Param("price") double price);
	
	
	// Custom Method For LIKE Query
	// select * from product where  title  like '%in'
	List<Product> findByTitleContaining(String keyword);
	
	//"%wildcard%"
	List<Product> findByTitleLike(String keywordWithWild);
	
	List<Product> findByTitleNotContaining(String title);
	
	//Method To Join Query And Fetch Results
	@Query("SELECT p FROM Product p JOIN  p.category where p.category.title =:catTitle")
	List<Product> getProductByCategoryTitle(@Param("catTitle") String title);
	
	

}

package com.elearn.app;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.elearn.app.entities.Course;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.services.CategoryService;

@SpringBootTest
class StartLearnAppApplicationTests {
	
	@Autowired
	private CategoryService categoryService;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void testAddCourseToCategory() {
		
		System.out.println("Test Start");
		categoryService.addCourseToCategory( "bfddbcc8-b768-410b-aaec-79ed70bfa232", "9202315d-86df-4b43-95b7-e95185e96d14");
		
		System.out.println("Test Done");
	}
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Test
	@Transactional
	public void testRelation() {
		System.out.println("Test Start : testRelation");
		
		List<Course> courseList = categoryRepo.findById( "bfddbcc8-b768-410b-aaec-79ed70bfa232")
					.get().getCourses();
		
		System.out.println(courseList);
		System.out.println("Test Done : testRelation");
	}

}

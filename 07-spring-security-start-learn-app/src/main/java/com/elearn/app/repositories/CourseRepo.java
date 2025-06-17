package com.elearn.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearn.app.entities.Course;

public interface CourseRepo extends JpaRepository<Course, String>{
	
	
	Optional<Course> findByTitle(String title);
	
	List<Course> findByLive(boolean live);
	
	//search the course by Title
	
	
	//get courses by findByCategoryId(String id)

}

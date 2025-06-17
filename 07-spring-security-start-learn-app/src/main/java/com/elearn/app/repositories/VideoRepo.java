package com.elearn.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearn.app.entities.Course;
import com.elearn.app.entities.Video;

public interface VideoRepo extends JpaRepository<Video, String>{
	
	
	Optional<Video> findByTitle(String title);
	
	List<Course> findByCourse(Course course);
	

}

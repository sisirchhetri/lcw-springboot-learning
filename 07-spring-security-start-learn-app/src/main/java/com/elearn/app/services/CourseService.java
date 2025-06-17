package com.elearn.app.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.ResourceContentType;

public interface CourseService {
	
	CourseDto create(CourseDto course);
	
	List<CourseDto> getAll();
	
	CourseDto update(CourseDto dto, String courseId);
	
	void delete(String courseId);
	
	List<CourseDto> searchByTitle(String titleKeyword);
	
	CourseDto get(String courseId);
	
	CourseDto saveBanner(MultipartFile file, String courseId) throws IOException;
	
	ResourceContentType getCourseBannerById(String courseId);

}

package com.elearn.app.services;

import java.util.List;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponseDto;

public interface CategoryService {

	
	CategoryDto insert(CategoryDto categoryDto);
	
	List<CategoryDto> getAll();
	
	CategoryDto get(String categoryId);
	
	void delete(String categoryId);
	
	CategoryDto update(CategoryDto categoryDto, String categoryId);
	
	CustomPageResponseDto<CategoryDto> getAll(int pageNo, int pageSize, String sortBy, String direc);
	
	//search
	
	
	void addCourseToCategory(String catId, String courseId);//added
	
	List<CourseDto> getCoursesOfCategory(String categoryId);
	
	
	
}

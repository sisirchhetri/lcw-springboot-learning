package com.elearn.app.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponseDto;
import com.elearn.app.exceptions.CustomMessage;
import com.elearn.app.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private CategoryService categoryService;
	
	public CategoryController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//category : create
	@PostMapping
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto 
										  categoryDto) {
		
		CategoryDto createdCategoryDto = this.categoryService.insert(categoryDto);
		// 201 [Created Response Code]
				
				
		return ResponseEntity.status(HttpStatus.CREATED)
					         .body(createdCategoryDto);
	}
	
	@GetMapping
	public List<CategoryDto> getAll(){
		return categoryService.getAll();
	}
	
	@GetMapping("{categoryId}")
	public CategoryDto getCategory(@PathVariable("categoryId") String catId) {
		
		return categoryService.get(catId);
		
	}
	
	//update and delete CATEGORY
	
	@PutMapping("{categoryId}")
	public CategoryDto updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable String categoryId) {
		
		return categoryService.update(categoryDto, categoryId);
	}
	
	
	@DeleteMapping("{categoryId}")
	public ResponseEntity<CustomMessage> deleteCategory(@PathVariable String categoryId){
		
		categoryService.delete(categoryId);
		
		CustomMessage customMsg = new CustomMessage();
		customMsg.setMessage("Category Deleted Successfully.");
		customMsg.setSuccess(true);
		
		return ResponseEntity.status(HttpStatus.OK).body(customMsg);
	}
	
	
	@GetMapping("/list")
	public CustomPageResponseDto<CategoryDto> getCategoryList
	(@RequestParam(name = "pageNo", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NO) int pgNo,
     @RequestParam(name = "pageSize",required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pgSize,
     @RequestParam(name = "sort", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY)String sortBy,
     @RequestParam(name = "direction",required = false, defaultValue = AppConstants.DEFAULT_SORT_DIRECTION)String direc){
		
		
	
		return categoryService.getAll(pgNo, pgSize, sortBy, direc);
	}
	
	
	//Added 
	@PostMapping("/{categoryId}/courses/{courseId}")
	public ResponseEntity<CustomMessage> addCourseToCategory(
			@PathVariable String categoryId,
			@PathVariable String courseId)
	{
		
		categoryService.addCourseToCategory(categoryId, courseId);
		
		return ResponseEntity.ok(new CustomMessage("Course Added To Category", true));
	}
	
	
	//Added
	@GetMapping("/{categoryId}/courses")
	public ResponseEntity<List<CourseDto>> getCoursesOfCategory(@PathVariable String categoryId){
		
		return ResponseEntity.ok(categoryService.getCoursesOfCategory(categoryId));
	}
	
}

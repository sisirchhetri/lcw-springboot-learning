package com.elearn.app.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.CustomPageResponseDto;
import com.elearn.app.entities.Category;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.CourseRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo;
	
	private CourseRepo courseRepo;

	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepo categoryRepo,CourseRepo courseRepo, ModelMapper modelMapper) {
		this.categoryRepo = categoryRepo;
		this.modelMapper = modelMapper;
		this.courseRepo = courseRepo;
	}

	@Override
	public CategoryDto insert(CategoryDto categoryDto) {

		// Generate Id
		String id = UUID.randomUUID().toString();
		categoryDto.setId(id);

		// date
		categoryDto.setAddedDate(new Date());

		// convert dto to entity
		Category category = modelMapper.map(categoryDto, Category.class);

		Category savedCategory = categoryRepo.save(category);

		// convert entity to dto
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAll() {

		List<Category> all = categoryRepo.findAll();

		List<CategoryDto> allDto = all.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();

		return allDto;
	}

	@Override
	public CategoryDto get(String categoryId) {

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));

		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void delete(String categoryId) {
		categoryRepo.findById(categoryId)
					.orElseThrow(() -> new ResourceNotFoundException("Category Not Found."));

		categoryRepo.deleteById(categoryId);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, String categoryId) {

		Category updatedCategory = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category Not Found."));

		updatedCategory.setId(categoryId);
		updatedCategory.setTitle(categoryDto.getTitle());
		updatedCategory.setDesc(categoryDto.getDesc());

		categoryRepo.save(updatedCategory);

		return modelMapper.map(updatedCategory, CategoryDto.class);
	}

	@Override //List<CategoryDto> 
	public CustomPageResponseDto<CategoryDto> getAll(int pageNo, int pageSize, String sortBy, String direction) {
		
		
		Sort sort = Sort.by(sortBy); 
		sort = direction.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		
		
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
		
		Page<Category> categoryPage = categoryRepo.findAll(pageRequest);
		
		List<Category> all = categoryPage.getContent();        //categoryPage.toList();

		List<CategoryDto> allDto = all.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();
		
		CustomPageResponseDto<CategoryDto> pageResponseDto= new CustomPageResponseDto<>();
		pageResponseDto.setContent(allDto);
		pageResponseDto.setPageNumber(pageNo);
		pageResponseDto.setPageSize(pageSize);
		pageResponseDto.setTotalElements((int)categoryPage.getTotalElements());
		pageResponseDto.setTotalPages(categoryPage.getTotalPages());
		pageResponseDto.setLast(categoryPage.isLast());
		

		return pageResponseDto;
	}

	@Override
	@Transactional
	public void addCourseToCategory(String catId, String courseId) {
		
	 Category category = this.categoryRepo
			 				.findById(catId)
			 				.orElseThrow(
			 				 () -> new ResourceNotFoundException("Category Not Found.")
			 				 );
	
	 
		
		  Course course = this.courseRepo .findById(courseId) .orElseThrow( () -> new
		  ResourceNotFoundException("Course Not Found.") );
		  
		  category.addCourse(course);
		  
		  Category savedCategoryWithCourse = this.categoryRepo.save(category);
	 
	}

	//Added
	@Override
	@Transactional
	public List<CourseDto> getCoursesOfCategory(String categoryId) {
		
		Category category = this.categoryRepo
 				.findById(categoryId)
 				.orElseThrow(
 				 () -> new ResourceNotFoundException("Category Not Found.")
 				 );
		
		List<Course> courseList =category.getCourses();
/*		
		List<CourseDto> courseDtoList  = new ArrayList<>();
		for (Course course : courseList) {
			System.out.println(course.getTitle());
			
			courseDtoList.add(modelMapper.map(course,CourseDto.class));
		}
*/		
		
		return courseList.stream()
				  .map((course)-> modelMapper.map(course, CourseDto.class))
				  .toList();
	}
	
	

}

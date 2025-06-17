package com.elearn.app.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.ResourceContentType;
import com.elearn.app.entities.Category;
import com.elearn.app.entities.Course;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CategoryRepo;
import com.elearn.app.repositories.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepo courseRepo;

	private CategoryRepo categoryRepo;

	private ModelMapper modelMapper;
	
	private FileService fileService;

	public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper,CategoryRepo categoryRepo, FileService fileService  ) {
		this.courseRepo = courseRepo;
		this.modelMapper = modelMapper;
		this.categoryRepo = categoryRepo;
		this.fileService = fileService;
	}

	@Override
	public CourseDto create(CourseDto courseDto) {

		List<CategoryDto> addValidCategories = new ArrayList<>();
		
		//Case 1: No category ID passed  
		
		if(courseDto.getCategoryList() == null  || courseDto.getCategoryList().isEmpty()) {
			
			courseDto.setCategoryList(addValidCategories); //Add Empty Category List
		}
		
		// Case 2: Category ID exists
		else {
			for (CategoryDto catRef : courseDto.getCategoryList()) {
				
				if(catRef.getId() == null) {
					throw new ResourceNotFoundException("Category Must Have an Id");
				}
				Category cat = categoryRepo.findById(catRef.getId())
						.orElseThrow(() -> new ResourceNotFoundException("Category Not Found"));
				
				addValidCategories.add(modelMapper.map(cat, CategoryDto.class));
			}
			
			courseDto.setCategoryList(addValidCategories);
		}
		

		String id = UUID.randomUUID().toString();
		courseDto.setId(id);

		Date createdDate = new Date();
		courseDto.setCreatedDate(createdDate);

		Course savedCourse = courseRepo.save(this.dtoToEntity(courseDto));

		return entityToDto(savedCourse);
	}

	@Override
	public List<CourseDto> getAll() {

		List<Course> courseList = courseRepo.findAll();

		// Convert All Course in courseDto
		List<CourseDto> courseDtoList = courseList.stream().map((course) -> entityToDto(course))
				.collect(Collectors.toList());

		return courseDtoList;
	}

	
	@Override
	public CourseDto update(CourseDto dto, String courseId) {
		
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));
		
        course.setPrice(dto.getPrice());
        course.setLongDesc(dto.getLongDesc());
		course.setShortDesc(dto.getShortDesc());
		
		return entityToDto(courseRepo.save(course));
	}

	@Override
	public void delete(String courseId) {
		@SuppressWarnings("unused")
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));

		courseRepo.deleteById(courseId);
	}

	@Override
	public List<CourseDto> searchByTitle(String titleKeyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CourseDto get(String courseId) {
		
	  Course course = courseRepo.findById(courseId)
			      				.orElseThrow(
			      						()-> new ResourceNotFoundException("Course Not Found"));
	  return entityToDto(course);
	}

	public CourseDto entityToDto(Course course) {
//		CourseDto courseDto = new CourseDto();
//		courseDto.setId("1");

		CourseDto courseDto = modelMapper.map(course, CourseDto.class);

		return courseDto;
	}

	public Course dtoToEntity(CourseDto courseDto) {
//		Course course = new Course();
//		course.setId("1");

		Course course = modelMapper.map(courseDto, Course.class);

		return course;

	}

	@Override
	public CourseDto saveBanner(MultipartFile bannerFile, String courseId) throws IOException {
		
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course Not Found"));
		
		String bannerPath = fileService.save(bannerFile, AppConstants.COURSE_BANNER_UPLOAD_DIR,bannerFile.getOriginalFilename());
		
		course.setBannerName(bannerPath);
		course.setBannerContentType(bannerFile.getContentType());
		
		courseRepo.save(course);
		
		return modelMapper.map(course, CourseDto.class);
	}

	@Override
	public ResourceContentType getCourseBannerById(String courseId) {
		
		Course course = courseRepo.findById(courseId).orElseThrow(()-> new ResourceNotFoundException("Course Not Found"));
		String bannerUrl = course.getBannerName();
		
		Path path = Paths.get(bannerUrl);
		Resource resource = new FileSystemResource(path);
		
		ResourceContentType resContentType = new ResourceContentType();
		resContentType.setContentType(course.getBannerContentType());
		resContentType.setResource(resource);
		
		return resContentType;
	}


}

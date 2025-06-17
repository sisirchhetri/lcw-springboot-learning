package com.elearn.app.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.elearn.app.dtos.CourseDto;
import com.elearn.app.dtos.ResourceContentType;
import com.elearn.app.exceptions.CustomMessage;
import com.elearn.app.services.CourseService;
import com.elearn.app.services.FileService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

	CourseService courseService;
	FileService fileService;
	
	public CourseController(CourseService courseService,FileService fileService) {
		this.courseService = courseService;
		this.fileService = fileService;
	}


	@PostMapping
	public ResponseEntity<CourseDto> create(@RequestBody CourseDto courseDto ) {
		
		return ResponseEntity.status(HttpStatus.CREATED)
							 .body(courseService.create(courseDto));
	}


	@GetMapping
	public List<CourseDto> getAll() {
		Pageable pageable;
		return courseService.getAll();
	}
	
	@GetMapping("{courseId}")
	public ResponseEntity<CourseDto> getCategory(@PathVariable String courseId,
			@RequestHeader("Content-Type") String contentType,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			ServletContext context) {
		
		Enumeration<String>	headerNames = request.getHeaderNames();
		
		while(headerNames.hasMoreElements()) {
			
			String header = headerNames.nextElement();
			System.out.println("Header : "+ request.getHeader(header));
		}
		
		
		return  ResponseEntity.ok(courseService.get(courseId));
	}
	
	
	@PutMapping(value = "{courseId}")
	public ResponseEntity<CourseDto> update(@RequestBody CourseDto courseDto, @PathVariable String courseId) {
		
		return ResponseEntity.ok(courseService.update(courseDto, courseId));
	}
	
	@DeleteMapping("{courseId}")
	public ResponseEntity<CustomMessage> delete(@PathVariable String courseId) {
		
		courseService.delete(courseId);
		
		CustomMessage msg = new CustomMessage();
		msg.setMessage("Course Deleted Successfully.");
		msg.setSuccess(true);
		
		return ResponseEntity.status(HttpStatus.OK).body(msg);
	}
	
	@PostMapping("/{courseId}/banner")
	public ResponseEntity<Object> uploadBanner(@PathVariable String courseId,
			@RequestParam("banner") MultipartFile bannerFile) throws IOException{
		
		System.out.println(bannerFile.getContentType());
		System.out.println(bannerFile.getOriginalFilename());
		System.out.println(bannerFile.getName());
		System.out.println(bannerFile.getSize()/1024);
		
		List<String> mediaTypeList = Arrays.asList(MediaType.IMAGE_PNG_VALUE,
												   MediaType.IMAGE_JPEG_VALUE);
		//String bannerContentType = bannerFile.getContentType();
		if(bannerFile == null || !mediaTypeList.contains(bannerFile.getContentType())) {
			
			CustomMessage message = new CustomMessage();
			message.setMessage("Invalid File Type Or Empty File");
			message.setSuccess(false);
			return ResponseEntity.badRequest().body(message);
		}
		
		CourseDto courseDtoWithBanner = courseService.saveBanner(bannerFile, courseId);
		
//		fileService.save(bannerFile, AppConstants.COURSE_BANNER_UPLOAD_DIR,bannerFile.getOriginalFilename());
		
		return ResponseEntity.ok().body(courseDtoWithBanner);
	}
	
	
	@GetMapping("/{courseId}/banner")
	public ResponseEntity<Resource> serveBanner(@PathVariable String courseId){
		
		ResourceContentType resource = courseService.getCourseBannerById(courseId);
		
		return ResponseEntity.ok()
							 .contentType(MediaType.parseMediaType(resource.getContentType()))
							 .body(resource.getResource());
	}
	
}

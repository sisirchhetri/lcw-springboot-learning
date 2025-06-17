package com.elearn.app.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private String id;
	
	@NotEmpty(message = "title should not be empty")
	@Size(min = 3, max = 50, message = "title should have 3 - 50 characters")
	private String title;
	
	@NotEmpty(message = "description should not be empty")
	private String desc;
	
	@JsonFormat(shape = Shape.STRING, pattern = "dd-mm-yyyy")
	private Date addedDate;
	
	//private List<CourseDto> courses = new ArrayList<>();
	
}

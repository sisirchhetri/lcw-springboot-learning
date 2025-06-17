package com.elearn.app.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	
	private String id;
	
	@NotEmpty(message = "title should not be empty")
	@Size(min = 10, max = 50, message = "title should have 10 - 50 characters")
	private String title;
	
	@NotEmpty(message = "short description should not be empty")
	private String shortDesc;
	
	@NotEmpty(message = "long description not be empty")
//	@JsonIgnore
//	@JsonProperty
	private String longDesc;
	
	private double price;
	
	private boolean live = false;
	
	private double discount ;
	
	private Date createdDate;
	
	private String bannerName;
	
	private List<VideoDto> videos = new ArrayList<>();
	
	private List<CategoryDto> categoryList = new ArrayList<>();
	
	
	public String getBannerUrl() {
		
		return "http://localhost:8080/api/v1/courses/"+ id + "/banner";
	}
	
}

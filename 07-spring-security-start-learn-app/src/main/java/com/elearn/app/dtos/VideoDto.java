package com.elearn.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
	
	String videoId;
	
	String title;
	
	String desc;

	String filePath;
	
	String contentType;
	
//	CourseDto course; not required if videos are going why course needed.
}

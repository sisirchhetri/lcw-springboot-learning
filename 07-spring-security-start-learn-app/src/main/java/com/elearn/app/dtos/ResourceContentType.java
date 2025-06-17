package com.elearn.app.dtos;

import org.springframework.core.io.Resource;
import lombok.Data;

@Data
public class ResourceContentType {

	private Resource resource;
	private String contentType;
	
}

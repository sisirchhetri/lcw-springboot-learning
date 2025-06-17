package com.elearn.app.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponseDto<T> {

	private int pageNumber;
	
	private int pageSize;
	
	private int totalElements;
	
	private int totalPages;
	
	private boolean isLast;
	
	private List<T> content;
	
}

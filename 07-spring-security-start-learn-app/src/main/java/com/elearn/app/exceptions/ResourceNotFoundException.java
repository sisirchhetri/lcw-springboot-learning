package com.elearn.app.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException{

	
	public ResourceNotFoundException() {
		super("Resource Not Found!");
	}


	public ResourceNotFoundException(String message) {
		super(message);
	}

	
	

}

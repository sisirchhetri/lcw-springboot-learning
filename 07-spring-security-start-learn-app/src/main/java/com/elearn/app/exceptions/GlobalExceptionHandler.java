package com.elearn.app.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(exception = ResourceNotFoundException.class)
	public ResponseEntity<CustomMessage> handleResourceNotFoundException(ResourceNotFoundException ex){
		
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage(ex.getMessage());
		customMessage.setSuccess(false);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);
	}
	
	@ExceptionHandler(exception = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException ex){
		
		Map<String,String> errorMap = new HashMap<>();
		
		ex.getBindingResult()
		  .getAllErrors()
		  .forEach((objError)->{
			  
			 String fieldName = ((FieldError)objError).getField();
			 String errorMsg  = objError.getDefaultMessage();
			 errorMap.put("fieldName", fieldName);
			 errorMap.put("errorMsg", errorMsg);
		  });
		  ;
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
	}

}

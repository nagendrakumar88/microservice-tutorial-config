package com.lcwd.user.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lcwd.user.service.payload.ApiResponce;

@RestControllerAdvice
public class GlobalExeptionHelndeler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponce>handleResourceNotFoundExp(ResourceNotFoundException exp){
		
		String message = exp.getMessage();
		
		ApiResponce responce = ApiResponce.builder().message(message).success(false).status(HttpStatus.NOT_FOUND).build();
	
		return new ResponseEntity<ApiResponce>(responce,HttpStatus.OK);
	
	}

}

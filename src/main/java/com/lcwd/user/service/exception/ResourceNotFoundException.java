package com.lcwd.user.service.exception;

public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException() {
		
		super("Data not presesnt...");
		
		
	}

	public ResourceNotFoundException(String message) {
		
		super(message);
	}
}

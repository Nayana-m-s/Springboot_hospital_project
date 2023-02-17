package com.ty.springBoot_Hospital.Exception;

public class IdNotFoundException extends RuntimeException{
	
	private String message ="ID not found";
	
	public String getMessage() {
		return message;
	}
	public IdNotFoundException(String message) {
		super();
		this.message = message;
	}
	public IdNotFoundException() {
		super();
	}
}

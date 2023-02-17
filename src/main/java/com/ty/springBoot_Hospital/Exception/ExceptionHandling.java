package com.ty.springBoot_Hospital.Exception;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ty.springBoot_Hospital.util.Response;

@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<Response<String>> idNotFoundExceptionHandlerEntity(IdNotFoundException ex){
		Response<String> response = new Response<>();
		response.setMessage("Given id is not present");
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setData("No data found for given ID");
		return new ResponseEntity<Response<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Response<String>> NoSuchElementFoundExceptionHandlerEntity(NoSuchElementException no){
		Response<String> response = new Response<>();
		response.setMessage("Invalid ID");
		response.setHttpStatus(HttpStatus.NOT_FOUND.value());
		response.setData("No data found for given ID");
		return new ResponseEntity<Response<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ObjectError> error = ex.getAllErrors();
		Map<String, String> map =new  LinkedHashMap<String, String>();
		for (ObjectError objectError : error) {
			String fieldName = ((FieldError) objectError).getField();
			String message = ((FieldError) objectError).getDefaultMessage();
			map.put(fieldName, message);
		}
		return new ResponseEntity<Object>(map, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Response<String>> getException(ConstraintViolationException ex){
		Response<String> response = new Response<>();
		response.setMessage("Give valid input");
		response.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		return new ResponseEntity<Response<String>>(response,HttpStatus.BAD_REQUEST);
	}
}
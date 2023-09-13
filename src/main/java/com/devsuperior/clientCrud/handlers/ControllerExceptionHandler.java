package com.devsuperior.clientCrud.handlers;

import java.time.Instant;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.clientCrud.Exceptions.ResourceNotFoundException;
import com.devsuperior.clientCrud.dtos.CustomError;
import com.devsuperior.clientCrud.dtos.ValidationErrors;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		CustomError err = new CustomError(Instant.now()
										, status.value()
										, e.getMessage()
										,request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> database(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		ValidationErrors err = new ValidationErrors(Instant.now()
										, status.value()
										, "Data field invalid"
										,request.getRequestURI());
		for(FieldError f: e.getBindingResult().getFieldErrors()) {
			err.addError(f.getField(), f.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<CustomError> database(DataIntegrityViolationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.CONFLICT;
		CustomError err = new CustomError(Instant.now()
				, status.value()
				, e.getMessage()
				,request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	

}

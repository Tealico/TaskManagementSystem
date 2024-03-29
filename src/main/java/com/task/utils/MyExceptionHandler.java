package com.task.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.task.dto.ErrorFormat;
//import com.task.exception.GroupException;
//import com.task.exception.TaskException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;


@Component
@RestControllerAdvice
public class MyExceptionHandler {

	
//	@ExceptionHandler(value = { JwtException.class })
//	protected ResponseEntity<Object> handleJWTException(JwtException ex, WebRequest request) {
//		ErrorFormat errorBody = new ErrorFormat();
//		errorBody.setMessage("Unauthorized.");
//		errorBody.setSuggestion("Try to login first.");
//
//		return new ResponseEntity<>(errorBody, HttpStatus.UNAUTHORIZED);
//	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
			errors.put("error", "Validation Error");
		});
		return errors;
	}
	
	@ExceptionHandler(value = { RuntimeException.class })
	protected ResponseEntity<Object> handleCustomExceptions(RuntimeException ex, WebRequest request) {
		ErrorFormat errorBody = new ErrorFormat();
		errorBody.setMessage(ex.getMessage());
		errorBody.setSuggestion("Contact support.");

		return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
	}
}



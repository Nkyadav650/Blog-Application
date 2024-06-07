package com.G_Vichar.Blog.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.G_Vichar.Blog.Payload.ApiError;

@RestControllerAdvice
public class CustomExcepionHandler {

	@ExceptionHandler(BadCredentialException.class)
	public ResponseEntity<ApiError> BadCredentialExceptionHandler(BadCredentialException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Provide correct credentials !!");
		ApiError error = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.BAD_REQUEST);

	}
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiError> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Resource Not found !!");
		ApiError error = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ApiError> IdNotFoundExceptionHandler(IdNotFoundException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Id Not found !!");
		ApiError error = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<ApiError> AccessDeniedExceptionHandler(AccessDeniedException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Provide correct access details !!");
		ApiError error = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(AlreadyExistException.class)
	public ResponseEntity<ApiError> AlreadyExistExceptionHandler(AlreadyExistException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Provide Another user details !!");
		ApiError error = new ApiError(message, details, HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_ACCEPTABLE);

	}
	
	@ExceptionHandler(TokenExpiredException.class)
	public ResponseEntity<ApiError> handleTokenExpiredException(TokenExpiredException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Generate new token !!");
		ApiError error = new ApiError(message, details, HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_ACCEPTABLE);

	}
	
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ApiError> handleInvalidTokenException(InvalidTokenException ex) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Invalid Token , Provide correct token !!");
		ApiError error = new ApiError(message, details, HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
		return new ResponseEntity<ApiError>(error, HttpStatus.NOT_ACCEPTABLE);

	}
}

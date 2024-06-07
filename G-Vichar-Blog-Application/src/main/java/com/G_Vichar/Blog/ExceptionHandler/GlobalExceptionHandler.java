package com.G_Vichar.Blog.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.G_Vichar.Blog.Payload.ApiError;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Request Method is not Supported !!");
		ApiError error = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Media Type is not Supported !!");
		ApiError error = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());
		return ResponseEntity.badRequest().body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("Path varable is Missing!!");
		ApiError error = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(error);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		Map<String,Object> response = new HashMap<>();
		String message ="Method Argument not valid !!";
		Map<String,String> details = new HashMap<>();
				ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName=((FieldError)error).getField();
			String messages=error.getDefaultMessage();
			details.put(fieldName, fieldName+" "+messages);
		});
		
		response.put("Message", message);
		response.put("Details", details);
		response.put("timestamp", LocalDateTime.now());

		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
		List<String> details = new ArrayList<>();
		details.add("provide correct type !!");
		ApiError error = new ApiError(message, details, HttpStatus.BAD_REQUEST, LocalDateTime.now());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(error);
	}

}

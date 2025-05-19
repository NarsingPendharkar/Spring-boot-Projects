package org.umanage.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> handleAllExceptions(Exception ex){
		Map<String, Object>response=new HashMap<>();
		response.put("Error", ex.getMessage());
		response.put("TimeStamp", LocalDateTime.now());
		response.put("Errorcode", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("trackId", UUID.randomUUID().toString());
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	    Map<String, Object> response = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach((error) -> {
	        response.put(error.getField(), error.getDefaultMessage());
	    });
	    response.put("timestamp", LocalDateTime.now());
	    response.put("status", HttpStatus.BAD_REQUEST.value());
	    response.put("trackId", UUID.randomUUID().toString());
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Map<String,Object>>handleUsernotfound(UserNotFoundException ex){
		Map<String, Object> response=new HashMap<>();
		response.put("Error", ex.getMessage());
		response.put("TimeStamp", LocalDateTime.now());
		response.put("Errorcode", HttpStatus.NOT_FOUND.value());
		response.put("trackId", UUID.randomUUID().toString());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<Map<String, Object>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
	    Map<String, Object> error = new HashMap<>();
	    error.put("timestamp", LocalDateTime.now());
	    error.put("status", HttpStatus.CONFLICT.value()); 
	    error.put("error", "User Already Exists");
	    error.put("message", ex.getMessage());
	    error.put("trackId", UUID.randomUUID().toString());
	    return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}


}

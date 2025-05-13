package org.app.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.hibernate.persister.collection.mutation.RowMutationOperations.Values;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(EmployeeNotFound.class)
	public ResponseEntity<Map<String, Object>> employeeNotFoundException(EmployeeNotFound ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("timeStamp", LocalDateTime.now());
		response.put("status", HttpStatus.NOT_FOUND.value());
		response.put("errorCode", "EMPLOYEE_NOT_FOUND");
		response.put("errorType", "NOT_FOUND");
		response.put("traceId", UUID.randomUUID().toString());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Map<String, Object>> handleCommonException(Exception ex) {
		Map<String, Object> response = new HashMap<>();
		response.put("message", ex.getMessage());
		response.put("errorCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.put("timestamp", LocalDateTime.now());
		response.put("traceId", UUID.randomUUID().toString());
		response.put("errorType", "INTERNAL_SERVER_ERROR");
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
	    Map<String, Object> response = new HashMap<>();
	    ex.getBindingResult().getFieldErrors().forEach((error) -> {
	        response.put(error.getField(), error.getDefaultMessage());
	    });
	    response.put("timestamp", LocalDateTime.now());
	    response.put("status", HttpStatus.BAD_REQUEST.value());
	    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}

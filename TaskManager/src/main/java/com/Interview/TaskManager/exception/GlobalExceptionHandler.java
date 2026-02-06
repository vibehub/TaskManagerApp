package com.Interview.TaskManager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// 404 - Task not found
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ApiError> handleNotFound(TaskNotFoundException ex, HttpServletRequest req) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiError.of(404, "NOT_FOUND", ex.getMessage(), req.getRequestURI()));
	}

	// 400 - Bad request
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<ApiError> handleBadRequest(BadRequestException ex, HttpServletRequest req) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiError.of(400, "BAD_REQUEST", ex.getMessage(), req.getRequestURI()));
	}

	// 400 - Bean validation (@Valid)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest req) {

		String msg = ex.getBindingResult().getFieldErrors().stream().findFirst()
				.map(e -> e.getField() + " " + e.getDefaultMessage()).orElse("Validation failed");

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ApiError.of(400, "VALIDATION_ERROR", msg, req.getRequestURI()));
	}

	// 500 - Catch-all
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleGeneric(Exception ex, HttpServletRequest req) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiError.of(500, "INTERNAL_ERROR", "Something went wrong", req.getRequestURI()));
	}
}
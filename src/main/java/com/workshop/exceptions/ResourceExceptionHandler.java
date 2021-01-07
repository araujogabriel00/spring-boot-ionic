package com.workshop.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.workshop.resources.DataIntegrityException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		StandartError err = new StandartError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandartError> dataIntegrityException(DataIntegrityException e, HttpServletRequest request) {

		StandartError err = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandartError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação",
				System.currentTimeMillis());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());

		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandartError> authorization(AuthorizationException e, HttpServletRequest request) {

		StandartError err = new StandartError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandartError> amazonService(AmazonServiceException e, HttpServletRequest request) {

		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandartError err = new StandartError(code.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(code).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandartError> amazonClient(AmazonClientException e, HttpServletRequest request) {

		StandartError err = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandartError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {

		StandartError err = new StandartError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}
	
}

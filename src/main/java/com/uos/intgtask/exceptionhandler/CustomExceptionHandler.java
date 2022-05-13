package com.uos.intgtask.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.log4j.Log4j2;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	protected ResponseEntity<String> handleException(Exception exception) {
		log.warn("Exception Details - ErrorMessage : {}", exception);
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

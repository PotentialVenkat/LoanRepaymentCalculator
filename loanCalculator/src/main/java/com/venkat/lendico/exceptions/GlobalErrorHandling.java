package com.venkat.lendico.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalErrorHandling extends ResponseEntityExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	protected ErrorMessage handleBadRequest(BadRequestException ex) {
		return handleLetshareException(ex);
	}

	private ErrorMessage handleLetshareException(BadRequestException ex) {
		return new ErrorMessage(ex.getErrorCode(), ex.getMessage(), ex.getErrors());
	}

}
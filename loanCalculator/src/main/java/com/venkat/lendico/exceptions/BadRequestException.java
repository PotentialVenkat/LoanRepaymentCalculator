package com.venkat.lendico.exceptions;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private List<String> errors;
	private String errorCode;

	public BadRequestException(List<String> errors, String errorCode) {
		super(errors.toString());
		this.errors = errors;
		this.errorCode = errorCode;
	}

}
package com.venkat.lendico.exceptions;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorMessage {

	private String errorCode;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private List<String> errors;

	public ErrorMessage(String errorCode, String message, List<String> subErrors) {
		timestamp = LocalDateTime.now();
		this.errorCode = errorCode;
		this.message = message;
		this.errors = subErrors;
	}

}
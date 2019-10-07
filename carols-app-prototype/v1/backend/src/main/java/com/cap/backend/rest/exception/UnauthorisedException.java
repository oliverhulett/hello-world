package com.cap.backend.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: Make immutable w/ custom builder settings...

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorisedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnauthorisedException(String message) {
		super(message);
	}

	public UnauthorisedException(String message, Throwable cause) {
		super(message, cause);
	}
}
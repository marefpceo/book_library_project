package com.userdb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Registration failed. User might exists already.")
public class UserException extends RuntimeException {
	
	
	private static final long serialVersionUID = 6741249242872870017L;

	public UserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserException() {
		super();
	}
	
	public UserException(String message) {
		super(message);
	}
	
	
}

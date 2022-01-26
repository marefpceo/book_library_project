package com.booktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Registration failed. User might exists already.")
public class BookTrackerUserException extends RuntimeException {
	
	
	private static final long serialVersionUID = 6741249242872870017L;

	public BookTrackerUserException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BookTrackerUserException() {
		super();
	}
	
	public BookTrackerUserException(String message) {
		super(message);
	}
	
	
}

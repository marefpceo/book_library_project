package com.booktracker.exceptions;

public class BookTrackerUserException extends RuntimeException {
	
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

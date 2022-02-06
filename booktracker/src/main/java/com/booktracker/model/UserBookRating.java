package com.booktracker.model;

import java.io.Serializable;

public class UserBookRating implements Serializable {

	private static final long serialVersionUID = -3481943691509432576L;

	private String bookId;
	private String comment;
	private int rating;

	public UserBookRating() {

	}

	public UserBookRating(String bookId, String comment, int rating) {
		this.bookId = bookId;
		this.comment = comment;
		this.rating = rating;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return this.bookId + " " + this.comment + " " + this.rating;
	}
}

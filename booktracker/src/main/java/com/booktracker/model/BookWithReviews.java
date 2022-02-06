package com.booktracker.model;

import java.io.Serializable;
import java.util.List;

import com.bookshare.dataloader.data.model.Book;
import com.bookshare.dataloader.data.model.BookRating;

public class BookWithReviews implements Serializable{

	private static final long serialVersionUID = 2916260502890154467L;

	private Book bookDetails;
	
	private List<BookRating> ratings;
	
	public BookWithReviews() {
		
	}
	
	public BookWithReviews(Book bookDetails, List<BookRating> ratings) {
		this.bookDetails = bookDetails;
		this.ratings = ratings;
	}

	public Book getBookDetails() {
		return bookDetails;
	}

	public void setBookDetails(Book bookDetails) {
		this.bookDetails = bookDetails;
	}

	public List<BookRating> getRatings() {
		return ratings;
	}

	public void setRatings(List<BookRating> ratings) {
		this.ratings = ratings;
	}
	
	
}

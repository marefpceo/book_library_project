package com.bookshare.dataloader.data.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.CassandraType.Name;

@Table("book_rating_by_id")
public class BookRating implements Serializable {

	private static final long serialVersionUID = 8890001556199223434L;
	
	@PrimaryKey
	private BookUserComposite bookUserComposite;
	
	@Column("comment")
	@CassandraType(type = Name.TEXT)
	private String comments;
	
	@Column("rating")
	@CassandraType(type = Name.INT)
	private Integer rating;// out of 5

	public BookRating() {

	}

	public BookRating(BookUserComposite bookUserComposite, String comments, Integer rating) {
		this.bookUserComposite = bookUserComposite;
		this.comments = comments;
		this.rating = rating;
	}

	

	public BookUserComposite getBookUserComposite() {
		return bookUserComposite;
	}

	public void setBookUserComposite(BookUserComposite bookUserComposite) {
		this.bookUserComposite = bookUserComposite;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return this.comments + " " + this.rating;
	}
}

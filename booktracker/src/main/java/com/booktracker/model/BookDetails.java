package com.booktracker.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDetails implements Serializable{
	
	private static final long serialVersionUID = 2327553136795383688L;

	@JsonProperty("key")
	private String bookId;
	
	@JsonProperty("author_key")
	private List<String> authorID;
	
	@JsonProperty("author_name")
	private List<String> AuthorName;
	
	@JsonProperty("cover_i")
	private String coverId;
	
	@JsonProperty("title")
	private String title;
	
	private String bookDescription;
	
	public BookDetails() {
		
	} 
	
	
	public String getBookDescription() {
		return bookDescription;
	}


	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}


	@JsonProperty("description")
	private void unpackDescripttionValue(Map<String, String> description) {
		this.bookDescription = description.get("value");
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public List<String> getAuthorID() {
		return authorID;
	}

	public void setAuthorID(List<String> authorID) {
		this.authorID = authorID;
	}

	public List<String> getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(List<String> authorName) {
		AuthorName = authorName;
	}

	public String getCoverId() {
		return coverId;
	}

	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}	

}

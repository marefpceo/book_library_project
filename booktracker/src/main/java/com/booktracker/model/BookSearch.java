package com.booktracker.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookSearch implements Serializable{

	private static final long serialVersionUID = 3502730683693422701L;
	
	@JsonProperty("key")
	private String bookId;
	
	@JsonProperty("author_key")
	private List<String> authorID;
	
	@JsonProperty("author_name")
	private List<String> AuthorName;
	
	@JsonProperty("cover_i")
	private String coverId;
	
	private String coverArtUrl;
	
	

	public BookSearch() {
		
	}
	
	public BookSearch(String bookId, List<String> authorID, List<String> AuthorName) {
		this.bookId = bookId;
		this.authorID = authorID;
		this.AuthorName = AuthorName;
	}
	
	
	public String getCoverArtUrl() {
		return coverArtUrl;
	}

	public void setCoverArtUrl(String coverArtUrl) {
		this.coverArtUrl = coverArtUrl;
	}

	public String getCoverId() {
		return coverId;
	}

	public void setCoverId(String coverId) {
		this.coverId = coverId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return bookId + " "+ AuthorName;
	}
	

}

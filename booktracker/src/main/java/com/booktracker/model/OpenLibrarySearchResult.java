package com.booktracker.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenLibrarySearchResult implements Serializable {

	private static final long serialVersionUID = 4178653739335406179L;

	@JsonProperty("numFound")
	private long totalResults;
	@JsonProperty("docs")
	private List<BookSearch> searchedBooks;

	
	public OpenLibrarySearchResult() {
		
	}
	
	public OpenLibrarySearchResult(long totalResults, List<BookSearch> searchedBooks) {
		this.totalResults = totalResults;
		this.searchedBooks = searchedBooks;
	}

	public long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(long totalResults) {
		this.totalResults = totalResults;
	}

	public List<BookSearch> getSearchedBooks() {
		return searchedBooks;
	}

	public void setSearchedBooks(List<BookSearch> searchedBooks) {
		this.searchedBooks = searchedBooks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Total Results returns - " + totalResults;
	}

}

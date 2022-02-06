package com.booktracker.model;

import java.io.Serializable;
import java.util.List;

import com.bookshare.dataloader.data.model.UserBook;

public class UserDashboard implements Serializable{

	private static final long serialVersionUID = 6882648512403592299L;
	
	
	private String userName;
	private List<UserBook> readBooks;
	private List<UserBook> currentlyReadingBooks;
	private List<UserBook> wishList;
	
	public UserDashboard(String userName, List<UserBook> readBooks, 
			List<UserBook> currentlyReadingBooks, List<UserBook> wishList) {
		
		this.userName = userName;
		this.readBooks = readBooks;
		this.currentlyReadingBooks = currentlyReadingBooks;
		this.wishList = wishList;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserBook> getReadBooks() {
		return readBooks;
	}

	public void setReadBooks(List<UserBook> readBooks) {
		this.readBooks = readBooks;
	}

	public List<UserBook> getCurrentlyReadingBooks() {
		return currentlyReadingBooks;
	}

	public void setCurrentlyReadingBooks(List<UserBook> currentlyReadingBooks) {
		this.currentlyReadingBooks = currentlyReadingBooks;
	}

	public List<UserBook> getWishList() {
		return wishList;
	}

	public void setWishList(List<UserBook> wishList) {
		this.wishList = wishList;
	}
	
	@Override
	public String toString() {
		return this.userName;
	}
}

package com.userdb.model;

import java.io.Serializable;

public class PublicUserInformation implements Serializable{

	private static final long serialVersionUID = -820438389116710721L;
	
	private String firstName;
	private String lastName;
	private String userName;
	private String favoriteGenres;
	private int numberOfHourReadEveryDay;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFavoriteGenres() {
		return favoriteGenres;
	}
	public void setFavoriteGenres(String favoriteGenres) {
		this.favoriteGenres = favoriteGenres;
	}
	public int getNumberOfHourReadEveryDay() {
		return numberOfHourReadEveryDay;
	}
	public void setNumberOfHourReadEveryDay(int numberOfHourReadEveryDay) {
		this.numberOfHourReadEveryDay = numberOfHourReadEveryDay;
	}
	

}

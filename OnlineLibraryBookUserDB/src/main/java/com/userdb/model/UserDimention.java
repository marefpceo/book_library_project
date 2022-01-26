package com.userdb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserDimention implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4681791922509442135L;

	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private boolean enabled;
	private boolean expired;
	private String favoriteGenres;
	private Integer avgReadingHrsDaily;
	private List<UserAuthority> authorities;

	public UserDimention() {
		authorities = new ArrayList<>();
	}

	public UserDimention(String firstName, String lastName, String username, String password, boolean enabled,
			boolean expired, String favoriteGenres, Integer avgReadingHrsDaily) {
		this();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.expired = expired;
		this.favoriteGenres = favoriteGenres;
		this.avgReadingHrsDaily = avgReadingHrsDaily;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public String getFavoriteGenres() {
		return favoriteGenres;
	}

	public void setFavoriteGenres(String favoriteGenres) {
		this.favoriteGenres = favoriteGenres;
	}

	public Integer getAvgReadingHrsDaily() {
		return avgReadingHrsDaily;
	}

	public void setAvgReadingHrsDaily(Integer avgReadingHrsDaily) {
		this.avgReadingHrsDaily = avgReadingHrsDaily;
	}

	public List<UserAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<UserAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName + " " + username + " " + favoriteGenres + " " + " " + avgReadingHrsDaily;
	}

}

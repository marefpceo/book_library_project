package com.booktracker.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class BookUserDetails implements UserDetails{



	private static final long serialVersionUID = 2302012939641393183L;
	
	private UserDimention userDimention;
	
	public BookUserDetails(UserDimention userDimention) {
		this.userDimention = userDimention;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return userDimention.getPassword();
	}

	@Override
	public String getUsername() {
		return userDimention.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !userDimention.isExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !userDimention.isEnabled();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !userDimention.isExpired();
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public UserDimention getUserDimention() {
		return userDimention;
	}

}

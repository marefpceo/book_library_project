package com.userdb.model;

import java.io.Serializable;

public class UserAuthority implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1706217058893418821L;

	private String authority;
	
	public UserAuthority() {
	}
	
	public UserAuthority(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return this.authority;
	}

}

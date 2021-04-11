package com.task.security;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public class UserAuthority implements GrantedAuthority {

	private String authority;

	public UserAuthority() {
		super();
	}

	public UserAuthority(String authority) {
		super();
		this.authority = authority;
	}

	@Override
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
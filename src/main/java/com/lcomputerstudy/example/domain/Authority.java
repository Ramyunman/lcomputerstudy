package com.lcomputerstudy.example.domain;

import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;
	
	private String username;		//u_id
	private String authority;		//u_auth
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	
}

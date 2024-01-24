package com.ss.response;

import org.springframework.stereotype.Component;

@Component
public class JwtResponse {

	int id;

	String token;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

	public JwtResponse() {
		super();
	}

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	public JwtResponse(int id, String token) {
		super();
		this.id = id;
		this.token = token;
	}

	
}

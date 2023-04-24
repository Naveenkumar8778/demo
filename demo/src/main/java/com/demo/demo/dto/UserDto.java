package com.demo.demo.dto;

import jakarta.validation.constraints.NotEmpty;

public class UserDto {
	private String id;
	@NotEmpty(message = "username is required")
	private String username;
	@NotEmpty(message = "email is required")
	private String email;
	@NotEmpty(message = "password is required")
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	

	
}

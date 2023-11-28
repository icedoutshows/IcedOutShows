package com.icedoutshows.springboot.backend.apirest.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {

	private String emailUser;
	private String message;
	
	
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	
	
}

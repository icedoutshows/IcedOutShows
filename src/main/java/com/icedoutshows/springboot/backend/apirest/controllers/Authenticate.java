package com.icedoutshows.springboot.backend.apirest.controllers;

import org.springframework.stereotype.Service;

@Service
public class Authenticate {
	
	MailManager mailManager;
	
	

	public Authenticate(MailManager mailManager) {
		this.mailManager = mailManager;
	}



	public void sendMessageUser(String email) {
		mailManager.sendMessage(email);
	}
}

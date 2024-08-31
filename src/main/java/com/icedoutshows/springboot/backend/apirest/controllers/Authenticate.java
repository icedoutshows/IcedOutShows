package com.icedoutshows.springboot.backend.apirest.controllers;

import org.springframework.stereotype.Service;

import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;

@Service
public class Authenticate {
	
	MailManager mailManager;
	
	

	public Authenticate(MailManager mailManager) {
		this.mailManager = mailManager;
	}



	public void sendMessageUser(String email) {
		mailManager.sendMessage(email);
	}
	
	public void sendEntradas(Entrada entrada,String emailUser) {
	//	mailManager.sendEntradas(entrada,emailUser);
	}

}

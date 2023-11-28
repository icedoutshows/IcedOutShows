package com.icedoutshows.springboot.backend.apirest.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailManager {
	
	@Value("${spring.mail.username}")
	private String sender;
	
	String messageEmail= MessageHTML.TEMPLATE_MENSAJE;

	JavaMailSender javaMailSender;

	public MailManager(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMessage(String email) {
		MimeMessage message =javaMailSender.createMimeMessage();
		
		try {
			message.setSubject("Bienvenido a la newsletter");
			MimeMessageHelper helper=new MimeMessageHelper(message);
			helper.setTo(email);
			helper.setText(messageEmail,true);
			helper.setFrom(sender);
			javaMailSender.send(message);
			
		}catch(MessagingException e) {
			throw new RuntimeException(e);
			
		}
	}
	
	
}

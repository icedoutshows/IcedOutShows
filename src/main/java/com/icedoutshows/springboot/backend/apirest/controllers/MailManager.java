package com.icedoutshows.springboot.backend.apirest.controllers;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;
import com.icedoutshows.springboot.backend.apirest.models.entity.Evento;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class MailManager {
	
	@Value("${spring.mail.username}")
	private String sender;
	
	String messageEmail= MessageHTML.TEMPLATE_MENSAJE;
	String messageEntrada= MessageHTML.ENTRADA_CONFIRMACION;
	 private String CARACTERES = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	    private SecureRandom random = new SecureRandom();

	JavaMailSender javaMailSender;

	public MailManager(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	private String setCodeInTemplate(String templateCode,String index,String number) {
		return templateCode.replace("{"+index+"}", number);
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
	
	 public  String generarCadenaAleatoria(int longitud) {
	        StringBuilder sb = new StringBuilder(longitud);

	        for (int i = 0; i < longitud; i++) {
	            int indice = random.nextInt(CARACTERES.length());
	            char caracterAleatorio = CARACTERES.charAt(indice);
	            sb.append(caracterAleatorio);
	        }

	        return sb.toString();
	    }
	
	public void sendEntradas(Evento evento,String emailUser) {
	
		
		try {
			   String localMessageEntrada = messageEntrada;
			MimeMessage message =javaMailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(message);
			message.setSubject("Entrada comprada con éxito");
			localMessageEntrada= setCodeInTemplate(localMessageEntrada, "titulo", evento.getArtista());
			localMessageEntrada= setCodeInTemplate(localMessageEntrada, "lugar", evento.getLugar());
			localMessageEntrada= setCodeInTemplate(localMessageEntrada, "imagen", evento.getImagen());
			
			localMessageEntrada= setCodeInTemplate(localMessageEntrada, "correo", emailUser);
			
			//localMessageEntrada= setCodeInTemplate(localMessageEntrada, "recinto", entrada.getRecinto());
			localMessageEntrada= setCodeInTemplate(localMessageEntrada, "fecha", String.valueOf(evento.getFecha()));
			helper.setTo(emailUser);
			helper.setText(localMessageEntrada,true);
			helper.setFrom(sender);
			javaMailSender.send(message);
			
		}catch(MessagingException e) {
			throw new RuntimeException(e);
			
		}
	}
	

	
	
}

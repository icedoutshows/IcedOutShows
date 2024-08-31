package com.icedoutshows.springboot.backend.apirest.security.jwt;

import java.nio.charset.MalformedInputException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.icedoutshows.springboot.backend.apirest.security.entity.UsuarioPrincipal;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private final static Logger logger=LoggerFactory.getLogger(JwtProvider.class);
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;
	
	//Genera un token en función del usuario autenticado
	public String generateToken(Authentication authentication) {
		UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
		return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 1000)) //Fecha de expiracion del token
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact(); //Compacta la info en una cadena JWT
	}
	
	//Extrae el nombre de usuario del token
	public String getNombreUsuarioFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	
	//Validación del token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}catch(MalformedJwtException e) {
			logger.error("token mal formado");
		}catch(UnsupportedJwtException e) {
			logger.error("token no soportado");
		}catch(ExpiredJwtException e) {
			logger.error("token expirado");
		}catch(IllegalArgumentException e) {
			logger.error("token vacio");
		}catch(SignatureException e) {
			logger.error("fallo en la firma");
		}
		return false;
	}
}

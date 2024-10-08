package com.icedoutshows.springboot.backend.apirest.security.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import com.icedoutshows.springboot.backend.apirest.security.services.UserDetailsServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter{
	
	private final static Logger logger=LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	
	//Lógica de filtrado para cada petición Http
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String token= getToken(request);
			if(token!=null && jwtProvider.validateToken(token)) {
				String nombreUsuario=jwtProvider.getNombreUsuarioFromToken(token);
				UserDetails userDetails= userDetailsServiceImpl.loadUserByUsername(nombreUsuario);
				UsernamePasswordAuthenticationToken auth=
						new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth); //Establece la autenticación en el contexto de seguridad de Spring
			}
		}catch(Exception e) {
			logger.error("fail en el metodo doFilter");
		}
		filterChain.doFilter(request, response); //Permite que la solicitud continúe por la cadena de filtros de Spring
		
	}
	
	private String getToken(HttpServletRequest request) {
		String header=request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer")) {
			return header.replace("Bearer ", "");
		}
			return null;
		
	}

}

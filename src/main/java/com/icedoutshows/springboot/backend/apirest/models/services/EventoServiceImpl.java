package com.icedoutshows.springboot.backend.apirest.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icedoutshows.springboot.backend.apirest.models.dao.IEntradaDao;
import com.icedoutshows.springboot.backend.apirest.models.dao.IEventoDao;
import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;
import com.icedoutshows.springboot.backend.apirest.models.entity.Evento;
import com.icedoutshows.springboot.backend.apirest.security.dto.LoginUsuario;
import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;
import com.icedoutshows.springboot.backend.apirest.security.entity.UsuarioPrincipal;
import com.icedoutshows.springboot.backend.apirest.security.jwt.JwtProvider;
import com.icedoutshows.springboot.backend.apirest.security.services.UsuarioService;

@Service
public class EventoServiceImpl implements IEventoService {

	@Autowired
	private IEventoDao eventoDao;
	
	@Autowired
	private IEntradaDao entradaDao;
	

	
	
	@Override
	@Transactional(readOnly=true)
	public List<Evento> findAll() {
		return (List<Evento>) eventoDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Evento findById(Long id) {
		// TODO Auto-generated method stub
		return eventoDao.findById(id).orElse(null);
	}
	

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		eventoDao.deleteById(id);
		
	}

	@Override
	public Evento save(Evento evento) {
		// TODO Auto-generated method stub
		return eventoDao.save(evento);
	}

	
	 public void agregarEntradasAEvento(Evento evento, List<Entrada> entradas) {
	        for (Entrada entrada : entradas) {
	            entrada.setEvento(evento);
	            entradaDao.save(entrada);
	        }
	        evento.setEntradas(entradas);
	        eventoDao.save(evento);
	    }

	@Override
	public List<Entrada> findAllEntradas(Long id) {
		// TODO Auto-generated method stub
		Evento evento= eventoDao.findById(id).orElse(null);
		return evento.getEntradas();
	}
	
	
	
	
	public void deleteEntrada(Long id) {
		// TODO Auto-generated method stub
		entradaDao.deleteById(id);
		
	}
	
	

	 

	
	


}

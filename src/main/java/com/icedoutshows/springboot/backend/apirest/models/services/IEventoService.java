package com.icedoutshows.springboot.backend.apirest.models.services;

import java.util.List;

import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;
import com.icedoutshows.springboot.backend.apirest.models.entity.Evento;
import com.icedoutshows.springboot.backend.apirest.security.dto.LoginUsuario;

public interface IEventoService {
	
	public List<Evento> findAll();
	public Evento findById(Long id);
	public void delete(Long id);
	public Evento save(Evento evento);
	public void agregarEntradasAEvento(Evento evento, List<Entrada> entradas);
	public List<Entrada> findAllEntradas(Long id);
	public void deleteEntrada(Long id);




}

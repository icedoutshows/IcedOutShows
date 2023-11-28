package com.icedoutshows.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;
import com.icedoutshows.springboot.backend.apirest.models.entity.Evento;
import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;

public interface IEventoDao extends CrudRepository<Evento,Long> {


	 
}

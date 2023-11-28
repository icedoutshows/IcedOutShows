package com.icedoutshows.springboot.backend.apirest.models.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;
import com.icedoutshows.springboot.backend.apirest.models.entity.Evento;
import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;


public interface IEntradaDao extends CrudRepository<Entrada,Long>  {

	 @Query("SELECT u.entradas FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario")
	    List<Entrada> findEntradasByNombreUsuario(@Param("nombreUsuario") String nombreUsuario);
	
	 Optional<Entrada> findById(Long id);

	public List<Entrada> findByLugarContaining(String lugar);
	
	public List<Entrada> findByArtistaContaining(String artista);
	
	public List<Entrada> findByRecintoContaining(String recinto);
}

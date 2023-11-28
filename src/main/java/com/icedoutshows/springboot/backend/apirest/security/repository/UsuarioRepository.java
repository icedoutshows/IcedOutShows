package com.icedoutshows.springboot.backend.apirest.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);

}

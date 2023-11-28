package com.icedoutshows.springboot.backend.apirest.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.icedoutshows.springboot.backend.apirest.security.entity.Role;
import com.icedoutshows.springboot.backend.apirest.security.enums.RolNombre;

public interface RolRepository extends JpaRepository<Role,Integer> {
	
	Optional<Role> findByRolNombre(RolNombre rolNombre);

}

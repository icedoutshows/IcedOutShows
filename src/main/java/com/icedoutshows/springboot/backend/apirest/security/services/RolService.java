package com.icedoutshows.springboot.backend.apirest.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icedoutshows.springboot.backend.apirest.security.entity.Role;
import com.icedoutshows.springboot.backend.apirest.security.enums.RolNombre;
import com.icedoutshows.springboot.backend.apirest.security.repository.RolRepository;

@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRepository;
	
	public Optional<Role> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}

	public void save(Role rol) {
		rolRepository.save(rol);
	}
}

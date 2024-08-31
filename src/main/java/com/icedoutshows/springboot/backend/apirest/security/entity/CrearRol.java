package com.icedoutshows.springboot.backend.apirest.security.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.icedoutshows.springboot.backend.apirest.security.enums.RolNombre;
import com.icedoutshows.springboot.backend.apirest.security.services.RolService;

@Component
public class CrearRol implements CommandLineRunner{
	
	
	@Autowired
	RolService rolService;

	@Override
	public void run(String... args) throws Exception {
		
		
		//Role rolAdmin= new Role(RolNombre.ROLE_ADMIN);
		//Role rolUser= new Role(RolNombre.ROLE_USER);
		//rolService.save(rolAdmin);
		//rolService.save(rolUser);
		
	}

}

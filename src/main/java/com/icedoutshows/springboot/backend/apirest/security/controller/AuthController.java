package com.icedoutshows.springboot.backend.apirest.security.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.icedoutshows.springboot.backend.apirest.models.dao.IEntradaDao;
import com.icedoutshows.springboot.backend.apirest.models.dao.IEventoDao;
import com.icedoutshows.springboot.backend.apirest.models.entity.Entrada;
import com.icedoutshows.springboot.backend.apirest.models.entity.Evento;
import com.icedoutshows.springboot.backend.apirest.models.services.IEventoService;
import com.icedoutshows.springboot.backend.apirest.security.dto.JwtDto;
import com.icedoutshows.springboot.backend.apirest.security.dto.LoginUsuario;
import com.icedoutshows.springboot.backend.apirest.security.dto.NuevoUsuario;
import com.icedoutshows.springboot.backend.apirest.security.entity.Role;
import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;
import com.icedoutshows.springboot.backend.apirest.security.entity.UsuarioPrincipal;
import com.icedoutshows.springboot.backend.apirest.security.enums.RolNombre;
import com.icedoutshows.springboot.backend.apirest.security.jwt.JwtProvider;
import com.icedoutshows.springboot.backend.apirest.security.services.RolService;
import com.icedoutshows.springboot.backend.apirest.security.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo (@Valid @RequestBody NuevoUsuario nuevoUsuario){
		
		if(nuevoUsuario.getPassword()=="" || nuevoUsuario.getNombre()=="" || nuevoUsuario.getNombreUsuario()=="" || nuevoUsuario.getEmail()=="" ) {
			return new ResponseEntity<>("campos mal puestos",HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario())) {
			return new ResponseEntity<>("ese nombre ya existe",HttpStatus.BAD_REQUEST);
		}
		
		if(usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
			return new ResponseEntity<>("ese email ya existe",HttpStatus.BAD_REQUEST);
		}
		
		Usuario usuario=new Usuario
				( nuevoUsuario.getNombre(),nuevoUsuario.getNombreUsuario(),nuevoUsuario.getEmail(),passwordEncoder.encode(nuevoUsuario.getPassword()));
		Set<Role> roles=new HashSet<>();
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		usuario.setRoles(roles);
		usuarioService.save(usuario);
		return new ResponseEntity<>(HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<JwtDto> login(@RequestBody LoginUsuario loginUsuario,BindingResult bindingResult){
		if(bindingResult.hasErrors())
			return new ResponseEntity("campos mal puestos",HttpStatus.BAD_REQUEST);
		Authentication authentication=
			 authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
	SecurityContextHolder.getContext().setAuthentication(authentication);
	String jwt=jwtProvider.generateToken(authentication);
	UserDetails userDetails=(UserDetails) authentication.getPrincipal();
	JwtDto jwtDto=new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
	return new ResponseEntity(jwtDto,HttpStatus.OK);
	}
	
	
	 
	 


}

package com.icedoutshows.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.icedoutshows.springboot.backend.apirest.security.dto.LoginUsuario;
import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;
import com.icedoutshows.springboot.backend.apirest.security.services.UsuarioService;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EventoRestController {
	
	@Autowired
	private IEventoService eventoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private IEventoDao eventoDao;

	@Autowired
	private IEntradaDao entradaDao;
	
	@GetMapping("/eventos")
	public List <Evento> index(){
		return eventoService.findAll();
		
	}
	
	@GetMapping("/eventos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Evento show(@PathVariable("id") Long id) {
		return eventoService.findById(id);
		
	}
	
	
	@DeleteMapping("/eventos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		eventoService.delete(id);
	}

	@PostMapping("/eventos")
	@ResponseStatus(HttpStatus.CREATED)
	public Evento create(@RequestBody Evento evento) {
		 	return eventoService.save(evento);
	}	
	
	
	@PutMapping("/eventos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Evento update(@RequestBody Evento evento, @PathVariable Long id) {
		Evento currentEvento = this.eventoService.findById(id);
		currentEvento.setDescripcion(evento.getDescripcion());
		currentEvento.setImagen(evento.getImagen());
		currentEvento.setTitulo(evento.getTitulo());
		currentEvento.setRecomendado(evento.isRecomendado());
		this.eventoService.save(currentEvento);
		return currentEvento;
	}
	
	


	 @PostMapping("/eventos/{id}")
		@ResponseStatus(HttpStatus.OK)
	    public Evento agregarEntradasEvento(@RequestBody List<Entrada> entradas,@PathVariable Long id) {
	        Evento evento = eventoService.findById(id);
	        eventoService.agregarEntradasAEvento(evento, entradas);
	        return evento;
	    }	
	 
	 
		@GetMapping("/eventos/entradas/{id}")
		public List <Entrada> findAllEntradas(@PathVariable Long id){
			return eventoService.findAllEntradas(id);
		}
		
		
	
		
		 @GetMapping("/eventos/buscarPorArtista")
		 @ResponseStatus(HttpStatus.OK)
		    public List<Entrada> buscarPorArtista(@RequestParam String artista) {
			List<Entrada> entradas = entradaDao.findByArtistaContaining(artista);
			return entradas;
		      
		    }
		 
		 

		 @GetMapping("/eventos/buscarPorLugar")
		 @ResponseStatus(HttpStatus.OK)
		    public List<Entrada>  buscarPorLugar(@RequestParam String lugar) {
			   List<Entrada> entradas= entradaDao.findByLugarContaining(lugar);
		      return entradas;
		    }
		 
		 @GetMapping("/eventos/buscarPorRecinto")
		 @ResponseStatus(HttpStatus.OK)
		    public List<Entrada> buscarPorRecinto(@RequestParam String recinto) {
			   List<Entrada> entradas= entradaDao.findByRecintoContaining(recinto);
		      return entradas;
		    }
		 
		 
		 @GetMapping("/eventos/entrada")
		    public Entrada findEntradaById(@RequestParam Long idEvento,@RequestParam Long idEntrada) {
			 List<Entrada>entradas= eventoService.findAllEntradas(idEvento);
			  for(Entrada entrada:entradas) {
				  if(entrada.getEntradaId()==idEntrada)
				 return entrada;
			  }
			  return null;
		      
		    }
		 
		 @GetMapping("/eventos/entradaCompleta/{id}")
		    public Entrada findEntrada(@PathVariable("id") Long id) {
					return entradaDao.findById(id).orElse(null);
					
				}
			
		 
		 
		 
		 
			@DeleteMapping("/eventos/entrada/{id}")
			@ResponseStatus(HttpStatus.NO_CONTENT)
			public void deleteEntrada(@PathVariable("id") Long id) {
				eventoService.deleteEntrada(id);
			}

			

			@PutMapping("/eventos/entrada/{id}")
			@ResponseStatus(HttpStatus.CREATED)
			public Entrada updateEntrada(@RequestBody Entrada entrada, @PathVariable Long id) {
				Entrada currentEntrada = entradaDao.findById(id).orElse(entrada);
				currentEntrada.setArtista(entrada.getArtista());
				currentEntrada.setRecinto(entrada.getRecinto());
				currentEntrada.setFecha(entrada.getFecha());
				currentEntrada.setPrecio(entrada.getPrecio());
				currentEntrada.setLugar(entrada.getLugar());
				entradaDao.save(currentEntrada);
				return currentEntrada;
			}
		 
		 
		 @Autowired
			Authenticate authenticate;
			

			
			@PostMapping("/eventos/authenticate")
			public void authenticate(@RequestBody LoginRequest loginRequest){	
				authenticate.sendMessageUser(loginRequest.getEmailUser());
		
			}
			
			
			@GetMapping("/eventos/entradas")
			 public List<Entrada> obtenerEntradasPorNombreUsuario(String nombreUsuario) {
			        return entradaDao.findEntradasByNombreUsuario(nombreUsuario);
			    }
			
			
			 @PostMapping("/eventos/entradasComprar")
			 @ResponseStatus(HttpStatus.CREATED)
			 public void asignarEntradaAUsuario(@RequestParam String nombreUsuario,@RequestParam Long idEntrada) {
			        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).orElse(null);
			        Entrada entrada = entradaDao.findById(idEntrada).orElse(null);
			        if (usuario != null && entrada != null) {
			            usuario.getEntradas().add(entrada);
			            entrada.getUsuarios().add(usuario);
			            usuarioService.save(usuario);
			            entradaDao.save(entrada);
			        } else {
			     
			        }
			    }
	
}

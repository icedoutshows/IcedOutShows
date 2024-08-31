package com.icedoutshows.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.icedoutshows.springboot.backend.apirest.security.entity.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="entradas")
public class Entrada implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	private Long entradaId;
	private String recinto;
	 @JsonIgnoreProperties({"entradas"})
	 @ManyToOne
	 @JoinColumn(name = "evento_id", nullable=false)
	 private Evento evento;
	
	
	@JsonIgnoreProperties({"entradas"})
	 @ManyToMany(mappedBy = "entradas")
    private Set<Usuario> usuarios = new HashSet<>();
	
	 
	 public Entrada() {
		 
	 }

	public Entrada( String recinto, Evento evento) {
		super();
		
		this.evento=evento;
	
	
	}
	
	

	public Long getEntradaId() {
		return entradaId;
	}

	public void setEntradaId(Long id) {
		this.entradaId = entradaId;
	}


	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	 



	public String getRecinto() {
		return recinto;
	}

	public void setRecinto(String recinto) {
		this.recinto = recinto;
	}


	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	













	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 
	 
}

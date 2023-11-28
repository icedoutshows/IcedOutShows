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
	private String lugar;
	private String recinto;
	private String artista;
	private double precio;
	@Temporal(TemporalType.DATE)
	private Date fecha;

	
	 @JsonIgnoreProperties({"entradas"})
	 @ManyToOne
	 @JoinColumn(name = "id", nullable=false)
	 private Evento evento;
	
	
	@JsonIgnoreProperties({"entradas"})
	 @ManyToMany(mappedBy = "entradas")
    private Set<Usuario> usuarios = new HashSet<>();
	
	 
	 public Entrada() {
		 
	 }

	public Entrada( String lugar,String recinto, Date fecha,String artista,double precio, Evento evento) {
		super();
		
		this.lugar = lugar;
		this.fecha = fecha;
		this.evento=evento;
		this.artista=artista;
		this.precio=precio;
	
	}
	
	

	public Long getEntradaId() {
		return entradaId;
	}

	public void setEntradaId(Long id) {
		this.entradaId = entradaId;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
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

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
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

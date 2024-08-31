package com.icedoutshows.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="eventos")
public class Evento implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private Long evento_id;
	private String descripcion;
	private String imagen;
	private String titulo;
	private boolean recomendado;
	private String lugar;
	private String artista;
	private double precio;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	@JsonIgnoreProperties({"evento"})
	@OneToMany(mappedBy = "evento", cascade = CascadeType.REMOVE)
	private List<Entrada> entradas;
	
	
	
	public Evento() {
		
	}
	
	
	public List<Entrada> getEntradas() {
		return entradas;
	}


	public void setEntradas(List<Entrada> entradas) {
		this.entradas = entradas;
	}


	public Evento(String descripcion, String imagen, String titulo, boolean recomendado) {
		
		this.descripcion = descripcion;
		this.imagen = imagen;
		this.titulo = titulo;
		this.recomendado = recomendado;
		
	
	}

	
	public Long getEvento_id() {
		return evento_id;
	}


	public void setEvento_id(Long evento_id) {
		this.evento_id = evento_id;
	}


	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	


	public String getLugar() {
		return lugar;
	}


	public void setLugar(String lugar) {
		this.lugar = lugar;
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


	public Date getFecha() {
		return fecha;
	}


	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	



	public boolean isRecomendado() {
		return recomendado;
	}
	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}





	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}

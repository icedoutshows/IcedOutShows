package com.icedoutshows.springboot.backend.apirest.security.entity;

import java.io.Serializable;

import org.antlr.v4.runtime.misc.NotNull;

import com.icedoutshows.springboot.backend.apirest.security.enums.RolNombre;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int rol_id;
	
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;
	
	public Role() {
		
	}

	public Role(RolNombre rolNombre) {
		super();
		this.rolNombre = rolNombre;
	}

	public int getRol_Id() {
		return rol_id;
	}

	public void setId(int rol_id) {
		this.rol_id = rol_id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	
	

}

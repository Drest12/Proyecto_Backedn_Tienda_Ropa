package com.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reclamoId;
    private String nombre;
    private String asunto;
    private String email;
    private boolean estado;
	public Reclamo(String nombre, String asunto, String email, boolean estado) {
		
		this.nombre = nombre;
		this.asunto = asunto;
		this.email = email;
		this.estado = estado;
	}
	
	
	public Reclamo() {
		super();
	}


	public Long getReclamoId() {
		return reclamoId;
	}
	public void setReclamoId(Long reclamoId) {
		this.reclamoId = reclamoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
    
    
    
}

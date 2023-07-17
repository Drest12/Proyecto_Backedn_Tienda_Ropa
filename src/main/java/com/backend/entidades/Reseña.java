package com.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reseña")
public class Reseña {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reseñaId;
	private String reseña;
	private boolean estado;
	public Reseña(String reseña, boolean estado) {
		super();
		this.reseña = reseña;
		this.estado = estado;
	}
	public Reseña() {
		super();
	}
	public Long getReseñaId() {
		return reseñaId;
	}
	public void setReseñaId(Long reseñaId) {
		this.reseñaId = reseñaId;
	}
	public String getReseña() {
		return reseña;
	}
	public void setReseña(String reseña) {
		this.reseña = reseña;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}

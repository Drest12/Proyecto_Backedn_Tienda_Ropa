package com.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cupon")
public class Cupon {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cuponId;
	private String nombre;
	private String descuento;
	private boolean estado;
	public Cupon(String nombre, String descuento, boolean estado) {
		super();
		this.nombre = nombre;
		this.descuento = descuento;
		this.estado = estado;
	}
	public Cupon() {
		super();
	}
	public Long getCuponId() {
		return cuponId;
	}
	public void setCuponId(Long cuponId) {
		this.cuponId = cuponId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescuento() {
		return descuento;
	}
	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
	
}

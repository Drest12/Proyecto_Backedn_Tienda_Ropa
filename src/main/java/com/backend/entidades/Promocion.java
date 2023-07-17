package com.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "promocion")
public class Promocion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long promocionId;
	private String titulo;
	private String descuento;
	@Lob
	private byte[] imagen;
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "productoId", nullable = false)
	private Producto producto;

	public Promocion(String titulo, String descuento, byte[] imagen, boolean estado, Producto producto) {
		super();
		this.titulo = titulo;
		this.descuento = descuento;
		this.imagen = imagen;
		this.estado = estado;
		this.producto = producto;
	}

	public Promocion() {
		super();
	}

	public Long getPromocionId() {
		return promocionId;
	}

	public void setPromocionId(Long promocionId) {
		this.promocionId = promocionId;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescuento() {
		return descuento;
	}

	public void setDescuento(String descuento) {
		this.descuento = descuento;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
}

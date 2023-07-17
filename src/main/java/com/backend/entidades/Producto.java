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
@Table(name = "producto")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productoId;
	private String nombre;
	private String precio;
	private String descripcion;

	private String version;
	private String material;
	private String equipo;
	private String temporada;

	private String genero;
	private String atributo;
	private boolean estado;
	@Lob
	private byte[] imagen1;
	@Lob
	private byte[] imagen2;
	@ManyToOne
	@JoinColumn(name = "categoriaId", nullable = false)
	private Categoria categoria;
	public Producto(String nombre, String precio, String descripcion, String stock_total, String version,
			String material, String equipo, String temporada, String genero, String atributo, boolean estado,
			byte[] imagen1, byte[] imagen2, Categoria categoria) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.descripcion = descripcion;

		this.version = version;
		this.material = material;
		this.equipo = equipo;
		this.temporada = temporada;
		this.genero = genero;
		this.atributo = atributo;
		this.estado = estado;
		this.imagen1 = imagen1;
		this.imagen2 = imagen2;
		this.categoria = categoria;
	}
	
	
	public Producto() {
		super();
	}


	public Long getProductoId() {
		return productoId;
	}
	public void setProductoId(Long productoId) {
		this.productoId = productoId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getEquipo() {
		return equipo;
	}
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public String getTemporada() {
		return temporada;
	}
	public void setTemporada(String temporada) {
		this.temporada = temporada;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getAtributo() {
		return atributo;
	}
	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public byte[] getImagen1() {
		return imagen1;
	}
	public void setImagen1(byte[] imagen1) {
		this.imagen1 = imagen1;
	}
	public byte[] getImagen2() {
		return imagen2;
	}
	public void setImagen2(byte[] imagen2) {
		this.imagen2 = imagen2;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}

package com.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "empresa")
public class Empresa {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empresaId;
	private String nombre;
	private String descripcion;
	private String eslogan;
	private String direccion;
	private String telefono;
	private String celular;
	private String correo;
	private boolean estado;
	@Lob
	private byte[] imagen;
	public Empresa(String nombre, String descripcion, String eslogan, String direccion, String telefono, String celular,
			String correo, boolean estado, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.eslogan = eslogan;
		this.direccion = direccion;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
		this.estado = estado;
		this.imagen = imagen;
	}
	public Empresa() {
		super();
	}
	public Long getEmpresaId() {
		return empresaId;
	}
	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getEslogan() {
		return eslogan;
	}
	public void setEslogan(String eslogan) {
		this.eslogan = eslogan;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public byte[] getImagen() {
		return imagen;
	}
	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	
	
}

package com.backend.entidades;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "compra")
public class Compra {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long compraId;
	    private String metodoPago;
	    private Date diaEntrega;
	    private String producto;
	    private int cantidad;
	    private double precioUnidad;
	    private double descuento;

		private double totalCompra;
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date fechaCompra;
	    @ManyToOne
	    @JoinColumn(name = "proveedorId", nullable = false)
	    private Proveedor proveedor;
	    @PrePersist
	    protected void onCreate() {
	        fechaCompra = new Date();
	    }
		
	    
	    
		public Compra(String metodoPago, Date diaEntrega, String producto, int cantidad, double precioUnidad,
				double descuento, double totalCompra, Date fechaCompra, Proveedor proveedor) {
			super();
			this.metodoPago = metodoPago;
			this.diaEntrega = diaEntrega;
			this.producto = producto;
			this.cantidad = cantidad;
			this.precioUnidad = precioUnidad;
			this.descuento = descuento;
			this.totalCompra = totalCompra;
			this.fechaCompra = fechaCompra;
			this.proveedor = proveedor;
		}



		public Compra() {
			super();
		}
		public Long getCompraId() {
			return compraId;
		}
		public void setCompraId(Long compraId) {
			this.compraId = compraId;
		}
		public String getMetodoPago() {
			return metodoPago;
		}
		public void setMetodoPago(String metodoPago) {
			this.metodoPago = metodoPago;
		}
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
		public Date getDiaEntrega() {
			return diaEntrega;
		}
		public void setDiaEntrega(Date diaEntrega) {
			this.diaEntrega = diaEntrega;
		}
		public String getProducto() {
			return producto;
		}
		public void setProducto(String producto) {
			this.producto = producto;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public double getPrecioUnidad() {
			return precioUnidad;
		}
		public void setPrecioUnidad(double precioUnidad) {
			this.precioUnidad = precioUnidad;
		}
		public double getDescuento() {
			return descuento;
		}
		public void setDescuento(double descuento) {
			this.descuento = descuento;
		}
		@JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
		public Date getFechaCompra() {
			return fechaCompra;
		}
		public void setFechaCompra(Date fechaCompra) {
			this.fechaCompra = fechaCompra;
		}
		public Proveedor getProveedor() {
			return proveedor;
		}
		public void setProveedor(Proveedor proveedor) {
			this.proveedor = proveedor;
		}
		public void calcularTotalCompra() {
		    double subtotal = precioUnidad * cantidad;
		    double descuentoAplicado = subtotal * (descuento / 100.0);
		    totalCompra = subtotal - descuentoAplicado;
		}

		public double getTotalCompra() {
			return totalCompra;
		}

		public void setTotalCompra(double totalCompra) {
			this.totalCompra = totalCompra;
		}


	

}

package com.backend.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "producto_talla")
public class Producto_Talla {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long productoTallaid;
	    private String talla;
	    private int stock;
	    @ManyToOne
	    @JoinColumn(name = "productoId")
	    private Producto producto;
		public Producto_Talla(String talla, int stock, Producto producto) {
			super();
			this.talla = talla;
			this.stock = stock;
			this.producto = producto;
		}
		public Producto_Talla() {
			super();
		}
		public Long getProductoTallaid() {
			return productoTallaid;
		}
		public void setProductoTallaid(Long productoTallaid) {
			this.productoTallaid = productoTallaid;
		}
		public String getTalla() {
			return talla;
		}
		public void setTalla(String talla) {
			this.talla = talla;
		}
		public int getStock() {
			return stock;
		}
		public void setStock(int stock) {
			this.stock = stock;
		}
		public Producto getProducto() {
			return producto;
		}
		public void setProducto(Producto producto) {
			this.producto = producto;
		}
	    
}

package com.backend.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.entidades.Categoria;

import com.backend.entidades.Producto;


public interface ProductoRepository  extends JpaRepository<Producto,Long>{
	List<Producto> findByEstadoIsTrue();

	List<Producto> findByEstadoIsFalse();
	List<Producto> findByEquipo(String equipo);
	List<Producto> findByCategoria(Categoria categoria);
}

package com.backend.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.entidades.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	List<Categoria> findByEstadoIsTrue();

	List<Categoria> findByEstadoIsFalse();

}
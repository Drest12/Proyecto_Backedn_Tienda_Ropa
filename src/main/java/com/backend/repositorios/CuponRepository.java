package com.backend.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.entidades.Cupon;

@Repository
public interface CuponRepository extends JpaRepository<Cupon, Long> {
	List<Cupon> findByEstadoIsTrue();

	List<Cupon> findByEstadoIsFalse();
}

package com.backend.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.entidades.Reclamo;

public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {
	List<Reclamo> findByEstadoIsTrue();

	List<Reclamo> findByEstadoIsFalse();
}

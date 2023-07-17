package com.backend.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.entidades.Promocion;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {
	List<Promocion> findByEstadoIsTrue();

	List<Promocion> findByEstadoIsFalse();
}
